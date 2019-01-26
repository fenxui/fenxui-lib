package org.fenxui.application.view.factory.ootb;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

import javafx.scene.layout.Region;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.components.ContentPane;
import org.fenxui.application.view.components.NavigablePage;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.application.view.factory.AbstractFactoryInitContext;
import org.fenxui.application.view.components.option.MethodOption;
import org.fenxui.application.view.factory.handler.page.PageAnnotationHandler;
import org.fenxui.application.view.factory.handler.page.PageContext;
import org.fenxui.application.view.factory.PageContentProcessor;
import org.fenxui.core.exception.FenxuiInitializationException;

/**
 * Lays out page as a grid within a vbox vbox: title, grid grid: Row 1....n form
 * elements 1..n (A form element is typically a value and a field, but may also
 * include a button that populates the field) Row n+1 form action buttons row
 */
public class AbstractPageFactory implements PageFactory {

	private final PageContentProcessor pageContentProcessor;
	private final Map<Class, PageAnnotationHandler> pageAnnotationHandlers;

	public AbstractPageFactory(PageContentProcessor pageContentProcessor, AbstractFactoryInitContext factoryInitContext) {
		this.pageContentProcessor = pageContentProcessor;
		this.pageAnnotationHandlers = factoryInitContext.getPageAnnotationHandlers();
		pageContentProcessor.setPageFactory(this);
	}

	@Override
	public Region makePage(Object applicationPage, FenxuiConfig fenxuiConfig, FrameContext frameContext) throws FenxuiInitializationException {

		PageContext pageContext = new PageContext(applicationPage, frameContext);
		for (Annotation annotation : applicationPage.getClass().getAnnotations()) {
			PageAnnotationHandler handler = pageAnnotationHandlers.get(annotation.annotationType());
			if (handler != null) {
				handler.handle(pageContext, annotation);
			}
		}

		pageContentProcessor.processPageContent(pageContext, fenxuiConfig);

		Region region;
		if (pageContext.getMenuOrientation() == null) {
			region = new ContentPane();
			List<FieldOption> fieldOptions = pageContext.getFieldOptions();
			((ContentPane) region).addFields(fieldOptions);
			List<MethodOption> methodOptions = pageContext.getMethodOptions();
			((ContentPane) region).addActionWidgets(methodOptions);
		} else {
			region = new NavigablePage(pageContext.getMenuItems(), pageContext.getMenuOrientation(), pageContext.getMenuCssClass(), pageContext.getMenuMinWidth(), pageContext.getMenuMinHeight());
		}
		region.getStyleClass().setAll(pageContext.getPageCss());
		//		contentPane.getChildren().add(pageContext.getTitle());

//		contentPane.getChildren().add(gridPane);

//		HBox hbBtn = formActionFactory.makeFormActionRow(applicationPage);
//		contentPane.getChildren().add(hbBtn);

//		contentPane.getChildren().add(1, new Label());//space after title
//		contentPane.getChildren().add(4, new Label());//space above form submit button
//		contentPane.setSpacing(10);
		return region;
	}

}
