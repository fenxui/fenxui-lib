package org.fenxui.application.el;

import javafx.beans.property.*;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class EvaluableExpressionTest {

	@Test
	public void addWithStringProperties() {
		String textExpression = "fieldA + fieldB";
		TestClass testClass = new TestClass();
		// populate the context
		EvaluableExpression<String> expression = new EvaluableExpression<>(textExpression);
		VariableContext variableContext = new VariableContext(expression);
		variableContext.addVariable("fieldA", testClass.stringFieldA);
		variableContext.addVariable("fieldB", testClass.stringFieldB);
		variableContext.eval();

		{
			String result = expression.getResult();
			assertThat(result, is("00"));
		}

		testClass.stringFieldA.set("1");
		{
			String result = expression.getResult();
			assertThat(result, is("10"));
		}
	}

	@Test
	public void addWithIntProperties() {
		String textExpression = "fieldA + fieldB";
		TestClass testClass = new TestClass();
		// populate the context
		EvaluableExpression<Integer> expression = new EvaluableExpression<>(textExpression);
		VariableContext variableContext = new VariableContext(expression);
		variableContext.addVariable("fieldA", testClass.intFieldA);
		variableContext.addVariable("fieldB", testClass.intFieldB);
		variableContext.eval();

		{
			Integer result = expression.getResult();
			assertThat(result, is(0));
		}

		testClass.intFieldA.set(1);
		{
			Integer result = expression.getResult();
			assertThat(result, is(1));
		}
	}

	@Test
	public void addWithDoubleProperties() {
		String textExpression = "fieldA + fieldB";
		TestClass testClass = new TestClass();
		// populate the context
		EvaluableExpression<Double> expression = new EvaluableExpression<>(textExpression);
		VariableContext variableContext = new VariableContext(expression);
		variableContext.addVariable("fieldA", testClass.doubleFieldA);
		variableContext.addVariable("fieldB", testClass.doubleFieldB);
		variableContext.eval();

		{
			Double result = expression.getResult();
			assertThat(result, is(0.0));
		}

		testClass.doubleFieldA.set(1.2);
		testClass.doubleFieldB.set(6.7);
		{
			Double result = expression.getResult();
			assertThat(result, is(7.9));
		}
	}


	@Test
	public void divideWithIntProperties() {
		String textExpression = "fieldA / fieldB";
		TestClass testClass = new TestClass();
		// populate the context
		EvaluableExpression<Integer> expression = new EvaluableExpression<>(textExpression);
		VariableContext variableContext = new VariableContext(expression);
		variableContext.addVariable("fieldA", testClass.intFieldA);
		variableContext.addVariable("fieldB", testClass.intFieldB);
		testClass.intFieldB.set(1);
		variableContext.eval();

		{
			Integer result = expression.getResult();
			assertThat(result, is(0));
		}

		testClass.intFieldA.set(1);
		{
			Integer result = expression.getResult();
			assertThat(result, is(1));
		}

		testClass.intFieldB.set(2);
		{
			Integer result = expression.getResult();
			assertThat(result, is(0));//integer division
		}
	}

	@Test
	public void divideWithDoubleProperties() {
		String textExpression = "fieldA / fieldB";
		TestClass testClass = new TestClass();
		// populate the context
		EvaluableExpression<Double> expression = new EvaluableExpression<>(textExpression);
		VariableContext variableContext = new VariableContext(expression);
		variableContext.addVariable("fieldA", testClass.doubleFieldA);
		variableContext.addVariable("fieldB", testClass.doubleFieldB);
		testClass.doubleFieldB.set(1);
		variableContext.eval();

		{
			Double result = expression.getResult();
			assertThat(result, is(0.0));
		}

		testClass.doubleFieldA.set(1);
		{
			Double result = expression.getResult();
			assertThat(result, is(1.0));
		}

		testClass.doubleFieldB.set(2);
		{
			Double result = expression.getResult();
			assertThat(result, is(0.5));//double division
		}

		String textExpression2 = "fieldA / (fieldB * fieldB)";//fieldA/fieldB^2
		EvaluableExpression<Double> expression2 = new EvaluableExpression<>(textExpression2);
		VariableContext variableContext2 = new VariableContext(expression2);
		variableContext2.addVariable("fieldA", testClass.doubleFieldA);
		variableContext2.addVariable("fieldB", testClass.doubleFieldB);
		variableContext2.eval();
		{
			Double result = expression2.getResult();
			assertThat(result, is(0.25));
		}
	}

	private class TestClass {
		StringProperty stringFieldA = new SimpleStringProperty("0");
		StringProperty stringFieldB = new SimpleStringProperty("0");
		IntegerProperty intFieldA = new SimpleIntegerProperty(0);
		IntegerProperty intFieldB = new SimpleIntegerProperty(0);
		DoubleProperty doubleFieldA = new SimpleDoubleProperty(0);
		DoubleProperty doubleFieldB = new SimpleDoubleProperty(0);

	}



}
