package o1_EmployeeApp;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Employee> employees = List.of(
			    new Employee(1, "Amit", "IT", 60000, null),
			    new Employee(2, "Ravi", "IT", 50000, 1),
			    new Employee(3, "Neha", "HR", 45000, null),
			    new Employee(4, "Priya", "HR", 40000, 3),
			    new Employee(5, "Karan", "Finance", 70000, null)
			);
		
		employees.stream().filter(x->x.getSalary()>50000).forEach(System.out::println);;
		
		Runnable task = () ->System.out.println("Running task");
		Thread thread =new Thread(task);
		thread.start();
		
		
		
	}

}
