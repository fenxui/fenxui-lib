package org.fenxui.application.view.factory.handler.action;

import org.fenxui.application.view.factory.handler.NodeContext;
import org.fenxui.core.exception.FenxuiInitializationException;

import java.lang.annotation.Annotation;

public interface MethodAnnotationHandler {

	void handle(NodeContext nodeContext, Annotation annotation) throws FenxuiInitializationException;
}
