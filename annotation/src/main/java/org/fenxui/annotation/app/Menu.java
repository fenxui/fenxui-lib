package org.fenxui.annotation.app;

import org.fenxui.api.option.layout.Orientation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Menu {

	String cssClass() default "menupage";
	Orientation orientation() default Orientation.VERTICAL;
	int minimumWidth() default 185;
	int minimumHeight() default 23;
}
