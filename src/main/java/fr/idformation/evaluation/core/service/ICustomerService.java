package fr.idformation.evaluation.core.service;

import java.util.Optional;
import java.util.Set;

import fr.idformation.evaluation.core.domain.Customer;

public interface ICustomerService {

	Set<Customer> findByNameStartingWith(String string);

	Optional<Customer> getOneCustomer(Integer id);

	Boolean saveCustomer(Customer customer);

	boolean deleteCustomer(Optional<Customer> customer);

}
