package org.fenxui.application.marshall;

import javafx.beans.property.Property;
import javafx.scene.control.TextField;
import org.fenxui.application.view.components.option.FieldOption;

public class BindBiDirectionalMarshallStrategy implements MarshallStrategy<Property, TextField> {

	@Override
	public void execute(FieldOption fieldOption, TextField textField) {
		textField.textProperty().bindBidirectional(fieldOption.getValue());
	}
}
