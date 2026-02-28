package o1_EmployeeApp;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Excersise2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
		List<Employee> employees = List.of(
			    new Employee(1, "Amit", "IT", 60000, null),
			    new Employee(2, "Ravi", "IT", 50000, 1),
			    new Employee(3, "Neha", "HR", 45000, null),
			    new Employee(4, "Priya", "HR", 40000, 3),
			    new Employee(5, "Karan", "Finance", 70000, null)
			);
		
		
		
		// Exercise 1: Sort employees by salary
		System.out.println("Exercise 1----------------------------------------------------------");		
		employees.stream().sorted(Comparator.comparing(Employee::getSalary)).forEach(System.out::println);
		System.out.println("--------------------------------------------------------");

		employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).forEach(System.out::println);
		System.out.println("--------------------------------------------------------");
		employees.stream().sorted(Comparator.comparing((Employee x) -> x.getSalary())).forEach(System.out::println);

		System.out.println("********************************************************");


	}

}
