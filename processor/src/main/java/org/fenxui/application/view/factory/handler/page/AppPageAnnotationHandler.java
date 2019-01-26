package org.fenxui.application.view.factory.handler.page;

import java.lang.annotation.Annotation;
import javafx.scene.control.Label;
import org.fenxui.annotation.AppPage;
import org.fenxui.application.view.factory.ootb.FrameContext;

public class AppPageAnnotationHandler implements PageAnnotationHandler {

	@Override
	public void handle(FrameContext frameContext, Annotation annotation) {
		AppPage appPage = (AppPage) annotation;
		Label label = new Label(appPage.value());
		label.setId(appPage.cssClass() + "-title");
		frameContext.setPageCss(appPage.cssClass());
		frameContext.setTitle(label);
	}

}
