package org.eclipse.scout.contacts.client.person;

import org.eclipse.scout.contacts.shared.Icons;
import org.eclipse.scout.contacts.shared.person.CreatePersonPermission;
import org.eclipse.scout.contacts.shared.person.GenderCodeType;
import org.eclipse.scout.contacts.shared.person.IPersonService;
import org.eclipse.scout.contacts.shared.person.PersonFormData;
import org.eclipse.scout.contacts.shared.person.UpdatePersonPermission;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.imagefield.AbstractImageField;
import org.eclipse.scout.rt.client.ui.form.fields.radiobuttongroup.AbstractRadioButtonGroup;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;

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

//	public MainBox getMainBox() {
//		return getFieldByClass(MainBox.class);
//	}
//
//	public GroupBox getGroupBox() {
//		return getFieldByClass(GroupBox.class);
//	}
//
//	public OkButton getOkButton() {
//		return getFieldByClass(OkButton.class);
//	}
//
//	public CancelButton getCancelButton() {
//		return getFieldByClass(CancelButton.class);
//	}

	@Order(1000)
	public class MainBox extends AbstractGroupBox {
//		@Order(1000)
//		public class GroupBox extends AbstractGroupBox {
//
//		}

		@Order(10)
		@ClassId("08832a97-8845-4ff4-8dfd-c29366c22742")
		public class GeneralBox extends AbstractGroupBox {
			@Order(10)
			@ClassId("617ffd40-0d69-4d02-b4f8-90c28c68c6ce")
			public class PictureUrlField extends AbstractStringField {

				@Override
				protected boolean getConfiguredVisible() {
					return false;
				}
			}

			@Order(20)
			@ClassId("6366a23e-f8ba-4b50-b814-202e63daffc8")
			public class PictureField extends AbstractImageField {

				@Override
				protected Class<PictureUrlField> getConfiguredMasterField() {
					return PictureUrlField.class;
				}

				@Override
				protected void execChangedMasterValue(Object newMasterValue) {
					updateImage((String) newMasterValue);
				}

				@Override
				protected boolean getConfiguredLabelVisible() {
					return false;
				}

				@Override
				protected int getConfiguredGridH() {
					return 5;
				}

				@Override
				protected boolean getConfiguredAutoFit() {
					return true;
				}

				@Override
				protected String getConfiguredImageId() {
					return Icons.PersonSolid;
				}

				protected void updateImage(String url) {
					setImageUrl(url);
				}
			}

			@Order(30)
			@ClassId("359be835-439f-456e-9b0d-c832b034a298")
			public class FirstNameField extends AbstractStringField {

				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("FirstName");
				}
			}

			@Order(40)
			@ClassId("8679ade5-21fb-470e-8f00-13bd15199101")
			public class LastNameField extends AbstractStringField {

				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("LastName");
				}
			}

			@Order(50)
			@ClassId("7c602360-9daa-44b8-abb6-94ccf9b9db59")
			public class DateOfBirthField extends AbstractDateField {

				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("DateOfBirth");
				}
			}

			@Order(60)
			@ClassId("b9d0593e-3938-4f97-bdca-fdb6a1ce1d77")
			public class GenderGroup extends AbstractRadioButtonGroup<String> {

				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Gender");
				}

				@Override
				protected Class<? extends ICodeType<?, String>> getConfiguredCodeType() {
					return GenderCodeType.class;
				}
			}

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
