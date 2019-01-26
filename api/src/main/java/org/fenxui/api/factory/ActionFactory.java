package org.fenxui.api.factory;

import javafx.scene.Node;

public interface ActionFactory<T extends IMethodOption> {
	Node create(T option);
}
