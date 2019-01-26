package org.fenxui.application.marshall;

import javafx.beans.property.IntegerProperty;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.math.NumberUtils;
import org.fenxui.application.view.components.option.FieldOption;

public class IntegerMarshallStrategy implements MarshallStrategy<IntegerProperty, TextField> {

	@Override
	public void execute(FieldOption<IntegerProperty> fieldOption, TextField textField) {
		IntegerProperty integerProperty = fieldOption.getValue();
		integerProperty.addListener((observable, oldValue, newValue) -> textField.textProperty().set(newValue.toString()));
		textField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (NumberUtils.isDigits(newValue) && !integerProperty.isBound()) {
				integerProperty.set(NumberUtils.createInteger(newValue));
			}
		});
		textField.setText(String.valueOf(integerProperty.get()));
	}

}
