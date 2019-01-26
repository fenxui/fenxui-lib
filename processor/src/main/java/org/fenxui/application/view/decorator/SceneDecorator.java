package org.fenxui.application.view.decorator;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Screen;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.core.exception.FenxuiInitializationException;

/**
 * Scene org.fenxui.api.option.layout
 */
public class SceneDecorator extends AbstractDecorator<Region> implements ComponentDecorator<Scene> {
	public SceneDecorator(ComponentDecorator decorator) {
		super(decorator);
	}

	@Override
	public Scene decorate(FenxuiConfig fenxuiConfig) throws FenxuiInitializationException {
		Region region = decorator.decorate(fenxuiConfig);
		
		double width = fenxuiConfig.getInitialSceneWidth();
		double height = fenxuiConfig.getInitialSceneHeight();

		if (width < 0) {
			Rectangle2D bounds = Screen.getScreens().get(0).getBounds();
			width = bounds.getWidth() / 2.5;
		}
		if (height < 0) {
			Rectangle2D bounds = Screen.getScreens().get(0).getBounds();
			height = bounds.getHeight() / 1.35;
		}
		
		Scene scene = new Scene(region, width, height);
		getStage().setTitle(fenxuiConfig.getTitle());

		scene.getStylesheets().addAll(fenxuiConfig.getStylesheets());
		return scene;
	}

}
