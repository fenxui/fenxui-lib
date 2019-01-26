package org.fenxui.application.view.factory.handler;

import java.lang.annotation.Annotation;

import org.fenxui.annotation.ValueProviderValue;
import org.fenxui.annotation.ValueProviderValues;
import org.fenxui.core.exception.FenxuiInitializationException;

public class ValueProviderValuesAnnotationHandler implements FieldAnnotationHandler {
	//We may need to do a lookup for handler in future to handle overriden handlers
	private final ValueProviderValueAnnotationHandler handler = new ValueProviderValueAnnotationHandler();
	
	@Override
	public void handle(NodeContext fieldContext, Annotation annotation) throws FenxuiInitializationException {
		ValueProviderValues values = (ValueProviderValues) annotation;
		for (ValueProviderValue value : values.value()) {
			handler.handle(fieldContext, value);
		}		
	}

}
