package org.fenxui.application.view.components.valueprovider;

import java.util.List;
import org.fenxui.application.view.components.option.FieldOption.DisplayValue;

public interface ValueProvider {
	List<DisplayValue> getValues();
}
