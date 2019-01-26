package org.fenxui.application.view.factory.handler.page;

import javafx.scene.control.Label;
import org.fenxui.application.view.components.NamedHideable;
import org.fenxui.application.view.components.menu.PageLink;
import org.fenxui.application.view.factory.ootb.FrameContext;
import org.fenxui.api.option.layout.Orientation;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFrameContext implements FrameContext {
	//linked Navigable Frames/Pages
	protected final List<NamedHideable> contentPanes = new ArrayList<>();
	protected final List<PageLink> menuItems = new ArrayList<>();
	//menu attributes
	private String menuCssClass;
	private Orientation menuOrientation;
	private int menuMinWidth;
	private int menuMinHeight;
	//frame attributes
	private Label title;
	private String pageCss;

	//logic methods
	@Override
	public void linkPage(String value, boolean required, String cssClass) {
		NamedHideable last = contentPanes.get(contentPanes.size()-1);
		PageLink pageLink = new PageLink(value,  last, required, cssClass);
		menuItems.add(pageLink);
	}

	//generated getters/setters
	@Override
	public void setTitle(Label title) {
		this.title = title;
	}
	public Label getTitle() {
		return title;
	}

	@Override
	public void setPageCss(String cssClass) {
		this.pageCss = cssClass;
	}
	public String getPageCss() {
		return pageCss;
	}

	@Override
	public void setMenuOrientation(Orientation menuOrientation) {
		this.menuOrientation = menuOrientation;
	}
	public Orientation getMenuOrientation() {
		return menuOrientation;
	}

	@Override
	public void setMenuCssClass(String cssClass) {
		this.menuCssClass = cssClass;
	}
	public String getMenuCssClass() {
		return menuCssClass;
	}

	@Override
	public void setMenuMinWidth(int menuMinWidth) {
		this.menuMinWidth = menuMinWidth;
	}
	public int getMenuMinWidth() {
		return menuMinWidth;
	}

	@Override
	public void setMenuMinHeight(int menuMinHeight) {
		this.menuMinHeight = menuMinHeight;
	}
	public int getMenuMinHeight() {
		return menuMinHeight;
	}

	public List<PageLink> getMenuItems() {
		return menuItems;
	}

	public void addContentPane(NamedHideable contentPane) {
		contentPanes.add(contentPane);
	}
	public List<NamedHideable> getContentPanes() {
		return contentPanes;
	}

}
