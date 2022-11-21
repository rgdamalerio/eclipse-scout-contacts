package org.eclipse.scout.contacts.client.person;

import java.util.regex.Pattern;

import org.eclipse.scout.contacts.client.common.CountryLookupCall;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.AddressBox.LocationBox.CityField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.AddressBox.LocationBox.CountryField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.AddressBox.StreetField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.EmailField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.MobileField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.PhoneField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox.EmailWorkField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox.OrganizationField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox.PhoneWorkField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox.PositionField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.GeneralBox.DateOfBirthField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.GeneralBox.FirstNameField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.GeneralBox.GenderGroup;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.GeneralBox.LastNameField;
import org.eclipse.scout.contacts.shared.Icons;
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
import org.eclipse.scout.rt.client.ui.form.fields.imagefield.AbstractImageField;
import org.eclipse.scout.rt.client.ui.form.fields.radiobuttongroup.AbstractRadioButtonGroup;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
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

	public EmailWorkField getEmailWorkField() {
		return getFieldByClass(EmailWorkField.class);
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

	public StreetField getStreetField() {
		return getFieldByClass(StreetField.class);
	}

	public CityField getCityField() {
		return getFieldByClass(CityField.class);
	}

	public CountryField getCountryField() {
		return getFieldByClass(CountryField.class);
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

			@Order(40)
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

			@Order(50)
			public class DateOfBirthField extends AbstractDateField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("DateOfBirth");
				}
			}

			@Order(2000)
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

				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("ContactInfo0");
				}

				@Order(10)
				@ClassId("736450dd-ba89-43cd-ba52-bcd31196b462")
				public class AddressBox extends AbstractGroupBox {

					@Override
					protected boolean getConfiguredBorderVisible() {
						return false;
					}

					@Override
					protected int getConfiguredGridH() {
						return 3;
					}

					@Override
					protected int getConfiguredGridW() {
						return 1;
					}

					@Override
					protected int getConfiguredGridColumnCount() {
						return 1;
					}

					@Order(10)
					public class StreetField extends AbstractStringField {
						@Override
						protected String getConfiguredLabel() {
							return TEXTS.get("Street");
						}

						@Override
						protected int getConfiguredMaxLength() {
							return 128;
						}

						@Override
						protected void execChangedValue() {
							// TODO Auto-generated method stub
							validateAddressFields();
						}
					}

					protected void validateAddressFields() {
						boolean hasStreet = StringUtility.hasText(getStreetField().getValue());
						boolean hasCity = StringUtility.hasText(getCityField().getValue());

						getCityField().setMandatory(hasStreet);
						getCountryField().setMandatory(hasStreet || hasCity);
					}

					// use a sequence box for horizontal layout
					@Order(20)
					@ClassId("a278333c-057e-4c1d-a442-0c1dd62fdca7")
					public class LocationBox extends AbstractSequenceBox {

						@Override
						protected String getConfiguredLabel() {
							return TEXTS.get("Location");
						}

						@Override
						protected boolean getConfiguredAutoCheckFromTo() {
							return false;
						}

						@Order(-1000)
						public class CityField extends AbstractStringField {
							@Override
							protected String getConfiguredLabel() {
								return TEXTS.get("City");
							}

							@Override
							protected int getConfiguredMaxLength() {
								return 128;
							}

							@Override
							protected byte getConfiguredLabelPosition() {
								// TODO Auto-generated method stub
								return LABEL_POSITION_ON_FIELD;
							}

							@Override
							protected void execChangedValue() {
								// TODO Auto-generated method stub
								validateAddressFields();
							}
						}

						@Order(20)
						public class CountryField extends AbstractSmartField<String> {
							@Override
							protected String getConfiguredLabel() {
								return TEXTS.get("Country");
							}

							@Override
							protected byte getConfiguredLabelPosition() {
								// TODO Auto-generated method stub
								return LABEL_POSITION_ON_FIELD;
							}

							@Override
							protected void execChangedValue() {
								// TODO Auto-generated method stub
								validateAddressFields();
							}

							@Override
							protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
								// TODO Auto-generated method stub
								return CountryLookupCall.class;
							}
						}

					}
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
				public class EmailField extends AbstractStringField {

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
				public class OrganizationField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Organization");
					}

					@Override
					protected int getConfiguredMaxLength() {
						return 128;
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
				public class EmailWorkField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("EmailWork");
					}

					@Override
					protected int getConfiguredMaxLength() {
						return 128;
					}
				}

			}

			@Order(30)
			@ClassId("fcb5b155-2c89-4ef8-9a96-ac41e9032107")
			public class NotesBox extends AbstractGroupBox {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Notes");
				}

				@Order(10)
				@ClassId("ce791f14-fca6-4f11-8476-89cbf905eb2e")
				public class NotesField extends AbstractStringField {

					@Override
					protected int getConfiguredGridH() {
						return 4;
					}

					@Override
					protected boolean getConfiguredLabelVisible() {
						return false;
					}

					@Override
					protected boolean getConfiguredMultilineText() {
						return true;
					}
				}
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
//		@Override
//		protected void execLoad() {
//			PersonFormData formData = new PersonFormData();
//			exportFormData(formData);
//			formData = BEANS.get(IPersonService.class).prepareCreate(formData);
//			importFormData(formData);
//
//			setEnabledPermission(new CreatePersonPermission());
//		}

		@Override
		protected void execStore() {
			IPersonService service = BEANS.get(IPersonService.class);
			PersonFormData formData = new PersonFormData();
			exportFormData(formData);
			formData = service.create(formData);
			importFormData(formData);
		}
	}

	public class ModifyHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() {
//			PersonFormData formData = new PersonFormData();
//			exportFormData(formData);
//			formData = BEANS.get(IPersonService.class).load(formData);
//			importFormData(formData);
//
//			setEnabledPermission(new UpdatePersonPermission());

			IPersonService service = BEANS.get(IPersonService.class);
			PersonFormData formData = new PersonFormData();
			exportFormData(formData);
			formData = service.load(formData);
			importFormData(formData);

			getForm().setSubTitle(calculateSubTitle());
		}

		@Override
		protected void execStore() {
//			PersonFormData formData = new PersonFormData();
//			exportFormData(formData);
//			formData = BEANS.get(IPersonService.class).store(formData);
//			importFormData(formData);

			IPersonService service = BEANS.get(IPersonService.class);
			PersonFormData formData = new PersonFormData();
			exportFormData(formData);
			service.store(formData);
		}
	}

	protected String calculateSubTitle() {
		return StringUtility.join(" ", getFirstNameField().getValue(), getLastNameField().getValue());
	}
}
