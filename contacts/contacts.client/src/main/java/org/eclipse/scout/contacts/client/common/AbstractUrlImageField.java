package org.eclipse.scout.contacts.client.common;

import java.util.Set;

import org.eclipse.scout.contacts.shared.Icons;
import org.eclipse.scout.contacts.shared.common.AbstractUrlImageFieldData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.dto.FormData.DefaultSubtypeSdkCommand;
import org.eclipse.scout.rt.client.dto.FormData.SdkCommand;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.ImageFieldMenuType;
import org.eclipse.scout.rt.client.ui.form.fields.imagefield.AbstractImageField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.platform.util.StringUtility;

@FormData(value = AbstractUrlImageFieldData.class, sdkCommand = SdkCommand.CREATE, defaultSubtypeSdkCommand = DefaultSubtypeSdkCommand.CREATE)
public abstract class AbstractUrlImageField extends AbstractImageField {

	private String url;

	@FormData
	public String getUrl() {
		return url;
	}

	@FormData
	public void setUrl(String url) {
		this.url = url;
		updateImage();
	}

	@Override
	protected boolean getConfiguredLabelVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected int getConfiguredGridH() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	protected boolean getConfiguredAutoFit() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected String getConfiguredImageId() {
		// TODO Auto-generated method stub
		return Icons.PersonSolid;
	}

	protected void updateImage() {
		setImageUrl(this.url);
	}

	@Order(1000)
	public class EditURLMenu extends AbstractMenu {
		@Override
		protected String getConfiguredText() {
			return TEXTS.get("EditURL");
		}

		@Override
		protected void execAction() {

			PictureUrlForm form = new PictureUrlForm();
			String oldUrl = getUrl();

			if (StringUtility.hasText(oldUrl)) {
				form.getUrlField().setValue(oldUrl);
			}

			form.startModify();
			form.waitFor();
		}

		@Override
		protected Set<? extends IMenuType> getConfiguredMenuTypes() {
			// TODO Auto-generated method stub
			return CollectionUtility.<IMenuType>hashSet(ImageFieldMenuType.ImageUrl, ImageFieldMenuType.ImageId,
					ImageFieldMenuType.Null);
		}
	}

}
