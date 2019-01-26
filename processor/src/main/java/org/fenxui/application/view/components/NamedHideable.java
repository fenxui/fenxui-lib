package org.fenxui.application.view.components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.paint.Paint;

public interface NamedHideable extends NamedControl {

	BooleanProperty visibleProperty();
	void setVisible(boolean empty);
	boolean isVisible();

	ObjectProperty<Paint> linkHintColorProperty();
	void votify(String message, boolean isValid);

}
