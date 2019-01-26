package org.fenxui.annotation;

import java.lang.annotation.*;

@Repeatable(Validators.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Validator {
	String type();
	String message();
	/**
	 * Expression to evaluate.
	 * When true, validation is applied.
	 * Encode variables as ${fieldName} or ${className.fieldName}
	 */
	String evalExpression() default "";
}
