package org.fenxui.application.el;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EvaluableConditionTest {

	@Test
	public void eval() {
		String expression = "enableNotifications eq 'true'";
		TestClass testClass = new TestClass();

		// populate the context
		EvaluableCondition condition = new EvaluableCondition(expression);
		VariableContext variableContext = new VariableContext(condition);
		variableContext.addVariable("enableNotifications", testClass.enableNotifications);
		variableContext.eval();

		{
			boolean result = condition.stateProperty().get();
			assertFalse(result);
		}

		testClass.enableNotifications.set("true");
		{
			boolean result = condition.stateProperty().get();
			assertTrue(result);
		}
	}

	@Test
	public void evalQualified() {
		String expression = "testClass.enableNotifications eq 'true'";
		TestClass testClass = new TestClass();

		// populate the context
		EvaluableCondition condition = new EvaluableCondition(expression);
		VariableContext variableContext = new VariableContext(condition);
		variableContext.addVariable("testClass.enableNotifications", testClass.enableNotifications);
		variableContext.eval();

		{
			boolean result = condition.stateProperty().get();
			assertFalse(result);
		}

		testClass.enableNotifications.set("true");
		{
			boolean result = condition.stateProperty().get();
			assertTrue(result);
		}
	}

	private class TestClass {
		StringProperty enableNotifications = new SimpleStringProperty("false");
	}
}
