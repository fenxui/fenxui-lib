package org.fenxui.application.view.decorator;

import javafx.scene.layout.Region;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.factory.ootb.AppFactory;
import org.fenxui.core.exception.FenxuiInitializationException;

public class FactoryInvocation implements ComponentDecorator<Region> {
	private final AppFactory appFactory;

	public FactoryInvocation(AppFactory appFactory) {
		this.appFactory = appFactory;
	}

	@Override
	public Region decorate(FenxuiConfig fenxuiConfig) throws FenxuiInitializationException {
		return appFactory.makeApp(fenxuiConfig);
	}

	@Override
	public AppFactory getAppFactory() {
		return appFactory;
	}
}
