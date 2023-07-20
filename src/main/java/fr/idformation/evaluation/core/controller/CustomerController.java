package fr.idformation.evaluation.core.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.idformation.evaluation.ConstantList;
import fr.idformation.evaluation.core.domain.Customer;
import fr.idformation.evaluation.core.dto.CustomerDTO;
import fr.idformation.evaluation.core.dto.mapper.CustomerMapper;
import fr.idformation.evaluation.core.service.ICustomerService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/GestionClient")
@CrossOrigin(origins = { "http://localhost:3000" }, maxAge = ConstantList.MAX_AGE)

public class CustomerController {

	/**
	 *
	 */
	@Autowired
	private ICustomerService customerService;

	/**
	 * Method that searchs for a filtered list of customer (using a name filter).
	 *
	 * @param string the string sequence the customer is searched for
	 * @return the set of filterd Customer DTO
	 */
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@GetMapping("/")
	public Set<CustomerDTO> findByNameStartingWith(@RequestParam("search") final String string) {
		return CustomerMapper.customersToDTOs(customerService.findByNameStartingWith(string));
	}

	/**
	 * Method that edit a customer from the database. It takes data coming from the
	 * request body after filling a form. Checks if the customer exists or not, send
	 * a not found error if not / updates it if it exists.
	 *
	 * @param id          the customer corresponding id
	 * @param customerDTO the dto containing data from the front app
	 * @param response    the error message response
	 * @return true or false depending on the succes or not of the method
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/edit/{id}")
	public boolean updateCustomer(@PathVariable final Integer id, @RequestBody final CustomerDTO customerDTO,
			final HttpServletResponse response) {
		Optional<Customer> customer = customerService.getOneCustomer(id);
		if (customer == null || !customer.isPresent()) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		} else {
			return customerService.saveCustomer(CustomerMapper.dTOToCustomer(customerDTO, customer.get(), true));
		}
	}

	/**
	 * Method that searches for one specific customer in the data base. Returns a
	 * DTO if found, or null and an error if not.
	 *
	 * @param id       the customer'id to be searched with
	 * @param response the error message response
	 * @return a dto or null
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}")
	public CustomerDTO getOneCustomer(@PathVariable final Integer id, final HttpServletResponse response) {
		Optional<Customer> customer = customerService.getOneCustomer(id);
		if (customer == null || !customer.isPresent()) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} else {
			return (CustomerMapper.customerToDTO(customer.get(), true));
		}
	}

	/**
	 * Method that delete a customer from the database. Checks if the customer
	 * exists or not, send a not found error if not / deletes it if it exists.
	 *
	 * @param id       the customer corresponding id
	 * @param response the error message response
	 * @return true or false depending on the succes or not of the method
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public boolean deleteCustomer(@PathVariable final Integer id, final HttpServletResponse response) {
		Optional<Customer> customer = customerService.getOneCustomer(id);
		if (customer == null || !customer.isPresent()) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		} else {
			return customerService.deleteCustomer(customer);
		}
	}

}
