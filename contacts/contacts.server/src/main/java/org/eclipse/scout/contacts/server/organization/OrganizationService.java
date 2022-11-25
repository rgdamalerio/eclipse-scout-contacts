package org.eclipse.scout.contacts.server.organization;

import java.util.UUID;

import org.eclipse.scout.contacts.server.sql.SQLs;
import org.eclipse.scout.contacts.shared.organization.CreateOrganizationPermission;
import org.eclipse.scout.contacts.shared.organization.IOrganizationService;
import org.eclipse.scout.contacts.shared.organization.OrganizationFormData;
import org.eclipse.scout.contacts.shared.organization.OrganizationTablePageData;
import org.eclipse.scout.contacts.shared.organization.ReadOrganizationPermission;
import org.eclipse.scout.contacts.shared.organization.UpdateOrganizationPermission;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.security.ACCESS;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class OrganizationService implements IOrganizationService {
	@Override
	public OrganizationTablePageData getOrganizationTableData(SearchFilter filter) {
		OrganizationTablePageData pageData = new OrganizationTablePageData();

		// TODO [Administrator] fill pageData.
		String sql = SQLs.ORGANIZATION_PAGE_SELECT + SQLs.ORGANIZATION_PAGE_DATA_SELECT_INTO;
		SQL.selectInto(sql, new NVPair("page", pageData));

		return pageData;
	}

	@Override
	public OrganizationFormData create(OrganizationFormData formData) {
		if (!ACCESS.check(new CreateOrganizationPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [Client] add business logic here.
		if (StringUtility.isNullOrEmpty(formData.getOrganizationId())) {
			formData.setOrganizationId(UUID.randomUUID().toString());
		}

		SQL.insert(SQLs.ORGANIZATION_INSERT, formData);

		return store(formData);
	}

	@Override
	public OrganizationFormData load(OrganizationFormData formData) {
		if (!ACCESS.check(new ReadOrganizationPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [Client] add business logic here.
		SQL.selectInto(SQLs.ORGANIZATION_SELECT, formData);

		return formData;
	}

	@Override
	public OrganizationFormData store(OrganizationFormData formData) {
		if (!ACCESS.check(new UpdateOrganizationPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [Client] add business logic here.

		SQL.update(SQLs.ORGANIZATION_UPDATE, formData);

		return formData;
	}
}
