package org.fenxui.application.view.factory.handler.app;

import org.fenxui.annotation.app.MenuItem;
import org.fenxui.application.view.factory.handler.FieldAnnotationHandler;
import org.fenxui.application.view.factory.handler.NodeContext;
import org.fenxui.application.view.factory.ootb.FrameContext;

import java.lang.annotation.Annotation;

/**
 * Handle MenuItem annotations
 */
public class MenuItemAnnotationHandler implements FieldAnnotationHandler {

	@Override
	public void handle(NodeContext nodeContext, Annotation annotation) {
		MenuItem menuItem = (MenuItem) annotation;
		FrameContext frameContext = nodeContext.getPageContext();
		frameContext.linkPage(menuItem.value(), menuItem.required(), menuItem.cssClass());
	}

}
