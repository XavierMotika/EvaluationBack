package fr.idformation.evaluation.mapper;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import fr.idformation.evaluation.core.domain.Contact;
import fr.idformation.evaluation.core.domain.Customer;
import fr.idformation.evaluation.core.domain.TypeContact;
import fr.idformation.evaluation.core.dto.ContactDTO;
import fr.idformation.evaluation.core.dto.mapper.ContactMapper;
import io.jsonwebtoken.lang.Assert;

@SpringBootTest
public class ContactMapperTest {

	@Test
	void defaultConstructorTestReturnsNotNull() {
		// when
		ContactMapper contactrMapperConstructor = new ContactMapper();
		// then
		Assert.isTrue(contactrMapperConstructor != null, "Should not be null, but it is");
	}

	@Test
	void nullContactToDTOReturnsNull() {
		// given
		Contact contact = null;
		// when
		ContactDTO contactDTO = ContactMapper.contactToDTO(contact);
		// then
		Assert.isTrue(contactDTO == null, "Should be null, but it isn't");
	}

	@Test
	void contactToDTOReturnsSameData() {
		// given
		Contact contact = new Contact();
		contact = newContactTest(contact);
		// when
		ContactDTO contactDTO = ContactMapper.contactToDTO(contact);
		// then
		Assert.isTrue(contactDTO.getValue() == contact.getValue(), "Should be the same, but it isn't");
		Assert.isTrue(contactDTO.getTypeContact() == contact.getTypeContact().getLabel(),
				"Should be the same, but it isn't");
	}

	@Test
	void nullDTOToContactsReturnsNull() {
		// given
		ContactDTO contactDTO = null;

		// when
		Contact contact = ContactMapper.dTOToContact(contactDTO, new Customer());
		// then
		Assert.isTrue(contact == null, "Should be null, but it isn't");
	}

	@Test
	void contactDTOToDTOReturnsSameData() {
		// given
		ContactDTO contactDTO = new ContactDTO();
		contactDTO.setTypeContact("Téléphone");
		// when
		Contact contact = ContactMapper.dTOToContact(contactDTO, new Customer());
		// then
		Assert.isTrue(contactDTO.getValue() == contact.getValue(), "Should be the same, but it isn't");
		Assert.isTrue(contactDTO.getTypeContact() == contact.getTypeContact().getLabel(),
				"Should be the same, but it isn't");
	}

	@Test
	void nullToDTOsReturnsNull() {
		// given
		Set<Contact> contacts = null;
		// when
		Set<ContactDTO> dtos = ContactMapper.contactsToDTOs(contacts);
		// then
		Assert.isTrue(dtos == null, "Should be null, but it isn't");
	}

	@Test
	void contactsToDTOsReturnsSameSize() {
		// given
		Set<Contact> contacts = new HashSet<>();
		contacts.add(newContactTest(new Contact()));
		contacts.add(newContactTest(new Contact()));
		contacts.add(newContactTest(new Contact()));
		// when
		Set<ContactDTO> dtos = ContactMapper.contactsToDTOs(contacts);
		// then
		Assert.notNull(dtos, "Should not be null, but it is");
		Assert.isTrue(contacts.size() == dtos.size(), "Should be the same size, but it isn't");

	}

	@Test
	void nullToContactsReturnsNull() {
		// given
		Set<ContactDTO> contactDTOs = null;
		// then
		Set<Contact> contacts = ContactMapper.dTOsToContacts(contactDTOs, new Customer());
		// then
		Assert.isTrue(contacts == null, "Should be null, but it isn't");

	}

	@Test
	void dTOsToContactsReturnsSameSize() {
		// given
		Set<ContactDTO> contactDTOs = new HashSet<>();
		contactDTOs.add(ContactMapper.contactToDTO(newContactTest(new Contact())));
		contactDTOs.add(ContactMapper.contactToDTO(newContactTest(new Contact())));
		contactDTOs.add(ContactMapper.contactToDTO(newContactTest(new Contact())));
		// when
		Set<Contact> contacts = ContactMapper.dTOsToContacts(contactDTOs, new Customer());
		// then
		Assert.notNull(contacts, "Should not be null, but it is");
		Assert.isTrue(contacts.size() == contactDTOs.size(), "Should be the same size, but it isn't");

	}

	/**
	 * Method that sets data to a contact.
	 *
	 * @param contact
	 * @return the same contact with datas
	 */
	public static Contact newContactTest(final Contact contact) {
		Contact newContact = null;
		if (contact != null) {
			newContact = new Contact();
			newContact.setId(0);
			newContact.setValue("value");
			newContact.setCustomer(new Customer());
			TypeContact type = new TypeContact();
			type.setId((short) 1);
			type.setLabel("Télépohone");
			newContact.setTypeContact(type);
		}

		return newContact;
	}

}
