package org.fenxui.annotation;

import org.fenxui.api.prototype.OOTBActionPrototype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to register buttons to submit form, etc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FormAction {

	/**
	 * Display value
	 */
	String value();

	/**
	 * factory to use to display.
	 * By default will use "button"
	 */
	String factory() default OOTBActionPrototype.BUTTON_ACTION;

	/**
	 * CSS class used to style the widget
	 */
	String cssClass() default "button-raised";
}
