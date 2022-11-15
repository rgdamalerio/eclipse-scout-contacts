package org.eclipse.scout.contacts.shared;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IPersonService extends IService {
	PersonFormData prepareCreate(PersonFormData formData);

	PersonFormData create(PersonFormData formData);

	PersonFormData load(PersonFormData formData);

	PersonFormData store(PersonFormData formData);
}
