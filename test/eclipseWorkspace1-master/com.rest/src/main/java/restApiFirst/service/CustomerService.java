package restApiFirst.service;

import java.util.List;

import restApiFirst.exception.CustomeremailAlreadyExistException;
import restApiFirst.exception.CustomeremailNotFoundException;
import restApiFirst.model.Customer;



public interface CustomerService {

	public Customer addCustomer(Customer customer) throws CustomeremailAlreadyExistException;
	public Customer findbyCustomeremail(String email);
	
	public List<Customer> getAllCustomer();
	public boolean deleteByEmail(String email) throws CustomeremailNotFoundException;
	public Customer updateCust(Customer cust) throws CustomeremailNotFoundException;
	
}