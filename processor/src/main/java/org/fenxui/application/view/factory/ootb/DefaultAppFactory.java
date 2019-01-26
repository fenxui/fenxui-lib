package org.fenxui.application.view.factory.ootb;

import org.fenxui.application.view.factory.AbstractFactoryInitContext;

public class DefaultAppFactory extends AbstractAppFactory implements AppFactory {
	private final Runnable onCloseAction;

	public DefaultAppFactory(Runnable onCloseAction, AbstractFactoryInitContext initContext) {
		super(new DefaultPageFactory(initContext));
		this.onCloseAction = onCloseAction;
	}

	@Override
	public Runnable getOnCloseAction() {
		return onCloseAction;
	}

}
