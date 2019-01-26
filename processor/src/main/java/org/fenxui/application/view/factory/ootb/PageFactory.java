package org.fenxui.application.view.factory.ootb;

import javafx.scene.layout.Region;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.core.exception.FenxuiInitializationException;

public interface PageFactory {

	Region makePage(Object applicationPage, FenxuiConfig fenxuiConfig, FrameContext frameContext) throws FenxuiInitializationException, FenxuiInitializationException;

}
