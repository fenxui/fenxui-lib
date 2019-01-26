package org.fenxui.application.el;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;

public class EvaluableExpression<T extends Object> implements JexlEvaluable {
	private final ObjectProperty<T> result = new SimpleObjectProperty<>();
	private final String expression;

	public EvaluableExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public void evaluate(JexlContext context) {
		JexlEngine jexl = new JexlBuilder().create();
		JexlExpression e = jexl.createExpression(expression);
		T result = (T) e.evaluate(context);
		this.result.set(result);
	}

	public T getResult() {
		return result.get();
	}

	public ObjectProperty<T> resultProperty() {
		return result;
	}

	public void setResult(T result) {
		this.result.set(result);
	}
}
