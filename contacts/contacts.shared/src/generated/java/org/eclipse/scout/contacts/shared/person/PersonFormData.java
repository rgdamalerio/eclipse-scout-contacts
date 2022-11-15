package org.eclipse.scout.contacts.shared.person;

import java.util.Date;

import javax.annotation.Generated;

import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications
 * recommended.
 */
@Generated(value = "org.eclipse.scout.contacts.client.person.PersonForm", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class PersonFormData extends AbstractFormData {
	private static final long serialVersionUID = 1L;

	public DateOfBirth getDateOfBirth() {
		return getFieldByClass(DateOfBirth.class);
	}

	public FirstName getFirstName() {
		return getFieldByClass(FirstName.class);
	}

	public GenderGroup getGenderGroup() {
		return getFieldByClass(GenderGroup.class);
	}

	public LastName getLastName() {
		return getFieldByClass(LastName.class);
	}

	/**
	 * access method for property PersonId.
	 */
	public String getPersonId() {
		return getPersonIdProperty().getValue();
	}

	/**
	 * access method for property PersonId.
	 */
	public void setPersonId(String personId) {
		getPersonIdProperty().setValue(personId);
	}

	public PersonIdProperty getPersonIdProperty() {
		return getPropertyByClass(PersonIdProperty.class);
	}

	public PictureUrl getPictureUrl() {
		return getFieldByClass(PictureUrl.class);
	}

	@ClassId("7c602360-9daa-44b8-abb6-94ccf9b9db59-formdata")
	public static class DateOfBirth extends AbstractValueFieldData<Date> {
		private static final long serialVersionUID = 1L;
	}

	@ClassId("359be835-439f-456e-9b0d-c832b034a298-formdata")
	public static class FirstName extends AbstractValueFieldData<String> {
		private static final long serialVersionUID = 1L;
	}

	@ClassId("b9d0593e-3938-4f97-bdca-fdb6a1ce1d77-formdata")
	public static class GenderGroup extends AbstractValueFieldData<String> {
		private static final long serialVersionUID = 1L;
	}

	@ClassId("8679ade5-21fb-470e-8f00-13bd15199101-formdata")
	public static class LastName extends AbstractValueFieldData<String> {
		private static final long serialVersionUID = 1L;
	}

	public static class PersonIdProperty extends AbstractPropertyData<String> {
		private static final long serialVersionUID = 1L;
	}

	@ClassId("617ffd40-0d69-4d02-b4f8-90c28c68c6ce-formdata")
	public static class PictureUrl extends AbstractValueFieldData<String> {
		private static final long serialVersionUID = 1L;
	}
}