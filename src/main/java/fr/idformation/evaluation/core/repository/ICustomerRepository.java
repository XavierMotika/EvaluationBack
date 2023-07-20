package fr.idformation.evaluation.core.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.idformation.evaluation.core.domain.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

	/**
	 * Searches for clients in the data base using a given string.
	 *
	 * @param pString the string to search the client's names with
	 * @return a filtered set of customer
	 */
	Set<Customer> findBylastNameStartingWith(String pString);

}
