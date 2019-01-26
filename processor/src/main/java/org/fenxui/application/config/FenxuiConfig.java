package org.fenxui.application.config;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.layout.ColumnConstraints;

public class FenxuiConfig {
	private String title;
	private Pos alignent = Pos.CENTER;
	private int hgap = 10;
	private int vgap = 10;
	private double initialSceneWidth = -1;
	private double initialSceneHeight = -1;
	private List<ColumnConstraints> colConstraints = new FormPrototype().getFormLayout();
	private Insets padding = new Insets(25, 25, 25, 25);
	private List<String> stylesheets = new ArrayList<>();

	public Pos getAlignent() {
		return alignent;
	}

	public void setAlignent(Pos alignent) {
		this.alignent = alignent;
	}

	public double getInitialSceneWidth() {
		return initialSceneWidth;
	}

	public void setInitialSceneWidth(double initialSceneWidth) {
		this.initialSceneWidth = initialSceneWidth;
	}

	public double getInitialSceneHeight() {
		return initialSceneHeight;
	}

	public void setInitialSceneHeight(double initialSceneHeight) {
		this.initialSceneHeight = initialSceneHeight;
	}

	public int getHgap() {
		return hgap;
	}

	public void setHgap(int hgap) {
		this.hgap = hgap;
	}

	public int getVgap() {
		return vgap;
	}

	public void setVgap(int vgap) {
		this.vgap = vgap;
	}

	public Insets getPadding() {
		return padding;
	}

	public void setPadding(Insets padding) {
		this.padding = padding;
	}

	public List<String> getStylesheets() {
		return stylesheets;
	}

	public void setStylesheets(List<String> stylesheets) {
		this.stylesheets = stylesheets;
	}

	public List<ColumnConstraints> getColConstraints() {
		return colConstraints;
	}

	public void setColConstraints(List<ColumnConstraints> colConstraints) {
		this.colConstraints = colConstraints;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public static class Builder {

		FenxuiConfig config = new FenxuiConfig();

		public FenxuiConfig build() {
			return config;
		}

		public Builder set(Setter setter) {
			setter.execute();
			return this;
		}

		public Builder alignment(Pos pos) {
			return set(() -> config.alignent = pos);
		}

		public Builder initialSceneWidth(int initialSceneWidth) {
			return set(() -> config.initialSceneWidth = initialSceneWidth);
		}

		public Builder initialSceneHeight(int initialSceneHeight) {
			return set(() -> config.initialSceneHeight = initialSceneHeight);
		}

		public Builder hGap(int hgap) {
			return set(() -> config.hgap = hgap);
		}

		public Builder vGap(int vgap) {
			return set(() -> config.vgap = vgap);
		}


		public Builder formColumnConstraints(ColumnConstraints... columnConstraints) {
			return set(() -> config.colConstraints = Arrays.asList(columnConstraints));
		}

		public Builder padding(double inset) {
			return set(() -> config.padding = new Insets(inset));
		}

		public Builder padding(double top, double right, double bottom, double left) {
			return set(() -> config.padding = new Insets(top, right, bottom, left));
		}

		public Builder css(URL url) {
			return set(() -> config.stylesheets.add(url.toExternalForm()));
		}

		public Builder title(String title) {
			return set(()-> config.title = title);
		}
	}

	@FunctionalInterface
	private interface Setter {

		void execute();
	}

	private static class FormPrototype {

		private int percentWidthFormFieldLabel = 20;
		private int percentWidthFormField = 30;
		private int percentWidthFormFieldButton = 10;

		public List<ColumnConstraints> getFormLayout() {
			ColumnConstraints label = new ColumnConstraints();
			label.setPercentWidth(percentWidthFormFieldLabel);
			ColumnConstraints field = new ColumnConstraints();
			field.setPercentWidth(percentWidthFormField);
			ColumnConstraints button = new ColumnConstraints();
			button.setPercentWidth(percentWidthFormFieldButton);
			return Arrays.asList(label, field, button);
		}

	}
}
