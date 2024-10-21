package restApiFirst.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restApiFirst.exception.CustomeremailAlreadyExistException;
import restApiFirst.exception.CustomeremailNotFoundException;
import restApiFirst.repository.CustomerRepo;
import restApiFirst.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepo customerrepo;
	
	@Override
	public Customer addCustomer(Customer customer) throws CustomeremailAlreadyExistException {
		
		Optional<Customer> customeremail = customerrepo.findById(customer.getEmail());
		if(customeremail.isPresent()) {
			throw new CustomeremailAlreadyExistException("Duplicate customer email id");
		}
		else 
			customerrepo.save(customer);
		return customer;
	}

	@Override
	public Customer findbyCustomeremail(String email) {
		Optional<Customer> emailid = customerrepo.findById(email);
		if(emailid.isPresent()) {
			return emailid.get();
		}
		else {
		return null;
		}
	}

	@Override
	public List<Customer> getAllCustomer() {
		List<Customer> customers=customerrepo.findAll();
		return customers;
	}

	@Override
	public boolean deleteByEmail(String email) throws CustomeremailNotFoundException {
		Optional<Customer> emailid = customerrepo.findById(email);
		if(emailid.isPresent()) {
			customerrepo.delete(emailid.get());
			return true;
		}
		else {
			throw new CustomeremailNotFoundException("customer email id does not exist");
		}
		
	}

	@Override
	public Customer updateCust(Customer cust) throws CustomeremailNotFoundException {
			Optional<Customer> updcust = customerrepo.findById(cust.getEmail());
			if(updcust.isPresent()) {
				Customer customerfound=updcust.get();
				customerfound.setCustomerName(cust.getCustomerName());
				customerfound.setMobileNumber(cust.getMobileNumber());
				customerrepo.save(customerfound);
				return customerfound;
			}
			else {
				throw new CustomeremailNotFoundException("customer email does not exist");
			}
			

	}

}