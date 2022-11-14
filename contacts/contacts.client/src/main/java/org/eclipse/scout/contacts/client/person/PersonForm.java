package org.eclipse.scout.contacts.client.person;

import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.CancelButton;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.GroupBox;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.OkButton;
import org.eclipse.scout.contacts.shared.person.CreatePersonPermission;
import org.eclipse.scout.contacts.shared.person.IPersonService;
import org.eclipse.scout.contacts.shared.person.PersonFormData;
import org.eclipse.scout.contacts.shared.person.UpdatePersonPermission;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;

@FormData(value = PersonFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class PersonForm extends AbstractForm {

	// represents the person's primary key
	private String personId;

	@FormData
	public String getPersonId() {
		return personId;
	}

	@FormData
	public void setPersonId(String personId) {
		this.personId = personId;
	}

	@Override
	public Object computeExclusiveKey() {
		return getPersonId();
	}

	@Override
	protected int getConfiguredDisplayHint() {
		return IForm.DISPLAY_HINT_VIEW;
	}

	@Override
	protected String getConfiguredTitle() {
		// TODO [Administrator] verify translation
		return TEXTS.get("Person");
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public GroupBox getGroupBox() {
		return getFieldByClass(GroupBox.class);
	}

	public OkButton getOkButton() {
		return getFieldByClass(OkButton.class);
	}

	public CancelButton getCancelButton() {
		return getFieldByClass(CancelButton.class);
	}

	@Order(1000)
	public class MainBox extends AbstractGroupBox {

		@Order(10)
		@ClassId("08832a97-8845-4ff4-8dfd-c29366c22742")
		public class GeneralBox extends AbstractGroupBox {
		}

		@Order(20)
		@ClassId("3469046e-ee95-4e86-b0c9-a8ed01fbf664")
		public class DetailsBox extends AbstractTabBox {

			@Order(10)
			@ClassId("2081b483-3d6e-4239-b7da-b6e2d2aa3b7a")
			public class ContactInfoBox extends AbstractGroupBox {

				@Order(10)
				@ClassId("736450dd-ba89-43cd-ba52-bcd31196b462")
				public class AddressBox extends AbstractGroupBox {
				}
			}

			@Order(20)
			@ClassId("8e18a673-aca5-44a2-898f-60a744e4467a")
			public class WorkBox extends AbstractGroupBox {
			}

			@Order(30)
			@ClassId("fcb5b155-2c89-4ef8-9a96-ac41e9032107")
			public class NotesBox extends AbstractGroupBox {
			}
		}

		@Order(1000)
		public class GroupBox extends AbstractGroupBox {

		}

		@Order(2000)
		public class OkButton extends AbstractOkButton {

		}

		@Order(3000)
		public class CancelButton extends AbstractCancelButton {

		}
	}

	public void startModify() {
		startInternalExclusive(new ModifyHandler());
	}

	public void startNew() {
		startInternal(new NewHandler());
	}

	public class NewHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() {
			PersonFormData formData = new PersonFormData();
			exportFormData(formData);
			formData = BEANS.get(IPersonService.class).prepareCreate(formData);
			importFormData(formData);

			setEnabledPermission(new CreatePersonPermission());
		}

		@Override
		protected void execStore() {
			PersonFormData formData = new PersonFormData();
			exportFormData(formData);
			formData = BEANS.get(IPersonService.class).create(formData);
			importFormData(formData);
		}
	}

	public class ModifyHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() {
			PersonFormData formData = new PersonFormData();
			exportFormData(formData);
			formData = BEANS.get(IPersonService.class).load(formData);
			importFormData(formData);

			setEnabledPermission(new UpdatePersonPermission());
		}

		@Override
		protected void execStore() {
			PersonFormData formData = new PersonFormData();
			exportFormData(formData);
			formData = BEANS.get(IPersonService.class).store(formData);
			importFormData(formData);
		}
	}
}
