package org.eclipse.scout.contacts.server.organization;

import java.util.List;

import org.eclipse.scout.contacts.server.ServerSession;
import org.eclipse.scout.contacts.shared.organization.OrganizationLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;
import org.eclipse.scout.rt.testing.platform.runner.RunWithSubject;
import org.eclipse.scout.rt.testing.server.runner.RunWithServerSession;
import org.eclipse.scout.rt.testing.server.runner.ServerTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWithSubject("anonymous")
@RunWith(ServerTestRunner.class)
@RunWithServerSession(ServerSession.class)
public class OrganizationLookupCallTest {
	// TODO [Client] add test cases

	protected OrganizationLookupCall createLookupCall() {
		return new OrganizationLookupCall();
	}

	@Test
	public void testGetDataByAll() {
		OrganizationLookupCall call = createLookupCall();
		// TODO [Client] fill call
		List<? extends ILookupRow<String>> data = call.getDataByAll();
		// TODO [Client] verify data
	}

	@Test
	public void testGetDataByKey() {
		OrganizationLookupCall call = createLookupCall();
		// TODO [Client] fill call
		List<? extends ILookupRow<String>> data = call.getDataByKey();
		// TODO [Client] verify data
	}

	@Test
	public void testGetDataByText() {
		OrganizationLookupCall call = createLookupCall();
		// TODO [Client] fill call
		List<? extends ILookupRow<String>> data = call.getDataByText();
		// TODO [Client] verify data
	}
}
