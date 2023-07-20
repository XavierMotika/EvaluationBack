package fr.idformation.evaluation.mapper;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import fr.idformation.evaluation.core.domain.Contact;
import fr.idformation.evaluation.core.domain.Customer;
import fr.idformation.evaluation.core.dto.CustomerDTO;
import fr.idformation.evaluation.core.dto.mapper.CustomerMapper;
import io.jsonwebtoken.lang.Assert;

@SpringBootTest
public class CustomerMapperTest {

	@Test
	void defaultConstructorTestReturnsNotNull() {
		// when
		CustomerMapper customerMapperConstructor = new CustomerMapper();
		// then
		Assert.isTrue(customerMapperConstructor != null, "Should not be null, but it is");
	}

	@Test
	void nullCustomerToDTOReturnsNull() {
		// given
		Customer customer = null;
		// when
		CustomerDTO customerDTO = CustomerMapper.customerToDTO(customer, false);
		// then
		Assert.isTrue(customerDTO == null, "Should be null, but it isn't");
	}

	@Test
	void customerToDTOReturnsSameData() {
		// given
		Customer customer = new Customer();
		customer = newCustomerTest(customer);
		// when
		CustomerDTO customerDTO = CustomerMapper.customerToDTO(customer, false);
		// then
		Assert.isTrue(customerDTO.getId() == customer.getId(), "Should be the same, but it isn't");
		Assert.isTrue(customerDTO.getFirstName() == customer.getFirstName(), "Should be the same, but it isn't");
		Assert.isTrue(customerDTO.getLastName() == customer.getLastName(), "Should be the same, but it isn't");
		Assert.isTrue(customerDTO.getAdress() == customer.getAdress(), "Should be the same, but it isn't");
		Assert.isTrue(customerDTO.getZipCode() == customer.getZipCode(), "Should be the same, but it isn't");
		Assert.isTrue(customerDTO.getCountry() == customer.getCountry(), "Should be the same, but it isn't");
	}

	@Test
	void customerToDTOKeepsSameContacts() {
		// given
		Customer customer = new Customer();
		customer = newCustomerTest(customer);
		// when
		CustomerDTO customerDTO = CustomerMapper.customerToDTO(customer, true);
		// then
		Assert.notNull(customerDTO.getContacts(), "Should not be null, but it is");
		Assert.isTrue(customerDTO.getContacts().size() == customer.getContacts().size(),
				"Should be the same size, but it isn't");
	}

	@Test
	void nullDTOToCustomersReturnsNull() {
		// given
		CustomerDTO customerDTO = null;

		// when
		Customer customer = CustomerMapper.dTOToCustomer(customerDTO, null, false);
		// then
		Assert.isTrue(customer == null, "Should be null, but it isn't");
	}

	@Test
	void customerWOContactsDTOToDTOReturnsSameData() {
		// given

		Customer customer = new Customer();
		customer = newCustomerTest(customer);
		CustomerDTO customerDTO = CustomerMapper.customerToDTO(customer, false);
		// when
		customer = CustomerMapper.dTOToCustomer(customerDTO, customer, false);
		// then
		Assert.isTrue(customerDTO.getId() == customer.getId(), "Should be the same, but it isn't");
		Assert.isTrue(customerDTO.getFirstName() == customer.getFirstName(), "Should be the same, but it isn't");
		Assert.isTrue(customerDTO.getLastName() == customer.getLastName(), "Should be the same, but it isn't");
		Assert.isTrue(customerDTO.getAdress() == customer.getAdress(), "Should be the same, but it isn't");
		Assert.isTrue(customerDTO.getZipCode() == customer.getZipCode(), "Should be the same, but it isn't");
		Assert.isTrue(customerDTO.getCountry() == customer.getCountry(), "Should be the same, but it isn't");
	}

	@Test
	void customerWContactsDTOToDTOReturnsSameData() {
		// given
		CustomerDTO customerDTO = new CustomerDTO();
		Customer customer = new Customer();
		customer = newCustomerTest(customer);
		// when
		customer = CustomerMapper.dTOToCustomer(customerDTO, customer, true);
		// then
		Assert.isTrue(customerDTO.getId() == customer.getId(), "Should be the same, but it isn't");
		Assert.isTrue(customerDTO.getFirstName() == customer.getFirstName(), "Should be the same, but it isn't");
		Assert.isTrue(customerDTO.getLastName() == customer.getLastName(), "Should be the same, but it isn't");
		Assert.isTrue(customerDTO.getAdress() == customer.getAdress(), "Should be the same, but it isn't");
		Assert.isTrue(customerDTO.getZipCode() == customer.getZipCode(), "Should be the same, but it isn't");
		Assert.isTrue(customerDTO.getCountry() == customer.getCountry(), "Should be the same, but it isn't");
	}

	@Test
	void customerToDTOKeepsSameContacts2() {
		// given
		Customer customer = newCustomerTest(new Customer());
		customer.setContacts(new HashSet<Contact>());

		// when
		CustomerDTO customerDTO = CustomerMapper.customerToDTO(customer, true);
		// then
		Assert.notNull(customerDTO.getContacts(), "Should not be null, but it is");
		Assert.isTrue(customerDTO.getContacts().size() == customer.getContacts().size(),
				"Should be the same size, but it isn't");
	}

	@Test
	void nullToDTOsReturnsNull() {
		// given
		Set<Customer> customers = null;
		// when
		Set<CustomerDTO> dtos = CustomerMapper.customersToDTOs(customers);
		// then
		Assert.isTrue(dtos == null, "Should be null, but it isn't");
	}

	@Test
	void customersToDTOsReturnsSameSize() {
		// given
		Set<Customer> customers = new HashSet<Customer>();
		customers.add(newCustomerTest(new Customer()));
		customers.add(newCustomerTest(new Customer()));
		customers.add(newCustomerTest(new Customer()));
		// when
		Set<CustomerDTO> dtos = CustomerMapper.customersToDTOs(customers);
		// then
		Assert.notNull(dtos, "Should not be null, but it is");
		Assert.isTrue(dtos.size() == dtos.size(), "Should be the same size, but it isn't");

	}

	/**
	 * Method that set data to a customer.
	 *
	 * @param customer
	 * @return the same customer with datas
	 */
	public Customer newCustomerTest(final Customer customer) {
		Customer newCustomer = null;
		if (customer != null) {
			newCustomer = new Customer();
			newCustomer.setId(0);
			newCustomer.setFirstName("first name");
			newCustomer.setLastName("fast name");
			newCustomer.setAdress("adress");
			newCustomer.setZipCode("00000");
			newCustomer.setCountry("country");
			newCustomer.setContacts(new HashSet<Contact>());
			newCustomer.addContact(ContactMapperTest.newContactTest(new Contact()));
			newCustomer.addContact(ContactMapperTest.newContactTest(new Contact()));
			newCustomer.addContact(ContactMapperTest.newContactTest(new Contact()));
		}

		return newCustomer;
	}

}
