package org.eclipse.scout.contacts.client.person;

import org.eclipse.scout.contacts.client.common.AbstractAddressBox;
import org.eclipse.scout.contacts.client.common.AbstractEmailField;
import org.eclipse.scout.contacts.client.common.AbstractNotesBox;
import org.eclipse.scout.contacts.client.common.AbstractUrlImageField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.EmailField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.MobileField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.PhoneField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.NotesBox;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox.EmailWorkField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox.OrganizationField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox.PhoneWorkField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox.PositionField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.GeneralBox.DateOfBirthField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.GeneralBox.FirstNameField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.GeneralBox.GenderGroup;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.GeneralBox.LastNameField;
import org.eclipse.scout.contacts.shared.organization.OrganizationLookupCall;
import org.eclipse.scout.contacts.shared.person.GenderCodeType;
import org.eclipse.scout.contacts.shared.person.IPersonService;
import org.eclipse.scout.contacts.shared.person.PersonFormData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.radiobuttongroup.AbstractRadioButtonGroup;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@FormData(value = PersonFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class PersonForm extends AbstractForm {

	@Override
	protected boolean execValidate() {
		// TODO Auto-generated method stub
		boolean noFirstName = StringUtility.isNullOrEmpty(getFirstNameField().getValue());
		boolean noLastName = StringUtility.isNullOrEmpty(getLastNameField().getValue());

		if (noFirstName) {
			getFirstNameField().requestFocus();

			throw new VetoException(TEXTS.get("MisingFirstName"));
		}
		if (noLastName) {
			getLastNameField().requestFocus();

			throw new VetoException(TEXTS.get("MisingLastName"));
		}
		return true;
	}

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

	public MobileField getMobileField() {
		return getFieldByClass(MobileField.class);
	}

	public EmailField getEmailField() {
		return getFieldByClass(EmailField.class);
	}

	public PositionField getPositionField() {
		return getFieldByClass(PositionField.class);
	}

	public PhoneWorkField getPhoneWorkField() {
		return getFieldByClass(PhoneWorkField.class);
	}

	public FirstNameField getFirstNameField() {
		return getFieldByClass(FirstNameField.class);
	}

	public LastNameField getLastNameField() {
		return getFieldByClass(LastNameField.class);
	}

	public DateOfBirthField getDateOfBirthField() {
		return getFieldByClass(DateOfBirthField.class);
	}

	public GenderGroup getGenderGroup() {
		return getFieldByClass(GenderGroup.class);
	}

	public EmailWorkField getEmailWorkField() {
		return getFieldByClass(EmailWorkField.class);
	}

	public NotesBox getNotesBox() {
		return getFieldByClass(NotesBox.class);
	}

	public OrganizationField getOrganizationField() {
		return getFieldByClass(OrganizationField.class);
	}

	public PhoneField getPhoneField() {
		return getFieldByClass(PhoneField.class);
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

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
			public class PictureField extends AbstractUrlImageField {

			}

			@Order(30)
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

			@Order(40)
			public class FirstNameField extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("FirstName");
				}

				@Override
				protected int getConfiguredMaxLength() {
					return 128;
				}
			}

			@Order(50)
			public class LastNameField extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("LastName");
				}

				@Override
				protected int getConfiguredMaxLength() {
					return 128;
				}
			}

			@Order(60)
			public class DateOfBirthField extends AbstractDateField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("DateOfBirth");
				}
			}

		}

		@Order(20)
		@ClassId("3469046e-ee95-4e86-b0c9-a8ed01fbf664")
		public class DetailsBox extends AbstractTabBox {

			@Order(10)
			@ClassId("2081b483-3d6e-4239-b7da-b6e2d2aa3b7a")
			public class ContactInfoBox extends AbstractGroupBox {

				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("ContactInfo0");
				}

				@Order(10)
				@ClassId("736450dd-ba89-43cd-ba52-bcd31196b462")
				public class AddressBox extends AbstractAddressBox {

				}

				@Order(2000)
				public class PhoneField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Phone");
					}

					@Override
					protected int getConfiguredMaxLength() {
						return 128;
					}
				}

				@Order(3000)
				public class MobileField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Mobile");
					}

					@Override
					protected int getConfiguredMaxLength() {
						return 128;
					}
				}

				@Order(4000)
				public class EmailField extends AbstractEmailField {

				}

			}

			@Order(20)
			@ClassId("8e18a673-aca5-44a2-898f-60a744e4467a")
			public class WorkBox extends AbstractGroupBox {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Work");
				}

				@Order(1000)
				public class PositionField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Position");
					}

					@Override
					protected int getConfiguredMaxLength() {
						return 128;
					}
				}

				@Order(2000)
				public class OrganizationField extends AbstractSmartField<String> {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Organization");
					}

					@Override
					protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
						return OrganizationLookupCall.class;
					}
				}

				@Order(3000)
				public class PhoneWorkField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("PhoneWork");
					}

					@Override
					protected int getConfiguredMaxLength() {
						return 128;
					}
				}

				@Order(4000)
				public class EmailWorkField extends AbstractEmailField {

				}

			}

			@Order(2000)
			public class NotesBox extends AbstractNotesBox {

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
		protected void execStore() {
			IPersonService service = BEANS.get(IPersonService.class);
			PersonFormData formData = new PersonFormData();
			exportFormData(formData);
			formData = service.create(formData); // <7>
			importFormData(formData);
		}
	}

	public class ModifyHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() {

			IPersonService service = BEANS.get(IPersonService.class); // <1>
			PersonFormData formData = new PersonFormData();
			exportFormData(formData); // <2>
			formData = service.load(formData); // <3>
			importFormData(formData); // <4>

			getForm().setSubTitle(calculateSubTitle()); // <5>
		}

		@Override
		protected void execStore() {

			IPersonService service = BEANS.get(IPersonService.class);
			PersonFormData formData = new PersonFormData();
			exportFormData(formData);
			service.store(formData); // <6>
		}
	}

	protected String calculateSubTitle() {
		return StringUtility.join(" ", getFirstNameField().getValue(), getLastNameField().getValue());
	}
}
