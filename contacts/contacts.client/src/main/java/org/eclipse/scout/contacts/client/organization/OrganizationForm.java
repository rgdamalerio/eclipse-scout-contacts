package org.eclipse.scout.contacts.client.organization;

import org.eclipse.scout.contacts.client.common.AbstractEmailField;
import org.eclipse.scout.contacts.client.common.AbstractNotesBox;
import org.eclipse.scout.contacts.client.common.AbstractUrlImageField;
import org.eclipse.scout.contacts.client.organization.OrganizationForm.MainBox.CancelButton;
import org.eclipse.scout.contacts.client.organization.OrganizationForm.MainBox.OkButton;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.GeneralBox;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.PhoneField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.GeneralBox.PictureField;
import org.eclipse.scout.contacts.shared.organization.IOrganizationService;
import org.eclipse.scout.contacts.shared.organization.OrganizationFormData;
import org.eclipse.scout.contacts.shared.organization.UpdateOrganizationPermission;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.desktop.bookmark.BookmarkFolderForm.MainBox.GroupBox.NameField;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;

@FormData(value = OrganizationFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class OrganizationForm extends AbstractForm {

	private String organizationId;

	@FormData
	public String getOrganizationId() {
		return organizationId;
	}

	@FormData
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public Object computeExclusiveKey() {
		return getOrganizationId();
	};

	@Override
	protected String getConfiguredTitle() {
		// TODO [Client] verify translation
		return TEXTS.get("Organization");
	}

	protected int getConfiguredDisplayHint() {
		return IForm.DISPLAY_HINT_VIEW;
	};

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public GeneralBox getGeneralBox() {
		return getFieldByClass(GeneralBox.class);
	}

	public PictureField getPictureField() {
		return getFieldByClass(PictureField.class);
	}

	public DetailsBox getDetailsBox() {
		return getFieldByClass(DetailsBox.class);
	}

	public ContactInfoBox getContactInfoBox() {
		return getFieldByClass(ContactInfoBox.class);
	}

	public PhoneField getPhoneField() {
		return getFieldByClass(PhoneField.class);
	}

	public NameField getNameField() {
		return getFieldByClass(NameField.class);
	}

	public OkButton getOkButton() {
		return getFieldByClass(OkButton.class);
	}

	public CancelButton getCancelButton() {
		return getFieldByClass(CancelButton.class);
	}

	@Order(1000)
	public class MainBox extends AbstractGroupBox {

		@Order(1000)
		public class GeneralBox extends AbstractGroupBox {

			@Order(1000)
			public class PictureField extends AbstractUrlImageField {

				@Override
				protected int getConfiguredGridH() {
					return 4;
				}

				protected double getConfiguredGridWeightY() {
					return 0;
				};

			}

			@Order(2000)
			public class NameField extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Name");
				}

				protected boolean getConfiguredMandatory() {
					return true;
				};
			}

			@Order(3000)
			public class HomepageField extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Homepage");
				}

			}

		}

		@Order(1500)
		public class DetailsBox extends AbstractTabBox {

			@Order(1000)
			public class ContactInfoBox extends AbstractGroupBox {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("ContactInfo");
				}

				@Order(1000)
				public class AddressBox extends AbstractGroupBox {

				}

				@Order(2000)
				public class PhoneField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Phone");
					}

				}

				@Order(3000)
				public class EmailField extends AbstractEmailField {

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
			OrganizationFormData formData = new OrganizationFormData();
			exportFormData(formData);
			formData = BEANS.get(IOrganizationService.class).create(formData);
			importFormData(formData);
		}
	}

	public class ModifyHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() {
			OrganizationFormData formData = new OrganizationFormData();
			exportFormData(formData);
			formData = BEANS.get(IOrganizationService.class).load(formData);
			importFormData(formData);

			setEnabledPermission(new UpdateOrganizationPermission());
		}

		@Override
		protected void execStore() {
			OrganizationFormData formData = new OrganizationFormData();
			exportFormData(formData);
			formData = BEANS.get(IOrganizationService.class).store(formData);
			importFormData(formData);
		}
	}
}
