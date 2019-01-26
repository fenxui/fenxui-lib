package org.fenxui.application.view.decorator;

import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.factory.ootb.AppFactory;
import org.fenxui.core.exception.FenxuiInitializationException;

public interface ComponentDecorator<T> {

	T decorate(FenxuiConfig fenxuiConfig) throws FenxuiInitializationException, FenxuiInitializationException;
	AppFactory getAppFactory();
}
