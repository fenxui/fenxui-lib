package org.fenxui.application.view.components.option;

import org.fenxui.api.factory.ActionFactory;
import org.fenxui.api.factory.IMethodOption;

import java.lang.reflect.Method;

public class MethodOption implements IMethodOption {
	private final Method method;
	private final Object pageObject;
	private ActionFactory actionFactory;
	private String label;
	private String cssClass;

	public MethodOption(Method method, Object pageObject) {
		this.method = method;
		this.pageObject = pageObject;
	}

	public Method getMethod() {
		return method;
	}

	public void setActionFactory(ActionFactory actionFactory) {
		this.actionFactory = actionFactory;
	}

	public ActionFactory getActionFactory() {
		return actionFactory;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public Object getPageObject() {
		return pageObject;
	}
}
