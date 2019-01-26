package org.fenxui.application.view.factory.handler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import javafx.scene.Node;
import org.fenxui.api.factory.ActionFactory;
import org.fenxui.api.factory.FieldFactory;
import org.fenxui.api.factory.ValidatorFactory;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.application.view.components.option.MethodOption;
import org.fenxui.application.view.factory.handler.page.PageContext;
import org.fenxui.core.exception.FenxuiInitializationException;

public class NodeContext {
	private Node node;
	private Field field;
	private final Object source;
	private FieldOption activeFieldOption;
	private MethodOption activeMethodOption;
	private final PageContext pageContext;
	private Map<String, FieldFactory> fieldFactories;
	private Map<String, ValidatorFactory> validatorFactories;
	private Map<String, ActionFactory> actionFactories;

	public NodeContext(Field field, Object source, PageContext pageContext, Map<String, FieldFactory> fieldFactories, Map<String, ValidatorFactory> validatorFactories) {
		this.field = field;
		this.source = source;
		this.activeFieldOption = new FieldOption(field.getName());
		this.pageContext = pageContext;
		this.fieldFactories = fieldFactories;
		this.validatorFactories = validatorFactories;
		pageContext.addFieldOption(activeFieldOption);
	}

	public NodeContext(Method method, Object source, PageContext pageContext, Map<String, ActionFactory> actionFactories) {
		this.source = source;
		this.activeMethodOption = new MethodOption(method, source);
		this.pageContext = pageContext;
		this.actionFactories = actionFactories;
		pageContext.addMethodOption(activeMethodOption);
	}

	public FieldOption getActiveFieldOption() {
		return activeFieldOption;
	}

	public MethodOption getActiveMethodOption() {
		return activeMethodOption;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public Node getNode() {
		return node;
	}

	public Field getField() {
		return field;
	}

	public Object getSource() {
		return source;
	}

	public PageContext getPageContext() {
		return pageContext;
	}

	public FieldFactory getFieldFactory(String name) throws FenxuiInitializationException {
		FieldFactory fieldFactory = fieldFactories.get(name);
		if (fieldFactory == null) {
			throw new FenxuiInitializationException("Unknown field type: " + name);
		}
		return fieldFactory;
	}

	public ValidatorFactory getValidatorFactory(String name) throws FenxuiInitializationException {
		ValidatorFactory validatorFactory = validatorFactories.get(name);
		if (validatorFactory == null) {
			throw new FenxuiInitializationException("Unknown validator type: " + name);
		}
		return validatorFactory;
	}

	public ActionFactory getActionFactory(String name) throws FenxuiInitializationException {
		ActionFactory actionFactory = actionFactories.get(name);
		if (actionFactory == null) {
			throw new FenxuiInitializationException("Unknown field type: " + name);
		}
		return actionFactory;
	}

}
