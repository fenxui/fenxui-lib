package org.fenxui.application.el;

import org.junit.Test;

import java.util.Set;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class VariableExtractorTest {

	@Test
	public void extractVariablesSingleReference() {
		String expression = "#{enableNotifications} eq 'true'";
		VariableExtractor extractor = new VariableExtractor(expression);
		Set<String> variables = extractor.getVariables();
		assertThat(variables, containsInAnyOrder("enableNotifications"));
		String cleanExpression = extractor.getEvalExpression();
		assertThat(cleanExpression, is( "enableNotifications eq 'true'"));
	}

	@Test
	public void extractVariablesMultiReference() {
		String expression = "#{enableNotifications} eq 'true' || #{enableNotifications} eq 'false'";
		VariableExtractor extractor = new VariableExtractor(expression);
		Set<String> variables = extractor.getVariables();
		assertThat(variables, containsInAnyOrder("enableNotifications"));
		String cleanExpression = extractor.getEvalExpression();
		assertThat(cleanExpression, is( "enableNotifications eq 'true' || enableNotifications eq 'false'"));
	}

	@Test
	public void extractMultipleVariables() {
		String expression = "#{enableNotifications} eq 'true' || #{anotherVariable} eq 'false'";
		VariableExtractor extractor = new VariableExtractor(expression);
		Set<String> variables = extractor.getVariables();
		assertThat(variables, containsInAnyOrder("enableNotifications", "anotherVariable"));
		String cleanExpression = extractor.getEvalExpression();
		assertThat(cleanExpression, is( "enableNotifications eq 'true' || anotherVariable eq 'false'"));
	}

	@Test
	public void extractQualifiedVariables() {
		String expression = "#{testClass.enableNotifications} eq 'true'";
		VariableExtractor extractor = new VariableExtractor(expression);
		Set<String> variables = extractor.getVariables();
		assertThat(variables, containsInAnyOrder("testClass.enableNotifications"));
		String cleanExpression = extractor.getEvalExpression();
		assertThat(cleanExpression, is( "testClass.enableNotifications eq 'true'"));
	}



}
