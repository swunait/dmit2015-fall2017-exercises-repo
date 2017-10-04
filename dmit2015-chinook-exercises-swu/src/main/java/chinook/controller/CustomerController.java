package chinook.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.omnifaces.util.Messages;

import chinook.data.CustomerRepository;
import chinook.model.Customer;

@Model
public class CustomerController {
	
	private int currentSelectedCustomerId;		// getter/setter
	private Customer currentSelectedCustomer;	// getter
	
	public void findCustomer() {
		if( !FacesContext.getCurrentInstance().isPostback() ) {
			if( currentSelectedCustomerId > 0 ) {
				currentSelectedCustomer = customerRepository.find(currentSelectedCustomerId);
				if( currentSelectedCustomer == null ) {
					Messages.addGlobalInfo("There is no customer with customerID {0}", 
							currentSelectedCustomerId);
				} else {
					Messages.addGlobalInfo("Successfully retreived customer info.");
				}
			} else {
				Messages.addGlobalError("Bad request. A valid customerID is required.");
			}
		}		
	}

	@Inject
	private CustomerRepository customerRepository;
	
	private List<Customer> customers;
	
	@PostConstruct
	void init() {
		customers = customerRepository.findAll();
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public int getCurrentSelectedCustomerId() {
		return currentSelectedCustomerId;
	}

	public void setCurrentSelectedCustomerId(int currentSelectedCustomerId) {
		this.currentSelectedCustomerId = currentSelectedCustomerId;
	}

	public Customer getCurrentSelectedCustomer() {
		return currentSelectedCustomer;
	}
		
}
