package org.fenxui.application.marshall;

import javafx.beans.property.DoubleProperty;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fenxui.application.view.components.option.FieldOption;

public class DoubleMarshallStrategy implements MarshallStrategy<DoubleProperty, TextField> {
	private static final Logger logger = LogManager.getLogger(DoubleMarshallStrategy.class);

	@Override
	public void execute(FieldOption<DoubleProperty> fieldOption, TextField textField) {
		DoubleProperty doubleProperty = fieldOption.getValue();
		doubleProperty.addListener((observable, oldValue, newValue) -> textField.textProperty().set(newValue.toString()));
		textField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (NumberUtils.isCreatable(newValue) && !doubleProperty.isBound()) {
				if (logger.isDebugEnabled()) {
					logger.debug(fieldOption.getFieldName() + " oldValue: " + oldValue + " newValue: " + newValue);
				}
				doubleProperty.set(NumberUtils.createDouble(newValue));
			}
		});
		textField.setText(String.valueOf(doubleProperty.get()));
	}
}
