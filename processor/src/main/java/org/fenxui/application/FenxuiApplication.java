package org.fenxui.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.factory.FenxuiFactory;
import org.fenxui.application.view.prototype.FenxuiPrototype;

/**
 * Main class. Extend me.
 */
public abstract class FenxuiApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FenxuiPrototype prototype = getFenxuiPrototype();
		FenxuiFactory fenxuiFactory = prototype.getFenxuiFactory(getFenxuiConfig());
		Scene scene = fenxuiFactory.makeScene(primaryStage);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public abstract FenxuiConfig getFenxuiConfig();
	public abstract FenxuiPrototype getFenxuiPrototype();
}
