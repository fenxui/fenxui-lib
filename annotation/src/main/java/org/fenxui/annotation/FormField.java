package org.fenxui.annotation;

import org.fenxui.api.option.layout.LayoutSection;
import org.fenxui.api.prototype.OOTBFieldPrototypes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FormField {
	/**
	 * Display name of field 
	 */
	String label();
	 /**
	 * unique name of {@link .org.fenxui.application.view.factory.ootb.form.FieldFactory}
	 * Several factories are provided OOTB.  Any additional or overriding factories, you will be required to register the FieldFactory in the {@link org.fenxui.application.view.factory.FactoryInitContext}
	 * Default factories are "text", "checkbox", "password", "select", "monetary", "percent"
	 */
	String factory() default OOTBFieldPrototypes.TEXT_FIELD;
	/**
	 * additional: section for additional fields (displayed when clicking 'advanced') 
	 */
	LayoutSection section() default LayoutSection.DEFAULT;
	/**
	 * True if the field should expand horizontally with the form
	 */
	boolean dynamicWidth() default false;
	/**
	 * False if the field can be edited
	 */
	boolean readOnly() default false;
}
