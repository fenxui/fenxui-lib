package org.fenxui.api.factory;

import org.fenxui.api.option.IValidatorOption;
import org.fenxui.api.validator.IValidator;
import org.fenxui.core.exception.FenxuiInitializationException;

public interface ValidatorFactory<O extends IValidatorOption, V extends IValidator> {
	V create(O validatorOption) throws FenxuiInitializationException;
}
