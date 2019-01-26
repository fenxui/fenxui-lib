package org.fenxui.application.view.factory.handler;

import org.fenxui.core.exception.FenxuiInitializationException;

import java.lang.annotation.Annotation;

public interface FieldAnnotationHandler {

	void handle(NodeContext fieldContext, Annotation annotation) throws FenxuiInitializationException, FenxuiInitializationException;
}
