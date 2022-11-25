package org.eclipse.scout.contacts.client.common;

import org.eclipse.scout.contacts.shared.common.AbstractNotesBoxData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.dto.FormData.DefaultSubtypeSdkCommand;
import org.eclipse.scout.rt.client.dto.FormData.SdkCommand;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;

@FormData(value = AbstractNotesBoxData.class, sdkCommand = SdkCommand.CREATE, defaultSubtypeSdkCommand = DefaultSubtypeSdkCommand.CREATE)
public abstract class AbstractNotesBox extends AbstractGroupBox {

	@Override
	protected String getConfiguredLabel() {
		// TODO Auto-generated method stub
		return TEXTS.get("Notes");
	}

	public NotesField getNotesField() {
		return getFieldByClass(NotesField.class);
	}

	@Order(1000)
	public class NotesField extends AbstractStringField {

		@Override
		protected int getConfiguredGridH() {
			// TODO Auto-generated method stub
			return 4;
		}

		@Override
		protected boolean getConfiguredLabelVisible() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		protected boolean getConfiguredMultilineText() {
			// TODO Auto-generated method stub
			return true;
		}
	}

}
