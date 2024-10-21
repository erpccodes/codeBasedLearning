package restApiFirst.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restApiFirst.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,String>{
	
}