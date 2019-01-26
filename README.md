# fenxui
JavaFX Wrapper for annotation-based programming designed to make simple apps simpler.
## Getting Started
* Clone the fenxui-lib repository
* Build fenxui library using gradle <i>install</i> target
```
C:\Users\Me\Documents\GitHub\fenxui-lib>gradle install
```
* Clone the fenxui-ootb repository
* Build the fenxui-ootb dependencies using the gradle <i>install</i> target
```
C:\Users\Me\Documents\GitHub\fenxui-ootb>gradle install
```
* Add to your app
```
repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    compile group: 'org.fenxui', name: 'ootb-jfx-jdk8', version: '1.0+'
```

* Make your main class extend <b>FenxuiApplication</b> and provide an annotated view model for introspection.
```java
public class SampleApp extends FenxuiApplication {

	@Override
	public FenxuiConfig getFenxuiConfig() {
		return new FenxuiConfig.Builder()
				.css(SampleApp.class.getResource("/css/SampleApp.css"))
				.title("Sample Fenxui App")
				.build();
	}

	@Override
	public FenxuiPrototype getFenxuiPrototype() {
		return JFX8Prototype.newInstance(new SampleViewModel(), () -> {
			log.info("Application closing");
			Platform.exit();
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
```
* That's it!  See the [samples](https://github.com/fenxui/samples) repository for more demo applications.
### Prerequisites
* You must have a JDK (version 8) that supports JavaFX such as [Amazon Corretto 8](https://docs.aws.amazon.com/corretto/latest/corretto-8-ug/downloads-list.html/).
* You must have Gradle installed to be able to build fenxui

## Annotations
### Layout
#### @Menu
declares a class holding a menu
* cssClass (default: "menuitem")
* orientation (default: VERTICAL)
* minimumWidth (default: 185)
* minimumHeight (default: 23)
example<br>
```java
@Menu
public class SampleViewModel extends FenxuiViewModel {
```
![A sample side menu](https://user-images.githubusercontent.com/3435255/50727765-97b49a80-10ed-11e9-97d6-b7dfb865b265.png)

#### @MenuItem
declares a menu item or tab
* value (Text label)
* cssClass (default "menupage-link")
* required (flag if user input on the page is required)

example
```java
@MenuItem("Server Settings")
private final ServerSettings serverSettings = new ServerSettings(this);
```
#### @AppPage
Title/stylings for page
* value (Title)
* cssClass (default: "apppage")

example<br>
```java
@AppPage("Server Settings")
public class ServerSettings {
```

### @FormField
Declares a user-input field
* label
* factory (default "text") supported out the box values are listed as constants in OOTBFieldPrototypes.  You also have the ability to register custom field types as long as you also register the factory.
* section (If the field is hidden by default (ADDITIONAL) or displayed by default (DEFAULT); default DEFAULT)
* dynamicWidth (default false).  True if the field width should be bound to the page width. 

example<br>
```java
@FormField(label = "Server")
private final StringProperty machine = new SimpleStringProperty();
```

<b>Note</b>: the TextField/ComboBox/CheckBox, etc value will be saved on the Property field it decorates.  This allows you to use the field values directly.
![Supported form field types](https://user-images.githubusercontent.com/3435255/50727763-97b49a80-10ed-11e9-9fdd-3143e7376a37.png)

### @ExpressionFormField
Declares a field that is populated by a JEXL expression.
* label
* expression  (ie: "#{fieldA} + #{fieldB} + #{OtherClass.fieldZ}")
* factory (default "text") supported out the box values are listed as constants in OOTBFieldPrototypes.  You also have the ability to register custom field types as long as you also register the factory.
* dynamicWidth (default false).  True if the field width should be bound to the page width. 
* readOnly (default true).

example<br>
```java
@FormField(label = "Feet")
private IntegerProperty heightFeet = new SimpleIntegerProperty(0);

@FormField(label = "Inches")
private IntegerProperty heightInches = new SimpleIntegerProperty(1);//avoid division by 0

@ExpressionFormField(label = "total inches", expression = "12 * #{heightFeet} + #{heightInches}")
private IntegerProperty heightInchesTotal = new SimpleIntegerProperty();

@FormField(label = "Weight (lbs)")
private IntegerProperty weight = new SimpleIntegerProperty(0);

@ExpressionFormField(label = "BMI", expression = "#{weight} / (#{heightInchesTotal} * #{heightInchesTotal} * 1.0) * 7.03", factory = "percent")//'1.0' -> avoid integer division
private ObjectProperty<BigDecimal> bmi = new SimpleObjectProperty<>(BigDecimal.ZERO);
```
![Expression populated field](https://user-images.githubusercontent.com/3435255/51066030-086c2300-15d6-11e9-8762-f29d8e7cfaf4.png)

### Value Providers
#### @CheckBoxValue
The value to save on the bound StringProperty if the checkbox is checked or unchecked
* checked
* unchecked

```java
@CheckBoxValue(checked="true", unchecked="false")
private final StringProperty enableNotifications = new SimpleStringProperty("false");
```

#### @ValueProviderValue
Provides key-value pair for ComboBox. Decorate field for multiple values.
* key (the value to save on the field)
* value (the value to display in the ComboBox)

```java
@ValueProviderValue(key="ORCL", value= "Oracle")
@ValueProviderValue(key="PGRS", value= "PostgreSQL")
private final StringProperty serverType = new SimpleStringProperty();
```

### Validation
#### @Validator
Enables validation on the field.  A message will be displayed below the field when validation fails.  You can have multiple validators per field
* type 
* message
* evalExpression (JEXL-expression that controls if validation should be enforced on supporting validators)

```java
@Validator(type = OOTBFieldValidators.REQUIRED, message = "Port is required")
@Validator(type = OOTBFieldValidators.NUMERIC, message = "Port must be numeric")
private final StringProperty port = new SimpleStringProperty("8080");

@FormField(label = "Enable notifications", type=CHECKBOX)
@CheckBoxValue(checked="true", unchecked="false")
private final StringProperty enableNotifications = new SimpleStringProperty("false");

@FormField(label = "From email")
@Validator(type = OOTBFieldValidators.REQUIRED, message = "From email is required", evalExpression="#{enableNotifications} eq 'true'")
private final StringProperty senderAddress = new SimpleStringProperty();
```
![Conditional validator on value in same form](https://user-images.githubusercontent.com/3435255/50727761-97b49a80-10ed-11e9-85b1-e47e2b4d2e20.png)
```java
@AppPage("Tab B")
public class SimpleTabPageB {

  @FormField(label = "Required when Tab A enabled")
  @Validator(type = OOTBFieldValidators.REQUIRED, message = "From email is required", evalExpression="#{SimpleTabPageA.enable} eq 'true'")
  private final StringProperty conditionallyRequiredField = new SimpleStringProperty();

@AppPage("Tab A")
public class SimpleTabPageA  {

	@FormField(label = "Enable", type=CHECKBOX)
	@CheckBoxValue(checked="true", unchecked="false")
	private final StringProperty enable = new SimpleStringProperty("false");
```
![Conditional validator on value in different form](https://user-images.githubusercontent.com/3435255/50727762-97b49a80-10ed-11e9-8591-ebca8b94b89e.png)

### @FormAction
Declares a method to be executed by a widget's onAction() listener.
* value (Text displayed on button/widget)
* factory (default: OOTBActionPrototype.BUTTON_ACTION)
* cssClass (default: "button-raised")
```
@FormAction("Reverse")
public void reverse(Event event) {
	reversed.set(StringUtils.reverse(data.get()));
}
```

## Built With
* [Amazon Corretto 8](https://docs.aws.amazon.com/corretto/latest/corretto-8-ug/downloads-list.html/) - JDK8/OpenJFX8
* [Gradle](https://gradle.org/) - Dependency Management
## Contributing

## Versioning

## Authors

* **Ben Arnold** - *Initial work*

See also the list of [contributors](https://github.com/benfarnold/fenxui/contributors) who participated in this project.

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgment