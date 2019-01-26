package org.fenxui.application.view.components;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;

public class LayoutGridPane  extends Control {
	private final DoubleProperty minValueWidth = new SimpleDoubleProperty();
	private final DoubleProperty prefValueWidth = new SimpleDoubleProperty();
	private final DoubleProperty maxValueWidth = new SimpleDoubleProperty();

	private final List<RowOptions> normalRows = new ArrayList<>();
	private final List<RowOptions> additionalRows = new ArrayList<>();

	public void addNormalRow(boolean expanding, Label label, Node node) {
		normalRows.add(new RowOptions(expanding, label, node));
	}

	public void addAdditionalRow(boolean expanding, Label label, Node node) {
		additionalRows.add(new RowOptions(expanding, label, node));
	}

	public List<RowOptions> getNormalRows() {
		return normalRows;
	}

	public List<RowOptions> getAdditionalRows() {
		return additionalRows;
	}

	public double getMinValueWidth() {
		return minValueWidth.get();
	}

	public DoubleProperty minValueWidthProperty() {
		return minValueWidth;
	}

	public void setMinValueWidth(double minValueWidth) {
		this.minValueWidth.set(minValueWidth);
	}

	public double getPrefValueWidth() {
		return prefValueWidth.get();
	}

	public DoubleProperty prefValueWidthProperty() {
		return prefValueWidth;
	}

	public void setPrefValueWidth(double prefValueWidth) {
		this.prefValueWidth.set(prefValueWidth);
	}

	public double getMaxValueWidth() {
		return maxValueWidth.get();
	}

	public DoubleProperty maxValueWidthProperty() {
		return maxValueWidth;
	}

	public void setMaxValueWidth(double maxValueWidth) {
		this.maxValueWidth.set(maxValueWidth);
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new LayoutGridPaneSkin(this);
	}

	public class RowOptions {
		final boolean expanding;
		final Label label;
		final Node node;
		int rowIndex;

		private RowOptions(boolean expanding, Label label, Node node) {
			this.expanding = expanding;
			this.label = label;
			this.node = node;
		}
	}

}
