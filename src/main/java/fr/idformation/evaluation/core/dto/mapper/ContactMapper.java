package fr.idformation.evaluation.core.dto.mapper;

import java.util.HashSet;
import java.util.Set;

import fr.idformation.evaluation.core.domain.Contact;
import fr.idformation.evaluation.core.domain.Customer;
import fr.idformation.evaluation.core.dto.ContactDTO;

public class ContactMapper {

	/**
	 * Method that takes a set of contacts and converts it to a set of DTOs.
	 *
	 * @param pContacts the set to be coverted
	 * @return a set of converted DTOs
	 */
	public static Set<ContactDTO> contactsToDTOs(final Set<Contact> pContacts) {
		Set<ContactDTO> contactDTOs = null;

		if (pContacts != null) {
			contactDTOs = new HashSet<ContactDTO>();
			for (Contact contact : pContacts) {
				contactDTOs.add(contactToDTO(contact));
			}
		}
		return contactDTOs;
	}

	/**
	 * Method that takes a Contact and converts it to a DTO.
	 *
	 * @param pContact the contact to be converted
	 * @return a contact DTO
	 */
	public static ContactDTO contactToDTO(final Contact pContact) {
		ContactDTO contactDTO = null;
		if (pContact != null) {
			contactDTO = new ContactDTO();
			contactDTO.setId(pContact.getId());
			contactDTO.setTypeContact(TypeContactMapper.typeContactToDTO(pContact.getTypeContact()).getLabel());
			contactDTO.setValue(pContact.getValue());
		}

		return contactDTO;
	}

	/**
	 * Method that takes a set of contact DTOs and converts it to a set of contacts.
	 *
	 * @param pContactDTOs the set to be converted
	 * @param pCustomer    the customer from the data base
	 * @return a set of contacts
	 */
	public static Set<Contact> dTOsToContacts(final Set<ContactDTO> pContactDTOs, final Customer pCustomer) {
		Set<Contact> contacts = null;
		if (pContactDTOs != null) {
			contacts = new HashSet<Contact>();
			for (ContactDTO contactDTO : pContactDTOs) {
				contacts.add(dTOToContact(contactDTO, pCustomer));
			}

		}
		return contacts;
	}

	/**
	 * Method that takes a contact DTO and converts it to a contact.
	 *
	 * @param pContactDTO the dto to be converted
	 * @param pCustomer   the customer from the data base
	 * @return a contact
	 */
	public static Contact dTOToContact(final ContactDTO pContactDTO, final Customer pCustomer) {
		Contact contact = null;
		if (pContactDTO != null) {
			contact = new Contact();
			contact.setTypeContact(TypeContactMapper.dTOToTypeContact(pContactDTO.getTypeContact()));
			contact.setValue(pContactDTO.getValue());
			for (Contact contactDB : pCustomer.getContacts()) {
				if (contactDB.getTypeContact().equals(contact.getTypeContact())
						&& !contactDB.getValue().equals(contact.getValue())) {
					contact.setId(contactDB.getId());
				} else {
					contact.setId(pContactDTO.getId());
				}
			}

			contact.setCustomer(pCustomer);
		}
		return contact;
	}

}
