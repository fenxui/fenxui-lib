package org.fenxui.application.view.factory.handler;

import java.lang.annotation.Annotation;
import org.fenxui.annotation.Validator;
import org.fenxui.annotation.Validators;
import org.fenxui.core.exception.FenxuiInitializationException;

public class ValidatorsAnnotationHandler implements FieldAnnotationHandler {
	//We may need to do a lookup for handler in future to handle overriden handlers
	private final ValidatorAnnotationHandler handler = new ValidatorAnnotationHandler();
	
	@Override
	public void handle(NodeContext fieldContext, Annotation annotation) throws FenxuiInitializationException {
		Validators validators = (Validators) annotation;
		for (Validator validator : validators.value()) {
			handler.handle(fieldContext, validator);
		}		
	}

}
