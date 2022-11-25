package org.eclipse.scout.contacts.client.common;

import org.eclipse.scout.contacts.client.common.AbstractAddressBox.LocationBox.CityField;
import org.eclipse.scout.contacts.client.common.AbstractAddressBox.LocationBox.CountryField;
import org.eclipse.scout.contacts.client.common.AbstractAddressBox.ShowOnMapButtonBox.ShowOnMapButton;
import org.eclipse.scout.contacts.shared.Icons;
import org.eclipse.scout.contacts.shared.common.AbstractAddressBoxData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.dto.FormData.DefaultSubtypeSdkCommand;
import org.eclipse.scout.rt.client.dto.FormData.SdkCommand;
import org.eclipse.scout.rt.client.ui.form.fields.IValueField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractLinkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@FormData(value = AbstractAddressBoxData.class, sdkCommand = SdkCommand.CREATE, defaultSubtypeSdkCommand = DefaultSubtypeSdkCommand.CREATE)
public abstract class AbstractAddressBox extends AbstractGroupBox {

	@Override
	protected boolean getConfiguredBorderVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected int getConfiguredGridColumnCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	protected int getConfiguredGridH() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	protected int getConfiguredGridW() {
		// TODO Auto-generated method stub
		return 1;
	}

	public LocationBox getLocationBox() {
		return getFieldByClass(LocationBox.class);
	}

	public CityField getCityField() {
		return getFieldByClass(CityField.class);
	}

	public CountryField getCountryField() {
		return getFieldByClass(CountryField.class);
	}

	public ShowOnMapButton getShowOnMapButton() {
		return getFieldByClass(ShowOnMapButton.class);
	}

	public ShowOnMapButtonBox getShowOnMapButtonBox() {
		return getFieldByClass(ShowOnMapButtonBox.class);
	}

	public StreetField getStreetField() {
		return getFieldByClass(StreetField.class);
	}

	@Order(1000)
	public class StreetField extends AbstractStringField {
		@Override
		protected String getConfiguredLabel() {
			return TEXTS.get("Street");
		}

		@Override
		protected void execChangedValue() {
			// TODO Auto-generated method stub
			verifyAllFields();
			;
		}
	}

	@Order(2000)
	public class LocationBox extends AbstractSequenceBox {
		@Override
		protected String getConfiguredLabel() {
			return TEXTS.get("Location");
		}

		@Override
		protected boolean getConfiguredAutoCheckFromTo() {
			return false;
		}

		@Order(1000)
		public class CityField extends AbstractStringField {
			@Override
			protected String getConfiguredLabel() {
				return TEXTS.get("City");
			}

			@Override
			protected byte getConfiguredLabelPosition() {
				// TODO Auto-generated method stub
				return LABEL_POSITION_ON_FIELD;
			}

			@Override
			protected void execChangedValue() {
				// TODO Auto-generated method stub
				verifyAllFields();
			}
		}

		@Order(2000)
		public class CountryField extends AbstractSmartField<String> {
			@Override
			protected String getConfiguredLabel() {
				return TEXTS.get("Country");
			}

			@Override
			protected void execChangedValue() {
				// TODO Auto-generated method stub
				verifyAllFields();
			}

			@Override
			protected byte getConfiguredLabelPosition() {
				// TODO Auto-generated method stub
				return LABEL_POSITION_ON_FIELD;
			}

			@Override
			protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
				// TODO Auto-generated method stub
				return CountryLookupCall.class;
			}
		}

	}

	@Order(3000)
	public class ShowOnMapButtonBox extends AbstractSequenceBox {

		@Order(1000)
		public class ShowOnMapButton extends AbstractLinkButton {
			@Override
			protected String getConfiguredLabel() {
				return TEXTS.get("ShowOnMap");
			}

			@Override
			protected String getConfiguredIconId() {
				// TODO Auto-generated method stub
				return Icons.World;
			}

			@Override
			protected Class<? extends IValueField> getConfiguredMasterField() {
				// TODO Auto-generated method stub
				return CountryField.class;
			}

			@Override
			protected boolean getConfiguredMasterRequired() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			protected boolean getConfiguredProcessButton() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			protected void execClickAction() {

				BEANS.get(MapHelper.class).showMapInNewWindow(getCountryField().getValue(), getCityField().getValue(),
						getStreetField().getValue());
			}
		}

	}

	protected void verifyAllFields() {
		boolean hasStreet = StringUtility.hasText(getStreetField().getValue());
		boolean hasCity = StringUtility.hasText(getCityField().getValue());

		getCityField().setMandatory(hasStreet);
		getCountryField().setMandatory(hasStreet || hasCity);
	}

}
