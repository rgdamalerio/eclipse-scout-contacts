package org.eclipse.scout.contacts.server.person;

import org.eclipse.scout.contacts.server.sql.SQLs;
import org.eclipse.scout.contacts.shared.person.IPersonService;
import org.eclipse.scout.contacts.shared.person.PersonFormData;
import org.eclipse.scout.contacts.shared.person.PersonTablePageData;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class PersonService implements IPersonService {
	@Override
	public PersonTablePageData getPersonTableData(SearchFilter filter) {
		PersonTablePageData pageData = new PersonTablePageData();

		// TODO [Administrator] fill pageData.
		String sql = SQLs.PERSON_PAGE_SELECT + SQLs.PERSON_PAGE_DATA_SELECT_INTO;
		SQL.selectInto(sql, new NVPair("page", pageData));

		return pageData;
	}

	@Override
	public PersonFormData prepareCreate(PersonFormData formData) {
//		comment this for now to work with derby database
//		if (!ACCESS.check(new CreatePersonPermission())) {
//			throw new VetoException(TEXTS.get("AuthorizationFailed"));
//		}
		// TODO [Administrator] add business logic here.
		return formData;
	}

	@Override
	public PersonFormData create(PersonFormData formData) {
//		comment this for now to work with derby database
//		if (!ACCESS.check(new CreatePersonPermission())) {
//			throw new VetoException(TEXTS.get("AuthorizationFailed"));
//		}
		// TODO [Administrator] add business logic here.
		return formData;
	}

	@Override
	public PersonFormData load(PersonFormData formData) {
//		comment this for now to work with derby database
//		if (!ACCESS.check(new ReadPersonPermission())) {
//			throw new VetoException(TEXTS.get("AuthorizationFailed"));
//		}
		// TODO [Administrator] add business logic here.
		return formData;
	}

	@Override
	public PersonFormData store(PersonFormData formData) {
//		comment this for now to work with derby database
//		if (!ACCESS.check(new UpdatePersonPermission())) {
//			throw new VetoException(TEXTS.get("AuthorizationFailed"));
//		}
		// TODO [Administrator] add business logic here.
		return formData;
	}
}
