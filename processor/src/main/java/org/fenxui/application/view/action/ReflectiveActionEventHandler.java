package org.fenxui.application.view.action;

import java.lang.reflect.Method;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author ArnoldBe
 */
public class ReflectiveActionEventHandler extends ReflectiveEventHandler<ActionEvent> implements EventHandler<ActionEvent> {

	public ReflectiveActionEventHandler(Method method, Object pageModel, EventHandler<ActionEvent> eventHandler) {
		super(pageModel, method, eventHandler);
	}
}
