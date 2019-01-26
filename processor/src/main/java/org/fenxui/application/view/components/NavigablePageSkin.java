package org.fenxui.application.view.components;

import javafx.scene.control.SkinBase;
import javafx.scene.layout.BorderPane;
import org.fenxui.application.view.components.menu.NavigableMenu;
import org.fenxui.application.view.components.menu.PageLink;
import org.fenxui.application.view.components.menu.SideMenu;
import org.fenxui.application.view.components.menu.TabMenu;

import java.util.List;

public class NavigablePageSkin extends SkinBase<NavigablePage> {

	public <T extends NamedControl> NavigablePageSkin(NavigablePage control) {
		super(control);

		BorderPane borderPane = new BorderPane();
		FormContainer formContainer = new FormContainer();
		borderPane.setCenter(formContainer);
		NavigableMenu menu;
		switch (control.getOrientation()) {
			case VERTICAL:
				menu = new SideMenu(formContainer, control.getMenuCssClass());
				menu.minWidthProperty().set(control.getMinimumWidth());
				borderPane.setLeft(menu);
				break;
			case HORIZONTAL:
				menu = new TabMenu(formContainer, control.getMenuCssClass());
				menu.minHeightProperty().set(control.getMinimumHeight());
				borderPane.setTop(menu);
				break;
			default: //TODO
				menu = null;
		}
		List<PageLink> menuList = control.getMenuItems();
		if (menuList != null && menu != null) {
			menu.addLinks(menuList);
		}

		getChildren().add(borderPane);
	}
}
