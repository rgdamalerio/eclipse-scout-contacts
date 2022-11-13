package org.eclipse.scout.contacts.client.person;

import org.eclipse.scout.contacts.client.common.CountryLookupCall;
import org.eclipse.scout.contacts.client.person.PersonTablePage.Table;
import org.eclipse.scout.contacts.client.person.PersonTablePage.Table.CityColumn;
import org.eclipse.scout.contacts.client.person.PersonTablePage.Table.CountryColumn;
import org.eclipse.scout.contacts.client.person.PersonTablePage.Table.EamilColumn;
import org.eclipse.scout.contacts.client.person.PersonTablePage.Table.FirstNameColumn;
import org.eclipse.scout.contacts.client.person.PersonTablePage.Table.LastNameColumn;
import org.eclipse.scout.contacts.client.person.PersonTablePage.Table.MobileColumn;
import org.eclipse.scout.contacts.client.person.PersonTablePage.Table.OrganizationColumn;
import org.eclipse.scout.contacts.client.person.PersonTablePage.Table.PersonIdColumn;
import org.eclipse.scout.contacts.client.person.PersonTablePage.Table.PhoneColumn;
import org.eclipse.scout.contacts.shared.person.IPersonService;
import org.eclipse.scout.contacts.shared.person.PersonTablePageData;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractSmartColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@Data(PersonTablePageData.class)
public class PersonTablePage extends AbstractPageWithTable<Table> {
	@Override
	protected boolean getConfiguredLeaf() {
		return true;
	}

	@Override
	protected void execLoadData(SearchFilter filter) {
		importPageData(BEANS.get(IPersonService.class).getPersonTableData(filter));
	}

	@Override
	protected String getConfiguredTitle() {
		// TODO [Administrator] verify translation
		return TEXTS.get("Person");
	}

	public class Table extends AbstractTable {

		public FirstNameColumn getFirstNameColumn() {
			return getColumnSet().getColumnByClass(FirstNameColumn.class);
		}

		public LastNameColumn getLastNameColumn() {
			return getColumnSet().getColumnByClass(LastNameColumn.class);
		}

		public CityColumn getCityColumn() {
			return getColumnSet().getColumnByClass(CityColumn.class);
		}

		public CountryColumn getCountryColumn() {
			return getColumnSet().getColumnByClass(CountryColumn.class);
		}

		public PhoneColumn getPhoneColumn() {
			return getColumnSet().getColumnByClass(PhoneColumn.class);
		}

		public MobileColumn getMobileColumn() {
			return getColumnSet().getColumnByClass(MobileColumn.class);
		}

		public EamilColumn getEamilColumn() {
			return getColumnSet().getColumnByClass(EamilColumn.class);
		}

		public OrganizationColumn getOrganizationColumn() {
			return getColumnSet().getColumnByClass(OrganizationColumn.class);
		}

		public PersonIdColumn getPersonIdColumn() {
			return getColumnSet().getColumnByClass(PersonIdColumn.class);
		}

		@Order(1)
		public class OrganizationColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Organization");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(2)
		public class EamilColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Email");
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

		@Order(3)
		public class MobileColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("MobilePhone");
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

		@Order(4)
		public class PhoneColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Phone");
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

		@Order(5)
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

		@Order(6)
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

		@Order(7)
		public class LastNameColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("LastName");
			}

			@Override
			protected int getConfiguredWidth() {
				return 120;
			}
		}

		@Order(8)
		public class FirstNameColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("FirstName");
			}

			@Override
			protected int getConfiguredWidth() {
				return 120;
			}
		}

		@Order(0)
		public class PersonIdColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("PersonId");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}

			@Override
			protected boolean getConfiguredDisplayable() {
				return false;
			}

			@Override
			protected boolean getConfiguredPrimaryKey() {
				return true;
			}
		}

	}
}
