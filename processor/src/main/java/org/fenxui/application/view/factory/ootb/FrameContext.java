package org.fenxui.application.view.factory.ootb;

import javafx.scene.control.Label;
import org.fenxui.application.view.factory.handler.FieldPostProcessor;
import org.fenxui.application.view.factory.handler.page.PageContext;
import org.fenxui.api.option.layout.Orientation;

public interface FrameContext {
	void addPageContext(String name, PageContext pageContext);
	void addPageScopedFieldPostProcessor(Class dependentClass, String requiredClassName, FieldPostProcessor fieldPostProcessor);

	void setMenuCssClass(String cssClass);

	void setMenuOrientation(Orientation orientation);

	void linkPage(String value, boolean required, String cssClass);

	void setTitle(Label label);

	void setPageCss(String cssClass);

	void setMenuMinWidth(int minimumWidth);

	void setMenuMinHeight(int minimumHeight);

}
