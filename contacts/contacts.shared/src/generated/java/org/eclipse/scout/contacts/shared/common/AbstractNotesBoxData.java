package org.eclipse.scout.contacts.shared.common;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.form.fields.AbstractFormFieldData;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications
 * recommended.
 */
@Generated(value = "org.eclipse.scout.contacts.client.common.AbstractNotesBox", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public abstract class AbstractNotesBoxData extends AbstractFormFieldData {
	private static final long serialVersionUID = 1L;

	public Notes getNotes() {
		return getFieldByClass(Notes.class);
	}

	public static class Notes extends AbstractValueFieldData<String> {
		private static final long serialVersionUID = 1L;
	}
}
