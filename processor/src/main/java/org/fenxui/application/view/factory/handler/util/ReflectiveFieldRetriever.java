package org.fenxui.application.view.factory.handler.util;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.fenxui.core.exception.FenxuiInitializationException;

import java.lang.reflect.Field;

public class ReflectiveFieldRetriever {
	private final Field field;

	public ReflectiveFieldRetriever(Field field) {
		this.field = field;
	}

	public Object getFieldValue(Object applicationPage) throws FenxuiInitializationException {
		try {
			return FieldUtils.readField(field, applicationPage, true);
		} catch (IllegalAccessException ex) {
			throw new FenxuiInitializationException(ex);
		}
	}
}
