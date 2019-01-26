package org.fenxui.api.factory;

import javafx.scene.Node;
import org.fenxui.api.option.IFieldOption;

public interface FieldFactory<T extends IFieldOption> {
	Node create(T option);
}
