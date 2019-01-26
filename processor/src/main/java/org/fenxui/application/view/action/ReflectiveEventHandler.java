package org.fenxui.application.view.action;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javafx.event.Event;
import javafx.event.EventHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class ReflectiveEventHandler<T extends Event> {
	private static final Logger logger = LogManager.getLogger(ReflectiveEventHandler.class);

	protected final Method method;
	protected final Object pageModel;
	protected final EventHandler<T> eventHandler;

	public ReflectiveEventHandler(Object pageModel, Method method, EventHandler<T> eventHandler) {
		this.pageModel = pageModel;
		this.method = method;
		this.eventHandler = eventHandler;
	}

	public void handle(T event) {
		if (eventHandler != null) {//decorate event-handlers
			eventHandler.handle(event);
		}
		try {
			method.invoke(pageModel, event);
		} catch (IllegalAccessException|InvocationTargetException|IllegalArgumentException ex) {
			logger.error("Trouble handling event", ex);
		}
	}
}
