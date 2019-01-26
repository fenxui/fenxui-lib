package org.fenxui.application.el;

import javafx.beans.property.*;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.MapContext;

import java.util.HashMap;
import java.util.Map;

public class VariableContext {
	private final JexlEvaluable condition;
	private final Map<String, Property> propertyMap = new HashMap<>();
	private final IntegerProperty size = new SimpleIntegerProperty();

	public VariableContext(JexlEvaluable condition) {
		this.condition = condition;
	}

	public int getSize() {
		return size.get();
	}

	public IntegerProperty sizeProperty() {
		return size;
	}

	public void setSize(int size) {
		this.size.set(size);
	}

	public void eval() {
		condition.evaluate(createMapContext());
	}

	private JexlContext createMapContext() {
		JexlContext context = new MapContext();
		for (String key : propertyMap.keySet()) {
			context.set(key, propertyMap.get(key).getValue());//get current value of property
		}
		return context;
	}

	public void addVariable(String variableName, Property variableInstance) {
		propertyMap.put(variableName, variableInstance);
		variableInstance.addListener((observable, oldValue, newValue) -> {
			condition.evaluate(createMapContext());
		});
		size.set(propertyMap.size());
	}
}
