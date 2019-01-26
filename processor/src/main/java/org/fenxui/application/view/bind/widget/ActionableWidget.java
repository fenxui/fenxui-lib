package org.fenxui.application.view.bind.widget;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Common interface to allow common exposition of
 * setOnAction(EventHandler<ActionEvent>) found in many of the JavaFX widgets
 */
public interface ActionableWidget {

	void setOnAction(EventHandler<ActionEvent> value);

	EventHandler<ActionEvent> getOnAction();
}
