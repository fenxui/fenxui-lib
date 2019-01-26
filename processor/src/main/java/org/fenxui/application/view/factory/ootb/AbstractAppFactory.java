package org.fenxui.application.view.factory.ootb;

import javafx.scene.layout.Region;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.FenxuiViewModel;
import org.fenxui.core.exception.FenxuiInitializationException;

public abstract class AbstractAppFactory implements AppFactory {

	private final PageFactory pageFactory;
	private FenxuiViewModel fenxuiViewModel;

	public AbstractAppFactory(PageFactory pageFactory) {
		this.pageFactory = pageFactory;
	}

	public Region makeApp(FenxuiConfig fenxuiConfig) throws FenxuiInitializationException {
		AppConstruction appConstruction = new AppConstruction();
		Region contentPane = pageFactory.makePage(fenxuiViewModel, fenxuiConfig, appConstruction);
		appConstruction.postProcess();

		return contentPane;
	}

	@Override
	public void setViewModel(FenxuiViewModel fenxuiViewModel) {
		this.fenxuiViewModel = fenxuiViewModel;
	}
	@Override
	public FenxuiViewModel getViewModel() {
		return fenxuiViewModel;
	}

	public PageFactory getPageFactory() {
		return pageFactory;
	}
}