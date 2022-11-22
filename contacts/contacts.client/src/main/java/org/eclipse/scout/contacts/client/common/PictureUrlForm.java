package org.eclipse.scout.contacts.client.common;

import org.eclipse.scout.contacts.client.common.PictureUrlForm.MainBox.CancelButton;
import org.eclipse.scout.contacts.client.common.PictureUrlForm.MainBox.InfoField;
import org.eclipse.scout.contacts.client.common.PictureUrlForm.MainBox.OkButton;
import org.eclipse.scout.contacts.client.common.PictureUrlForm.MainBox.UrlBox;
import org.eclipse.scout.contacts.client.common.PictureUrlForm.MainBox.UrlBox.UrlField;
import org.eclipse.scout.contacts.shared.Icons;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.htmlfield.AbstractHtmlField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.html.HTML;
import org.eclipse.scout.rt.platform.text.TEXTS;

public class PictureUrlForm extends AbstractForm {
	@Override
	protected String getConfiguredTitle() {
		// TODO [Client] verify translation
		return TEXTS.get("PictureUrl");
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public UrlBox getUrlBox() {
		return getFieldByClass(UrlBox.class);
	}

	public InfoField getInfoField() {
		return getFieldByClass(InfoField.class);
	}

	public UrlField getUrlField() {
		return getFieldByClass(UrlField.class);
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
		public class UrlBox extends AbstractGroupBox {

			@Order(1000)
			public class UrlField extends AbstractStringField {
				@Override
				protected boolean getConfiguredLabelVisible() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				protected boolean getConfiguredStatusVisible() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				protected int getConfiguredGridW() {
					// TODO Auto-generated method stub
					return 2;
				}
			}

		}

		@Order(2000)
		public class InfoField extends AbstractHtmlField {
			@Override
			protected boolean getConfiguredLabelVisible() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			protected boolean getConfiguredStatusVisible() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			protected int getConfiguredGridW() {
				// TODO Auto-generated method stub
				return 2;
			}

			@Override
			protected boolean getConfiguredGridUseUiHeight() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			protected void execInitField() {
				// TODO Auto-generated method stub
				setValue(HTML.fragment(HTML.icon(Icons.Info), HTML.bold(" " + TEXTS.get("PleaseNote") + ": "),
						TEXTS.get("SecurityUrlRestrictedMsg")).toHtml());
			}
		}

		@Order(3000)
		public class OkButton extends AbstractOkButton {

		}

		@Order(4000)
		public class CancelButton extends AbstractCancelButton {

		}
	}

	public void startModify() {
		startInternal(new ModifyHandler());
	}

	public class ModifyHandler extends AbstractFormHandler {

	}
}
