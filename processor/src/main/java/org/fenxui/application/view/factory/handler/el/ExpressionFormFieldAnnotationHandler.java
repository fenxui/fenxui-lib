package org.fenxui.application.view.factory.handler.el;

import javafx.beans.property.IntegerProperty;
import org.fenxui.annotation.el.ExpressionFormField;
import org.fenxui.application.el.*;
import org.fenxui.application.marshall.IntegerMarshallStrategy;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.application.view.factory.handler.FieldAnnotationHandler;
import org.fenxui.application.view.factory.handler.NodeContext;
import org.fenxui.application.view.factory.handler.page.PageContext;
import org.fenxui.core.exception.FenxuiInitializationException;

import java.lang.annotation.Annotation;

public class ExpressionFormFieldAnnotationHandler implements FieldAnnotationHandler {

	@Override
	public void handle(NodeContext fieldContext, Annotation annotation) throws FenxuiInitializationException {
		ExpressionFormField formField = (ExpressionFormField) annotation;

		FieldOption fieldOption = fieldContext.getActiveFieldOption();
		PageContext pageContext = fieldContext.getPageContext();

		fieldOption.setName(formField.label());
		fieldOption.setFieldFactory(fieldContext.getFieldFactory(formField.factory()));
		fieldOption.setBindFieldToPaneWidth(formField.dynamicWidth());
		fieldOption.setReadOnly(formField.readOnly());
		if (fieldOption.getValue() instanceof IntegerProperty) {
			fieldOption.setMarshallStrategy(new IntegerMarshallStrategy());
		}

		VariableExtractor extractor = new VariableExtractor(formField.expression());
		EvaluableExpression condition = new EvaluableExpression(extractor.getEvalExpression());
		//bind the field to the result
		fieldOption.getValue().bind(condition.resultProperty());

		ExpressionInitUtil.INSTANCE.init(extractor, condition, pageContext, fieldOption);
	}
}
