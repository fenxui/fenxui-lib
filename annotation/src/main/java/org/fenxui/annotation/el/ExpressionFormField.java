package org.fenxui.annotation.el;

import org.fenxui.api.prototype.OOTBFieldPrototypes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Automatically set values of a field based on a formula evaluating fields
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExpressionFormField {
	/**
	 * Display name of field
	 */
	String label();

	/**
	 * Expression to evaluate
	 * ie: #{fieldA} + #{fieldB} + #{otherclass.field}
	 */
	String expression();

	/**
	 * unique name of {@link .org.fenxui.application.view.factory.ootb.form.FieldFactory}
	 * Several factories are provided OOTB.  Any additional or overriding factories, you will be required to register the FieldFactory in the {@link org.fenxui.application.view.factory.FactoryInitContext}
	 * Default factories are "text", "checkbox", "password", "select", "monetary", "percent"
	 */
	String factory() default OOTBFieldPrototypes.TEXT_FIELD;

	/**
	 * True if the field should expand horizontally with the form
	 */
	boolean dynamicWidth() default false;

	/**
	 * False if the field can be edited after formula-population
	 */
	boolean readOnly() default true;
}
