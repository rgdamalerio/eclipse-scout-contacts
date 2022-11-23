package org.eclipse.scout.contacts.client;

import java.util.List;

import org.eclipse.scout.contacts.client.contact.ContactOutline;
import org.eclipse.scout.contacts.client.person.PersonForm;
import org.eclipse.scout.contacts.client.search.SearchOutline;
import org.eclipse.scout.contacts.shared.Icons;
import org.eclipse.scout.rt.client.ui.action.keystroke.IKeyStroke;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.desktop.AbstractDesktop;
import org.eclipse.scout.rt.client.ui.desktop.notification.NativeNotificationDefaults;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutlineViewButton;
import org.eclipse.scout.rt.client.ui.desktop.outline.IOutline;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;

/**
 * @author Administrator
 */
public class Desktop extends AbstractDesktop {

	public Desktop() {

	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("ApplicationTitle");
	}

	@Override
	protected String getConfiguredLogoId() {
		return "application_logo";
	}

	@Override
	protected NativeNotificationDefaults getConfiguredNativeNotificationDefaults() {
		return super.getConfiguredNativeNotificationDefaults().withIconId("application_logo.png");
	}

	@Override
	protected List<Class<? extends IOutline>> getConfiguredOutlines() {
		return CollectionUtility.<Class<? extends IOutline>>arrayList(ContactOutline.class, SearchOutline.class);
	}

	@Override
	protected void execDefaultView() {
		selectFirstVisibleOutline();
	}

	protected void selectFirstVisibleOutline() {
		for (IOutline outline : getAvailableOutlines()) {
			if (outline.isEnabled() && outline.isVisible()) {
				setOutline(outline.getClass());
				return;
			}
		}
	}

	@Order(1000)
	public class ContactOutlineViewButton extends AbstractOutlineViewButton {

		public ContactOutlineViewButton() {
			this(ContactOutline.class);
		}

		protected ContactOutlineViewButton(Class<? extends ContactOutline> outlineClass) {
			super(Desktop.this, outlineClass);
		}

		@Override
		protected String getConfiguredKeyStroke() {
			return IKeyStroke.F2;
		}
	}

	@Order(2000)
	public class SearchOutlineViewButton extends AbstractOutlineViewButton {

		public SearchOutlineViewButton() {
			this(SearchOutline.class);
		}

		protected SearchOutlineViewButton(Class<? extends SearchOutline> outlineClass) {
			super(Desktop.this, outlineClass);
		}

		@Override
		protected DisplayStyle getConfiguredDisplayStyle() {
			return DisplayStyle.TAB;
		}

		@Override
		protected String getConfiguredKeyStroke() {
			return IKeyStroke.F3;
		}
	}

	public class QuickAccessMenu extends AbstractMenu {

		@Override
		protected String getConfiguredText() {
			return TEXTS.get("QuickAccess");
		}

		@Order(10)
		@ClassId("effb3b69-f488-4aed-8923-d430a5f1fd97")
		public class NewPersonMenu extends AbstractMenu {
			@Override
			protected String getConfiguredText() {
				return TEXTS.get("NewPersonMenu");
			}

			@Override
			protected void execAction() {
				new PersonForm().startNew();
			}
		}

	}

	public class OptionsMenu extends AbstractMenu {

		@Override
		protected String getConfiguredText() {
			return TEXTS.get("Options");
		}

		@Override
		protected String getConfiguredIconId() {
			return Icons.Gear;
		}

	}

	public class UserMenu extends AbstractMenu {

		@Override
		protected String getConfiguredIconId() {
			return Icons.PersonSolid;
		}

	}

}
