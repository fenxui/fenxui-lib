package org.fenxui.application.marshall;

import javafx.beans.property.StringProperty;
import javafx.scene.control.CheckBox;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.application.view.components.valueprovider.ValueProvider;

import java.util.List;

public class CheckBoxMarshallStrategy implements MarshallStrategy<StringProperty, CheckBox> {

	@Override
	public void execute(FieldOption<StringProperty> fieldOption, CheckBox checkBox) {
		StringProperty valueProperty = fieldOption.getValue();

		ValueProvider valueProvider = fieldOption.getValueProvider();
		List<FieldOption.DisplayValue> values = valueProvider.getValues();
		String selectedValue = values.get(0).getSaveValue();
		String unselectedValue = values.get(1).getSaveValue();
		checkBox.setSelected(selectedValue.equalsIgnoreCase(valueProperty.getValue()));//initial values
		//propagate changes
		checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				valueProperty.setValue(selectedValue);
			} else {
				valueProperty.setValue(unselectedValue);
			}
		});

	}
}
