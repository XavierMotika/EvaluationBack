package fr.idformation.evaluation.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.idformation.evaluation.core.domain.Customer;

@Repository
public interface IContactRepository extends JpaRepository<Customer, Integer> {

}
