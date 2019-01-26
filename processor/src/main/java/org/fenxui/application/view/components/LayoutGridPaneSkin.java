package org.fenxui.application.view.components;

import java.util.List;
import javafx.beans.binding.When;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Paint;
import org.fenxui.application.view.bind.widget.UniqueValidatableControl;
import org.fenxui.application.view.components.option.ColorOptions;

public class LayoutGridPaneSkin extends SkinBase<LayoutGridPane> {

	public LayoutGridPaneSkin(LayoutGridPane control) {
		super(control);
		
		List<LayoutGridPane.RowOptions> normalRows = control.getNormalRows();
		List<LayoutGridPane.RowOptions> additionalRows = control.getAdditionalRows();
		int i;

		GridPane gridPane = new GridPane();
		ColumnConstraints valueFieldConstraints =  new ColumnConstraints();
		control.minValueWidthProperty().bind(valueFieldConstraints.minWidthProperty());
		control.prefValueWidthProperty().bind(valueFieldConstraints.prefWidthProperty());
		control.maxValueWidthProperty().bind(valueFieldConstraints.maxWidthProperty());

		for (i = 0; i < normalRows.size(); i++ ) {
			LayoutGridPane.RowOptions rowOption = normalRows.get(i);
			gridPane.addRow(i, rowOption.label, rowOption.node);
			double multiplier = 1;

			if (rowOption.node instanceof HeightSizedNode) {
				multiplier = ((HeightSizedNode) rowOption.node).getMultiplier();
			}
			RowConstraints rowConstraints = new RowConstraints(20 * multiplier, 35*multiplier, 50*multiplier, Priority.SOMETIMES, VPos.CENTER, false);
			gridPane.getRowConstraints().add(rowConstraints);
			GridPane.setHgrow(rowOption.node, Priority.ALWAYS);
		}

		gridPane.getColumnConstraints().addAll(new ColumnConstraints(80,165,180, Priority.SOMETIMES, HPos.LEFT, false), valueFieldConstraints);
		if (!additionalRows.isEmpty()) {
			Label invisible = new Label();
			invisible.setVisible(false);
			BooleanProperty showAdvanced = new SimpleBooleanProperty();
			Label advancedLink = new Label();
			advancedLink.textProperty().bind(new When(showAdvanced).then("Hide").otherwise("Advanced"));
			advancedLink.setTextFill(Paint.valueOf(ColorOptions.BLUEISH));
			gridPane.addRow(i++, invisible, advancedLink);
			gridPane.getRowConstraints().add(new RowConstraints(20, 35, 50, Priority.SOMETIMES, VPos.CENTER, false));

			advancedLink.setOnMouseClicked(event -> {
				showAdvanced.set(!showAdvanced.get());//toggle
				if (showAdvanced.get()) {
					for (LayoutGridPane.RowOptions rowOption : additionalRows) {
						gridPane.addRow(rowOption.rowIndex, rowOption.label, rowOption.node);
						gridPane.getRowConstraints().add(new RowConstraints(20, 35, 50, Priority.SOMETIMES, VPos.CENTER, false));
						GridPane.setHgrow(rowOption.node, Priority.ALWAYS);
					}
				} else {
					for (LayoutGridPane.RowOptions rowOption : additionalRows) {
						List<RowConstraints> rowConstraints = gridPane.getRowConstraints();
						gridPane.getChildren().removeAll(rowOption.label, rowOption.node);
						rowConstraints.remove(rowConstraints.size()-1);//remove last
					}
				}
			});
		}

		for (LayoutGridPane.RowOptions rowOptions : additionalRows) {
			rowOptions.rowIndex = i++;//prime indexes
		}

		getChildren().add(gridPane);
	}

}
