package fr.idformation.evaluation.core.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.idformation.evaluation.core.domain.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

	Set<Customer> findBylastNameStartingWith(String string);

}
