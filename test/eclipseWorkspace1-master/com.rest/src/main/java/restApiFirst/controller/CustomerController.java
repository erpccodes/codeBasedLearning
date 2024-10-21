package restApiFirst.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import restApiFirst.exception.CustomeremailAlreadyExistException;
import restApiFirst.exception.CustomeremailNotFoundException;
import restApiFirst.service.CustomerService;
import restApiFirst.model.Customer;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerservice;
	
	@PostMapping("customer/api/addcustomer")
	
	public ResponseEntity<?> addcustomer(@RequestBody Customer newcustomer) {
		try {
			Customer newcust = customerservice.addCustomer(newcustomer);
			return new ResponseEntity<Customer>(newcust,HttpStatus.CREATED);
		} catch (CustomeremailAlreadyExistException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("customer/api/getcustomers")
	
	public ResponseEntity<?> getcustomers(){
		List<Customer> customerlist = customerservice.getAllCustomer();
		return new ResponseEntity<List> (customerlist,HttpStatus.OK);
	}
	
	
	@GetMapping("customer/api/findcustomer/{cusemail}")
	
	public ResponseEntity<?> getgivencustomer(@PathVariable("cusemail") String email){
		Customer custres = customerservice.findbyCustomeremail(email);
		if(custres==null) {
			return new ResponseEntity<String>("customer email does not exist",HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Customer>(custres,HttpStatus.FOUND);
		}
	}
	
	@DeleteMapping("customer/api/delete/{custemail}")
	
	public ResponseEntity<?> deletecustomer(@PathVariable("custemail") String email){
		try {
			customerservice.deleteByEmail(email);
			return new ResponseEntity<String>("Customer data deleted",HttpStatus.OK);
		} catch (CustomeremailNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("customer/api/update")
	
	public ResponseEntity<?> updateCustomer(@RequestBody Customer custupd){
		try {
			Customer customerupdate = customerservice.updateCust(custupd);
			return new ResponseEntity<Customer>(customerupdate,HttpStatus.OK);
		} catch (CustomeremailNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
}