package org.eclipse.scout.contacts.server;

import org.eclipse.scout.contacts.shared.CreatePersonPermission;
import org.eclipse.scout.contacts.shared.IPersonService;
import org.eclipse.scout.contacts.shared.PersonFormData;
import org.eclipse.scout.contacts.shared.ReadPersonPermission;
import org.eclipse.scout.contacts.shared.UpdatePersonPermission;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.security.ACCESS;

public class PersonService implements IPersonService {
	@Override
	public PersonFormData prepareCreate(PersonFormData formData) {
		if (!ACCESS.check(new CreatePersonPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [Administrator] add business logic here.
		return formData;
	}

	@Override
	public PersonFormData create(PersonFormData formData) {
		if (!ACCESS.check(new CreatePersonPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [Administrator] add business logic here.
		return formData;
	}

	@Override
	public PersonFormData load(PersonFormData formData) {
		if (!ACCESS.check(new ReadPersonPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [Administrator] add business logic here.
		return formData;
	}

	@Override
	public PersonFormData store(PersonFormData formData) {
		if (!ACCESS.check(new UpdatePersonPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [Administrator] add business logic here.
		return formData;
	}
}
