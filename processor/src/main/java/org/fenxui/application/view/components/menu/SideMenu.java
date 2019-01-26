package org.fenxui.application.view.components.menu;

import javafx.scene.control.Skin;
import org.fenxui.application.view.components.FormContainer;

public class SideMenu extends NavigableMenu {

	public SideMenu(FormContainer formContainer, String css) {
		super(formContainer);

		getStyleClass().add(css);
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new SideMenuSkin(this);
	}
}