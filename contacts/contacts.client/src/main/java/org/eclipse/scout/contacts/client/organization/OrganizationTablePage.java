package org.eclipse.scout.contacts.client.organization;

import java.util.Set;

import org.eclipse.scout.contacts.client.common.CountryLookupCall;
import org.eclipse.scout.contacts.client.organization.OrganizationTablePage.Table;
import org.eclipse.scout.contacts.shared.organization.IOrganizationService;
import org.eclipse.scout.contacts.shared.organization.OrganizationTablePageData;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractSmartColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.form.FormEvent;
import org.eclipse.scout.rt.client.ui.form.FormListener;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@Data(OrganizationTablePageData.class)
public class OrganizationTablePage extends AbstractPageWithTable<Table> {
	@Override
	protected boolean getConfiguredLeaf() {
		return true;
	}

	@Override
	protected void execLoadData(SearchFilter filter) {
		importPageData(BEANS.get(IOrganizationService.class).getOrganizationTableData(filter));
	}

	@Override
	protected String getConfiguredTitle() {
		// TODO [Administrator] verify translation
		return TEXTS.get("Organizations");
	}

	public class Table extends AbstractTable {

		@Override
		protected Class<? extends IMenu> getConfiguredDefaultMenu() {
			return EditMenu.class;
		}

		@Order(1000)
		public class EditMenu extends AbstractMenu {
			@Override
			protected String getConfiguredText() {
				return TEXTS.get("Edit");
			}

//			@Override
//			protected Set<? extends IMenuType> getConfiguredMenuTypes() {
//				return CollectionUtility.hashSet(TableMenuType.SingleSelection, TableMenuType.MultiSelection);
//			}

			@Override
			protected void execAction() {
				OrganizationForm form = new OrganizationForm();
				form.setOrganizationId(getOrganizationIdColumn().getSelectedValue());
				form.addFormListener(new OrganizationFormListener());
				// start the form using its modify handler
				form.startModify();
			}

		}

		@Order(2000)
		public class NewMenu extends AbstractMenu {
			@Override
			protected String getConfiguredText() {
				return TEXTS.get("New");
			}

			@Override
			protected Set<? extends IMenuType> getConfiguredMenuTypes() {
				return CollectionUtility.<IMenuType>hashSet(TableMenuType.EmptySpace, TableMenuType.SingleSelection);
			}

			@Override
			protected void execAction() {
				OrganizationForm form = new OrganizationForm();
				form.addFormListener(new OrganizationFormListener());
				// start the form using its new handler
				form.startNew();
			}
		}

		private class OrganizationFormListener implements FormListener {

			@Override
			public void formChanged(FormEvent e) {
				// TODO Auto-generated method stub
				// reload page to reflect new/changed data after saving any changes
				if (FormEvent.TYPE_CLOSED == e.getType() && e.getForm().isFormStored()) {
					reloadPage();
				}
			}

		}

		public CityColumn getCityColumn() {
			return getColumnSet().getColumnByClass(CityColumn.class);
		}

		public CountryColumn getCountryColumn() {
			return getColumnSet().getColumnByClass(CountryColumn.class);
		}

		public HomepageColumn getHomepageColumn() {
			return getColumnSet().getColumnByClass(HomepageColumn.class);
		}

		public NameColumn getNameColumn() {
			return getColumnSet().getColumnByClass(NameColumn.class);
		}

		public OrganizationIdColumn getOrganizationIdColumn() {
			return getColumnSet().getColumnByClass(OrganizationIdColumn.class);
		}

		@Order(1000)
		public class OrganizationIdColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("OrganizationID");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}

			@Override
			protected boolean getConfiguredDisplayable() {
				return false;
			}
		}

		@Order(2000)
		public class NameColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Name0");
			}

			@Override
			protected int getConfiguredWidth() {
				return 120;
			}
		}

		@Order(3000)
		public class CityColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("City");
			}

			@Override
			protected int getConfiguredWidth() {
				return 120;
			}
		}

		@Order(4000)
		public class CountryColumn extends AbstractSmartColumn<String> {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Country");
			}

			@Override
			protected int getConfiguredWidth() {
				return 120;
			}

			@Override
			protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
				return CountryLookupCall.class;
			}
		}

		@Order(5000)
		public class HomepageColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Homepage");
			}

			@Override
			protected int getConfiguredWidth() {
				return 120;
			}

			@Override
			protected boolean getConfiguredDisplayable() {
				return false;
			}
		}

	}
}
