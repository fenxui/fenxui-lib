package org.fenxui.application.view.components.menu;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import org.fenxui.application.view.components.NamedHideable;

public class PageLink extends Control {
	private final NamedHideable page;
	private final StringProperty name = new SimpleStringProperty();
	private final BooleanProperty selected = new SimpleBooleanProperty(false);
	private final BooleanProperty required = new SimpleBooleanProperty(false);

	private boolean selectByDefault;

	public PageLink(String name, NamedHideable page, boolean required, String menuCssClass) {
		this.name.set(name);
		this.page = page;
		this.required.set(required);
		page.setName(name);
		// Region is not focus traversable by default, so we enable it here
		setFocusTraversable(true);
		getStyleClass().add(menuCssClass);
	}

	public NamedHideable getPage() {
		return page;
	}

	public String getName() {
		return name.get();
	}

	public StringProperty nameProperty() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public boolean isSelected() {
		return selected.get();
	}

	public BooleanProperty selectedProperty() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected.set(selected);
	}

	public boolean isSelectByDefault() {
		return selectByDefault;
	}

	public void setSelectByDefault(boolean selectByDefault) {
		this.selectByDefault = selectByDefault;
	}

	public boolean getRequired() {
		return required.get();
	}

	public BooleanProperty requiredProperty() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required.set(required);
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new PageLinkSkin(this, selectByDefault);
	}

}
