package org.fenxui.application.view.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class FormContainer extends StackPane {
	private final Map<String, NamedHideable> pages = new HashMap<>();
	private String selected;

	public void accept(NamedHideable page) {
		page.setVisible(getChildren().isEmpty());//select first
		if (page.isVisible()) {
			selected = page.getName();
		}
		pages.put(page.getName(), page);
		getChildren().add((Node) page);
	}

	public void select(String name) {
		pages.get(selected).setVisible(false);
		pages.get(name).setVisible(true);
		selected = name;
	}
}
