package fr.idformation.evaluation.core.dto.mapper;

import java.util.HashSet;
import java.util.Set;

import fr.idformation.evaluation.core.domain.Contact;
import fr.idformation.evaluation.core.domain.Customer;
import fr.idformation.evaluation.core.dto.ContactDTO;
import fr.idformation.evaluation.core.dto.CustomerDTO;

public class CustomerMapper {

	/**
	 * Method that takes a set of filtered customers and converts it in a set of
	 * DTOs.
	 *
	 * @param pCustomers the filtered customer set
	 * @return a set of customer DTOs
	 */
	public static Set<CustomerDTO> customersToDTOs(final Set<Customer> pCustomers) {
		Set<CustomerDTO> customerDTOs = null;

		if (pCustomers != null) {
			customerDTOs = new HashSet<>();
			for (Customer customer : pCustomers) {
				customerDTOs.add(customerToDTO(customer, true));
			}
		}

		return customerDTOs;
	}

	/**
	 * Method that take a Customer and converts it to a DTO.
	 *
	 * @param pCustomer    to be converted
	 * @param pAddContacts if the contact are to be added or not
	 * @return a DTO of the given customer
	 */
	public static CustomerDTO customerToDTO(final Customer pCustomer, final boolean pAddContacts) {
		CustomerDTO customerDTO = null;

		if (pCustomer != null) {
			customerDTO = new CustomerDTO();
			customerDTO.setId(pCustomer.getId());
			customerDTO.setFirstName(pCustomer.getFirstName());
			customerDTO.setLastName(pCustomer.getLastName());
			customerDTO.setAdress(pCustomer.getAdress());
			customerDTO.setZipCode(pCustomer.getZipCode());
			customerDTO.setCountry(pCustomer.getCountry());
			if (pAddContacts) {
				Set<ContactDTO> contacts = new HashSet<ContactDTO>();
				for (ContactDTO contactDTO : ContactMapper.contactsToDTOs(pCustomer.getContacts())) {
					contacts.add(contactDTO);
				}
				customerDTO.setContacts(contacts);
			}

		}

		return customerDTO;
	}

	/**
	 *
	 * @param pCustomerDTO
	 * @param pCustomer
	 * @param pAddContacts
	 * @return customer the modified customer
	 */
	public static final Customer dTOToCustomer(final CustomerDTO pCustomerDTO, final Customer pCustomer,
			final boolean pAddContacts) {
		Customer newCustomer = null;
		if (pCustomer != null) {
			newCustomer = pCustomer;
		} else {
			newCustomer = new Customer();
		}
		if (pCustomerDTO != null) {
			newCustomer.setId(pCustomerDTO.getId());
			newCustomer.setFirstName(pCustomerDTO.getFirstName());
			newCustomer.setLastName(pCustomerDTO.getLastName());
			newCustomer.setAdress(pCustomerDTO.getAdress());
			newCustomer.setZipCode(pCustomerDTO.getZipCode());
			newCustomer.setCountry(pCustomerDTO.getCountry());
			if (pAddContacts) {
				Set<Contact> contacts = new HashSet<Contact>();
				for (Contact contact : ContactMapper.dTOsToContacts(pCustomerDTO.getContacts(), pCustomer)) {
					for (Contact contactDB : pCustomer.getContacts()) {
						if (contact.getTypeContact() == contactDB.getTypeContact() && contact.getValue() == ""
								|| contact.getValue() == null) {
							contacts.remove(contactDB);
						} else {
							contacts.add(contact);
						}

					}
				}
				newCustomer.setContacts(contacts);
			}

		}

		return newCustomer;
	}

}
