package fr.idformation.evaluation.core.dto.mapper;

import java.util.HashSet;
import java.util.Set;

import fr.idformation.evaluation.core.domain.Contact;
import fr.idformation.evaluation.core.domain.Customer;
import fr.idformation.evaluation.core.dto.ContactDTO;

public class ContactMapper {

	/**
	 * Method that takes a set of contacts and converts it to a set of DTOs
	 *
	 * @param contacts, the set to be coverted
	 * @return a set of converted DTOs
	 */
	public static Set<ContactDTO> contactsToDTOs(Set<Contact> contacts) {
		Set<ContactDTO> contactDTOs = null;

		if (contacts != null) {
			contactDTOs = new HashSet<ContactDTO>();
			for (Contact contact : contacts) {
				contactDTOs.add(contactToDTO(contact));
			}
		}
		return contactDTOs;
	}

	/**
	 * Method that takes a Contact and converts it to a DTO
	 *
	 * @param contact the contact to be converted
	 * @return a contact DTO
	 */
	public static ContactDTO contactToDTO(Contact contact) {
		ContactDTO contactDTO = null;
		if (contact != null) {
			contactDTO = new ContactDTO();
			contactDTO.setId(contact.getId());
			contactDTO.setTypeContact(TypeContactMapper.typeContactToDTO(contact.getTypeContact()).getLabel());
			contactDTO.setValue(contact.getValue());
		}

		return contactDTO;
	}

	/**
	 * Method that takes a set of contact DTOs and converts it to a set of contacts
	 *
	 * @param contactDTOs the set to be converted
	 * @return a set of contacts
	 */
	public static Set<Contact> dTOsToContacts(Set<ContactDTO> contactDTOs, Customer customer) {
		Set<Contact> contacts = null;
		if (contactDTOs != null) {
			contacts = new HashSet<Contact>();
			for (ContactDTO contactDTO : contactDTOs) {
				contacts.add(dTOToContact(contactDTO, customer));
			}

		}
		return contacts;
	}

	/**
	 * Method that takes a contact DTO and converts it to a contact
	 *
	 * @param contactDTO the dto to be converted
	 * @return a contact
	 */
	public static Contact dTOToContact(ContactDTO contactDTO, Customer customer) {
		Contact contact = null;
		if (contactDTO != null) {
			contact = new Contact();
			contact.setTypeContact(TypeContactMapper.dTOToTypeContact(contactDTO.getTypeContact()));
			contact.setValue(contactDTO.getValue());
			for (Contact contactDB : customer.getContacts()) {
				if (contactDB.getTypeContact().equals(contact.getTypeContact())
						&& !contactDB.getValue().equals(contact.getValue())) {
					contact.setId(contactDB.getId());
				} else {
					contact.setId(contactDTO.getId());
				}
			}

			contact.setCustomer(customer);
		}
		return contact;
	}

}
