package org.fenxui.application.view.components.menu;

import javafx.geometry.HPos;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.util.List;

public class TabMenuSkin extends SkinBase<TabMenu> {

	public TabMenuSkin(TabMenu tabMenu) {
		super(tabMenu);

		GridPane gridPane = new GridPane();
		List<PageLink> pageLinks = tabMenu.getPageLinks();
		for (int i = 0; i < pageLinks.size(); i++) {
			PageLink option = pageLinks.get(i);
			if (i == 0) {
				option.setSelectByDefault(true);
			}
			gridPane.addColumn(i, option);
			gridPane.getColumnConstraints().add(new ColumnConstraints(12, 25, 200, Priority.SOMETIMES, HPos.CENTER, true));
			GridPane.setHgrow(option, Priority.ALWAYS);
		}
		gridPane.getRowConstraints().add(new RowConstraints(12,35,65));
		getChildren().add(gridPane);
	}
}
