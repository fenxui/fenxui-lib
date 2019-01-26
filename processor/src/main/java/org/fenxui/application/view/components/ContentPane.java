package org.fenxui.application.view.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Paint;
import org.fenxui.application.view.components.option.ColorOptions;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.application.view.components.option.MethodOption;

/**
 * What the @AppPage ends up being displayed in
 */
public class ContentPane extends Control implements NamedHideable {
	private String name;
	private final ObjectProperty<Paint> linkHintColor = new SimpleObjectProperty<>();
	private Map<String, Integer> validity = new HashMap<>();//1. error  0.  okay
	private final List<FieldOption> options = new ArrayList<>();
	private final List<MethodOption> actionOptions = new ArrayList<>();
	private int validatableFields = 0;
	
	public ContentPane() {
		linkHintColor.set(Paint.valueOf(ColorOptions.YELLOWISH));
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public List<FieldOption> getOptions() {
		return options;
	}

	public List<MethodOption> getActionOptions() {
		return actionOptions;
	}

	public void addFields(List<FieldOption> fieldOptions) {
		if (fieldOptions != null) {
			fieldOptions.stream().forEach(option-> {
				options.add(option);
				if (option.getValidators() != null || !option.getValidators().isEmpty()) {
					validatableFields++;
				}
			});
		}
	}

	public void addActionWidgets(List<MethodOption> methodOptions) {
		if (methodOptions != null) {
			methodOptions.stream().forEach(option-> {
				actionOptions.add(option);
			});
		}
	}

	@Override
	public ObjectProperty<Paint> linkHintColorProperty() {
		return linkHintColor;
	}

	@Override
	public void votify(String uniqueId, boolean isValid) {
		validity.put(uniqueId, isValid ? 0 : 1);
		int sum = validity.values().stream().collect(Collectors.summingInt(s->s));
		if (sum > 0) {//errors exist
			linkHintColor.set(Paint.valueOf(ColorOptions.REDDISH));
		} else if (validity.keySet().size() < validatableFields){
			linkHintColor.set(Paint.valueOf(ColorOptions.YELLOWISH));
		} else {
			linkHintColor.set(Paint.valueOf(ColorOptions.GREENISH));
		}
	}
	
	@Override
	protected Skin<?> createDefaultSkin() {
		return new ContentPaneSkin(this);
	}
}
