package fr.idformation.evaluation.core.service.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.idformation.evaluation.core.domain.Customer;
import fr.idformation.evaluation.core.repository.IContactRepository;
import fr.idformation.evaluation.core.repository.ICustomerRepository;
import fr.idformation.evaluation.core.service.ICustomerService;

@Service
public class customerService implements ICustomerService {

	@Autowired
	private ICustomerRepository customerRep;

	@Autowired
	private IContactRepository contactRep;

	@Override
	public Set<Customer> findByNameStartingWith(String string) {
		return customerRep.findBylastNameStartingWith(string);
	}

	@Override
	public Optional<Customer> getOneCustomer(Integer id) {
		return customerRep.findById(id);
	}

	@Override
	public Boolean saveCustomer(Customer customer) {
		try {
			customerRep.save(customer);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}

	}

	@Override
	public boolean deleteCustomer(Optional<Customer> customer) {
		try {
			customerRep.delete(customer.get());
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}

	}

}
