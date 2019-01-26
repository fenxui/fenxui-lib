package org.fenxui.application.view.factory.handler;

import java.lang.reflect.Method;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import org.fenxui.application.view.action.ReflectiveActionEventHandler;
import org.fenxui.application.view.bind.widget.ActionableWidget;
import org.fenxui.application.view.factory.handler.util.ReflectiveMethodRetriever;
import org.fenxui.core.exception.FenxuiInitializationException;

public interface ActionHandler {

	default void setOnAction(Object source, Node widget, String action) throws FenxuiInitializationException {
		if (!"".equals(action)) {
			Method method = new ReflectiveMethodRetriever(source.getClass())
					.getMethod(action, ActionEvent.class);
			if (method != null) {
				if (widget instanceof ActionableWidget) {
					ActionableWidget actionableWidget = (ActionableWidget) widget;
					EventHandler<ActionEvent> handler = actionableWidget.getOnAction();
					actionableWidget.setOnAction(new ReflectiveActionEventHandler(method, source, handler));
				}
			}
		}
	}
}
