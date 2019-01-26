package org.fenxui.application.view.factory.ootb;

import javafx.scene.layout.Region;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.FenxuiViewModel;
import org.fenxui.core.exception.FenxuiInitializationException;

public interface AppFactory {

	/**
	 * @param fenxuiConfig: any global configurations
	 * @return the content Region
	 * @throws FenxuiInitializationException
	 */
	Region makeApp(FenxuiConfig fenxuiConfig) throws FenxuiInitializationException;

	/**
	 * @return the task to be run when the window is closed
	 */
	Runnable getOnCloseAction();

	/**
	 * Sets the annotated view-model on the factory
	 * @param fenxuiViewModel
	 */
	void setViewModel(FenxuiViewModel fenxuiViewModel);

	/**
	 *
	 * @return the annotated view-model
	 */
	FenxuiViewModel getViewModel();
}
