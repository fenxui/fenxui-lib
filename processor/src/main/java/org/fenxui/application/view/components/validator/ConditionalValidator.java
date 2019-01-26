package org.fenxui.application.view.components.validator;

import javafx.beans.property.BooleanProperty;
import org.fenxui.api.validator.IValidator;

public interface ConditionalValidator extends IValidator {
	BooleanProperty conditionalProperty();
}
