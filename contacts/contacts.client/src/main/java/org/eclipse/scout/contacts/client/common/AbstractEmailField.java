package org.eclipse.scout.contacts.client.common;

import java.util.regex.Pattern;

import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;

public abstract class AbstractEmailField extends AbstractStringField {
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Override
	protected String getConfiguredLabel() {
		return TEXTS.get("E-Mail");
	}

	@Override
	protected int getConfiguredMaxLength() {
		return 64;
	}

	@Override
	protected String execValidateValue(String rawValue) {
		if (rawValue != null && !Pattern.matches(EMAIL_PATTERN, rawValue)) {
			throw new VetoException(TEXTS.get("BadEmailAddresss"));
		}

		return rawValue;
	}
}
