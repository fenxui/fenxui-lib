package org.fenxui.application.el;

import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.application.view.factory.handler.VariableContextFieldPostProcessor;
import org.fenxui.application.view.factory.handler.page.PageContext;
import org.fenxui.application.view.factory.ootb.FrameContext;

import java.util.Set;

public enum ExpressionInitUtil {
	INSTANCE;

	public void init(VariableExtractor extractor, JexlEvaluable evaluable, PageContext pageContext, FieldOption fieldOption) {
		VariableContext variableContext = new VariableContext(evaluable);
		Set<String> variableNames = extractor.getVariables();
		variableContext.sizeProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.intValue() == variableNames.size()) {
				variableContext.eval();//evaluate when all variables are resolved
			}
		});
		FrameContext appConstruction = pageContext.getFrameContext();
		Class processingClass = pageContext.getApplicationPage().getClass();

		for (String variableName : variableNames) {
			if (variableName.contains(".")) {
				String[] parts = variableName.split("\\.");
				String className = parts[0];
				String fieldName = parts[1];
				appConstruction.addPageScopedFieldPostProcessor(processingClass, className, new VariableContextFieldPostProcessor(fieldName, variableName, variableContext));
			} else {
				pageContext.addPostProcessor(fieldOption.getFieldName(), variableName, new VariableContextFieldPostProcessor(variableName, variableName, variableContext));
			}
		}
	}
}
