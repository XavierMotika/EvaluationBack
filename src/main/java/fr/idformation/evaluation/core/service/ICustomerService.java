package fr.idformation.evaluation.core.service;

import java.util.Optional;
import java.util.Set;

import fr.idformation.evaluation.core.domain.Customer;

public interface ICustomerService {

	/**
	 * Searches for clients in the data base using a given string.
	 *
	 * @param pString the string to search the client's names with
	 * @return a filtered set of customer
	 */
	Set<Customer> findByNameStartingWith(String pString);

	/**
	 * Searches for a client in the data base using a given id.
	 *
	 * @param pId the id to find the customer with
	 * @return the corresponding customer
	 */
	Optional<Customer> getOneCustomer(Integer pId);

	/**
	 * Saves a given customer in the data base.
	 *
	 * @param pCustomer the customer to be saved in the data base
	 * @return true if successful / false if not
	 */
	Boolean saveCustomer(Customer pCustomer);

	/**
	 * Deletes a given customer from the data base.
	 *
	 * @param pCustomer the customer to be deleted from the data base
	 * @return true if successful / false if not
	 */
	Boolean deleteCustomer(Optional<Customer> pCustomer);

}
