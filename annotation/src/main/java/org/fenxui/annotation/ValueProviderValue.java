package org.fenxui.annotation;

import java.lang.annotation.*;

@Repeatable(ValueProviderValues.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ValueProviderValue {
	/**
	 * The value saved
	 */
	String key();
	/**
	 * The value displayed 
	 */
	String value();
}
