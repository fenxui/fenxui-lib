package org.fenxui.application.view.components.menu;

import javafx.scene.control.Control;

import java.util.ArrayList;
import java.util.List;
import org.fenxui.application.view.components.FormContainer;
import org.fenxui.application.view.components.NamedHideable;

public class NavigableMenu extends Control {
	private final FormContainer formContainer;
	private final List<PageLink> links = new ArrayList<>();

	public NavigableMenu(FormContainer formContainer) {
		this.formContainer = formContainer;
	}

	public void addLinks(List<PageLink> links) {
		if (links != null) {
			links.stream().forEach(link -> {
				this.links.add(link);
				addOption(link);
			});
		}
	}

	private void addOption(PageLink option) {
		NamedHideable page = option.getPage();
		page.setName(option.getName());//link menu item to page
		option.selectedProperty().bind(page.visibleProperty());

		option.setOnMouseClicked(event -> {
			formContainer.select(option.getName());
		});
		formContainer.accept(page);
	}

	public List<PageLink> getPageLinks() {
		return links;
	}

}
