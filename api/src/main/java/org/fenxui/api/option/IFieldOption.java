package org.fenxui.api.option;

import org.fenxui.api.validator.IValidator;

import java.util.List;

public interface IFieldOption {
	List<IValidator> getValidators();
}
