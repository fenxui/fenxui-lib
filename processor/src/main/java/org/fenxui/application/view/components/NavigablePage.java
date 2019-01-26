package org.fenxui.application.view.components;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Paint;
import org.fenxui.application.view.components.menu.PageLink;
import org.fenxui.application.view.components.option.ColorOptions;
import org.fenxui.api.option.layout.Orientation;

import java.util.Collections;
import java.util.List;

public class NavigablePage<T extends NamedControl> extends Control implements NamedHideable {
	private String name;
	protected List<T> options = Collections.emptyList();
	private ObjectProperty<Paint> linkHintColor = new SimpleObjectProperty<>();
	private final List<PageLink> menuItems;
	private final Orientation orientation;
	private final String menuCssClass;
	private final int minimumWidth;
	private final int minimumHeight;

	public NavigablePage(List<PageLink> menuItems, Orientation orientation, String menuCssClass, int minimumWidth, int minimumHeight) {
		this.menuItems = menuItems;
		this.orientation = orientation;
		this.menuCssClass = menuCssClass;
		this.minimumWidth = minimumWidth;
		this.minimumHeight = minimumHeight;
		linkHintColor.set(Paint.valueOf(ColorOptions.YELLOWISH));
	}

	public void votify(String message, boolean isValid) {
		//noop
	}

	public void setOptions(List<T> options) {
		this.options = options;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public List<T> getOptions() {
		return options;
	}
	public Paint getLinkHintColor() {
		return linkHintColor.get();
	}

	@Override
	public ObjectProperty<Paint> linkHintColorProperty() {
		return linkHintColor;
	}

	public void setLinkHintColor(Paint linkHintColor) {
		this.linkHintColor.set(linkHintColor);
	}

	public int getMinimumWidth() {
		return minimumWidth;
	}

	public int getMinimumHeight() {
		return minimumHeight;
	}

	public String getMenuCssClass() {
		return menuCssClass;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public List<PageLink> getMenuItems() {
		return menuItems;
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new NavigablePageSkin(this);
	}



}
