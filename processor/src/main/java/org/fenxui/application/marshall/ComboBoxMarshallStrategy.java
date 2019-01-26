package org.fenxui.application.marshall;

import javafx.beans.property.StringProperty;
import javafx.scene.control.ComboBox;
import org.apache.commons.lang3.StringUtils;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.application.view.components.valueprovider.ValueProvider;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ComboBoxMarshallStrategy implements MarshallStrategy<StringProperty, ComboBox<String>> {

	@Override
	public void execute(FieldOption<StringProperty> fieldOption, ComboBox<String> comboBox) {
		ValueProvider valueProvider = fieldOption.getValueProvider();
		List<FieldOption.DisplayValue> values = valueProvider.getValues();

		//create a two-way map
		Map<String, String> selectedToSaveValuesMap = values.stream().collect(Collectors.toMap(v -> v.getDisplayValue(), v -> v.getSaveValue()));
		Map<String, String> saveToDisplayValuesMap = values.stream().collect(Collectors.toMap(v -> v.getSaveValue(), v -> v.getDisplayValue()));

		comboBox.getItems().addAll(selectedToSaveValuesMap.keySet());
		if (!StringUtils.isEmpty(fieldOption.getValue().getValue())) {
			String displayValue = saveToDisplayValuesMap.get(fieldOption.getValue().getValue());
			comboBox.selectionModelProperty().get().select(displayValue);
		}
		comboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			String saveValue = selectedToSaveValuesMap.get(newValue);
			fieldOption.getValue().setValue(saveValue);
		});
	}
}
