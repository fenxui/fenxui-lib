package org.fenxui.application.view.factory.handler.util;

import org.fenxui.core.exception.FenxuiInitializationException;

import java.lang.reflect.Method;

public class ReflectiveMethodRetriever {
	private final Class klass;

	public ReflectiveMethodRetriever(Class klass) {
		this.klass = klass;
	}

	public Method getMethod(String methodName, Class...args) throws FenxuiInitializationException {
		try {
			return klass.getMethod(methodName, args);
		} catch (NoSuchMethodException ex) {
			throw new FenxuiInitializationException(ex);
		}
	}
}
