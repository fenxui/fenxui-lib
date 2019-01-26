package org.fenxui.application.view.prototype;

import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.factory.FenxuiFactory;

/**
 * Specify Decorators for basic App construction
 */
public interface FenxuiPrototype {

	FenxuiFactory getFenxuiFactory(FenxuiConfig fenxuiConfig);
}
