package org.fenxui.application.view.factory.handler.action;

import org.fenxui.annotation.FormAction;
import org.fenxui.application.view.components.option.MethodOption;
import org.fenxui.application.view.factory.handler.NodeContext;
import org.fenxui.core.exception.FenxuiInitializationException;

import java.lang.annotation.Annotation;

public class FormActionAnnotationHandler implements MethodAnnotationHandler {

	@Override
	public void handle(NodeContext nodeContext, Annotation annotation) throws FenxuiInitializationException {
		FormAction formAction = (FormAction) annotation;
		MethodOption methodOption = nodeContext.getActiveMethodOption();
		methodOption.setActionFactory(nodeContext.getActionFactory(formAction.factory()));
		methodOption.setLabel(formAction.value());
		methodOption.setCssClass(formAction.cssClass());
	}
}
