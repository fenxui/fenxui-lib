package org.fenxui.application.view.components.menu;

import javafx.scene.control.Skin;
import org.fenxui.application.view.components.FormContainer;

public class TabMenu extends NavigableMenu {

	public TabMenu(FormContainer formContainer, String css) {
		super(formContainer);
		getStyleClass().add(css);
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new TabMenuSkin(this);
	}

}
