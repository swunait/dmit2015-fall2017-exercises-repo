package chinook.service;

import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;

import chinook.data.CustomerRepository;
import chinook.model.Customer;

@Stateful
public class CustomerService {

	@Inject
	private CustomerRepository customerRepository;
	
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

//	public List<Customer> findAllOrderByCompanyName() {
//		return customerRepository.findAllOrderByCompany();
//	}
	
	public Customer findOne(int customerId) {
		return customerRepository.find(customerId);
	}
}
