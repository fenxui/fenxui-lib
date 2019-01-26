package org.fenxui.application.view.factory.handler.app;

import org.fenxui.annotation.app.Menu;
import org.fenxui.application.view.factory.handler.page.PageAnnotationHandler;
import org.fenxui.application.view.factory.ootb.FrameContext;

import java.lang.annotation.Annotation;

public class MenuAnnotationHandler implements PageAnnotationHandler {

	@Override
	public void handle(FrameContext frameContext, Annotation annotation){
		Menu menu = (Menu) annotation;
		frameContext.setMenuCssClass(menu.cssClass());
		frameContext.setMenuOrientation(menu.orientation());
		frameContext.setMenuMinWidth(menu.minimumWidth());
		frameContext.setMenuMinHeight(menu.minimumHeight());
	}
}
