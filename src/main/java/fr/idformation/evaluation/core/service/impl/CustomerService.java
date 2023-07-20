package fr.idformation.evaluation.core.service.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.idformation.evaluation.core.domain.Customer;
import fr.idformation.evaluation.core.repository.ICustomerRepository;
import fr.idformation.evaluation.core.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService {

	/**
	 * Query repository for the customer entity.
	 */
	@Autowired
	private ICustomerRepository customerRep;

	/**
	 * Searches for clients in the data base using a given string.
	 *
	 * @param pString the string to search the client's names with
	 * @return a filtered set of customer
	 */
	@Override
	public Set<Customer> findByNameStartingWith(final String pString) {
		return customerRep.findBylastNameStartingWith(pString);
	}

	/**
	 * Searches for a client in the data base using a given id.
	 *
	 * @param pId the id to find the customer with
	 * @return the corresponding customer
	 */
	@Override
	public Optional<Customer> getOneCustomer(final Integer pId) {
		return customerRep.findById(pId);
	}

	/**
	 * Saves a given customer in the data base.
	 *
	 * @param pCustomer the customer to be saved in the data base
	 * @return true if successful / false if not
	 */
	@Override
	public Boolean saveCustomer(final Customer pCustomer) {
		try {
			customerRep.save(pCustomer);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}

	}

	/**
	 * Deletes a given customer from the data base.
	 *
	 * @param pCustomer the customer to be deleted from the data base
	 * @return true if successful / false if not
	 */
	@Override
	public Boolean deleteCustomer(final Optional<Customer> pCustomer) {
		try {
			customerRep.delete(pCustomer.get());
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}

	}

}
