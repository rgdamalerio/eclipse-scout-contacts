package org.eclipse.scout.contacts.server.organization;

import org.eclipse.scout.contacts.shared.organization.IOrganizationService;
import org.eclipse.scout.contacts.shared.organization.OrganizationTablePageData;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class OrganizationService implements IOrganizationService {
	@Override
	public OrganizationTablePageData getOrganizationTableData(SearchFilter filter) {
		OrganizationTablePageData pageData = new OrganizationTablePageData();
		// TODO [Administrator] fill pageData.
		return pageData;
	}
}
