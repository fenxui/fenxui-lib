package org.fenxui.application.el;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class VariableExtractor {
	private PatternBasedVariableExtractor currentBeanVariableExtractor = new PatternBasedVariableExtractor(Pattern.compile("(\\#\\{(([a-zA-Z0-9])*)\\})"));
	private PatternBasedVariableExtractor qualifiedVariableExtractor = new PatternBasedVariableExtractor(Pattern.compile("(\\#\\{(([a-zA-Z0-9])*\\.([a-zA-Z0-9])*)\\})"));

	private final Set<String> variables = new HashSet<>();
	private String evalExpression;

	public VariableExtractor(String expression) {
		evalExpression = expression;

		Stream.of(currentBeanVariableExtractor, qualifiedVariableExtractor).forEach(extractor -> {
			extractor.extractVariables(evalExpression);
			variables.addAll(extractor.getVariables());
			String newExpression = extractor.getEvalExpression();
			if (newExpression != null) {
				evalExpression = newExpression;
			}
		});
	}

	public String getEvalExpression() {
		return evalExpression;
	}

	public Set<String> getVariables() {
		return variables;
	}

	private class PatternBasedVariableExtractor {
		private final Pattern pattern;
		private final List<String> variables = new ArrayList<>();
		private String evalExpression;

		private PatternBasedVariableExtractor(Pattern pattern) {
			this.pattern = pattern;
		}

		private void extractVariables(String expression) {
			Matcher variableMatcher = pattern.matcher(expression);

			if (variableMatcher.find()) {
				String variableName = variableMatcher.group(2);
				evalExpression = expression.substring(0, variableMatcher.start()) + variableName + expression.substring(variableMatcher.end());
				variables.add(variableName);
				extractVariables(evalExpression);
			}
		}

		private String getEvalExpression() {
			return evalExpression;
		}

		private List<String> getVariables() {
			return variables;
		}

	}
}
