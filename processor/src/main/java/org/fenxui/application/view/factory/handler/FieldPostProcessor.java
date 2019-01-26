package org.fenxui.application.view.factory.handler;

import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.core.exception.FenxuiInitializationException;

public interface FieldPostProcessor {
	void postProcess(FieldOption fieldOption) throws FenxuiInitializationException;
	String getFieldName();
}
