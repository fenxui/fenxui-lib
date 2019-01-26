package org.fenxui.application.view.factory.handler.page;

import org.fenxui.application.view.factory.ootb.FrameContext;

import java.lang.annotation.Annotation;

public interface PageAnnotationHandler {

	void handle(FrameContext frameContext, Annotation annotation);
}
