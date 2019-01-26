package org.fenxui.application.view.components.valueprovider;

import java.util.ArrayList;
import java.util.List;
import org.fenxui.application.view.components.option.FieldOption.DisplayValue;

public class StaticValueProvider implements ValueProvider {
	private final List<DisplayValue> values = new ArrayList<>();
	
	@Override
	public List<DisplayValue> getValues() {
		return values;
	}
	
	public void addValue(DisplayValue value) {
		values.add(value);
	}

}
