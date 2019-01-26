package org.fenxui.application.view.components.option;

import org.fenxui.api.option.IValidatorOption;

public class ValidatorOptions implements IValidatorOption {
	private String message;

	public ValidatorOptions(String message) {
		this.message = message;
	}

	public ValidatorOptions() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
