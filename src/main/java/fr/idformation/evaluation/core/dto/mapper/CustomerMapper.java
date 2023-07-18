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
	 * DTOs
	 *
	 * @param customers the filtered customer set
	 * @return a set of customer DTOs
	 */
	public static Set<CustomerDTO> customersToDTOs(Set<Customer> customers) {
		Set<CustomerDTO> customerDTOs = null;

		if (customers != null) {
			customerDTOs = new HashSet<>();
			for (Customer customer : customers) {
				customerDTOs.add(customerToDTO(customer, true));
			}
		}

		return customerDTOs;
	}

	/**
	 * Method that take a Customer and converts it to a DTO
	 *
	 * @param customer to be converted
	 * @return a DTO of the given customer
	 */
	public static CustomerDTO customerToDTO(Customer customer, boolean addContacts) {
		CustomerDTO customerDTO = null;

		if (customer != null) {
			customerDTO = new CustomerDTO();
			customerDTO.setId(customer.getId());
			customerDTO.setFirstName(customer.getFirstName());
			customerDTO.setLastName(customer.getLastName());
			customerDTO.setAdress(customer.getAdress());
			customerDTO.setZipCode(customer.getZipCode());
			customerDTO.setCountry(customer.getCountry());
			if (addContacts) {
				Set<ContactDTO> contacts = new HashSet<ContactDTO>();
				for (ContactDTO contactDTO : ContactMapper.contactsToDTOs(customer.getContacts())) {
					contacts.add(contactDTO);
				}
				customerDTO.setContacts(contacts);
			}

		}

		return customerDTO;
	}

	public static Customer dTOToCustomer(CustomerDTO customerDTO, Customer customer, boolean addContacts) {
		if (customerDTO != null) {
			customer.setId(customerDTO.getId());
			customer.setFirstName(customerDTO.getFirstName());
			customer.setLastName(customerDTO.getLastName());
			customer.setAdress(customerDTO.getAdress());
			customer.setZipCode(customerDTO.getZipCode());
			customer.setCountry(customerDTO.getCountry());
			if (addContacts) {
				Set<Contact> contacts = new HashSet<Contact>();
				for (Contact contact : ContactMapper.dTOsToContacts(customerDTO.getContacts(), customer)) {
					for (Contact contactDB : customer.getContacts()) {
						if (contact.getTypeContact() == contactDB.getTypeContact() && contact.getValue() == ""
								|| contact.getValue() == null) {
							contacts.remove(contactDB);
						} else {
							contacts.add(contact);
						}

					}
				}
				customer.setContacts(contacts);
			}

		}

		return customer;
	}

}
