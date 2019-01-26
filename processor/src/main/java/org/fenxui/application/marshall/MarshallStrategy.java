package org.fenxui.application.marshall;

import javafx.beans.property.Property;
import javafx.scene.Node;
import org.fenxui.application.view.components.option.FieldOption;

public interface MarshallStrategy<P extends Property, N extends Node> {

	void execute(FieldOption<P> fieldOption, N node);
}
