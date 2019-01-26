package org.fenxui.application.view.components.menu;

import java.util.List;
import javafx.geometry.VPos;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class SideMenuSkin extends SkinBase<NavigableMenu> {

	public SideMenuSkin(SideMenu sideMenu) {
		super(sideMenu);

		GridPane gridPane = new GridPane();
		List<PageLink> pageLinks = sideMenu.getPageLinks();
		for (int i = 0; i < pageLinks.size(); i++) {
			PageLink option = pageLinks.get(i);
			if (i == 0) {
				option.setSelectByDefault(true);
			}
			gridPane.addRow(i, option);
			gridPane.getRowConstraints().add(new RowConstraints(12, 25, 45, Priority.SOMETIMES, VPos.CENTER, true));
			GridPane.setHgrow(option, Priority.ALWAYS);
		}

		getChildren().add(gridPane);
	}

}
