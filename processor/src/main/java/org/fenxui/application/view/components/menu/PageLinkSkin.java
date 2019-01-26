package org.fenxui.application.view.components.menu;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import org.fenxui.application.view.components.NamedHideable;

public class PageLinkSkin extends SkinBase<PageLink> {

	private static final PseudoClass ARMED_PSEUDOCLASS_STATE = PseudoClass.getPseudoClass("armed");

	public PageLinkSkin(PageLink configSectionLink, boolean selectByDefault) {
		super(configSectionLink);
		NamedHideable page = configSectionLink.getPage();

		BorderPane borderPane = new BorderPane();
		Rectangle bottom = new Rectangle();
		bottom.setHeight(configSectionLink.requiredProperty().get() ? 2 : .5);
		bottom.widthProperty().bind(borderPane.widthProperty());
		bottom.fillProperty().bind(page.linkHintColorProperty());
		page.linkHintColorProperty().addListener(new ChangeListener<Paint>() {
			@Override
			public void changed(ObservableValue<? extends Paint> observable, Paint oldValue, Paint newValue) {
				bottom.setHeight(3);
			}
		});
		borderPane.setBottom(bottom);

		pseudoClassStateChanged(ARMED_PSEUDOCLASS_STATE, selectByDefault);
		configSectionLink.selectedProperty().addListener((observable, oldValue, newValue) -> {
			pseudoClassStateChanged(ARMED_PSEUDOCLASS_STATE, newValue);
		});

		StackPane stackPane = new StackPane();
		Label label = new Label(configSectionLink.getName());
		label.setAlignment(Pos.CENTER_LEFT);
		stackPane.getChildren().add(label);
		borderPane.setCenter(stackPane);

		getChildren().addAll(borderPane);
	}
}
