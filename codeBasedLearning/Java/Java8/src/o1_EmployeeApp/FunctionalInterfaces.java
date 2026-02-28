package o1_EmployeeApp;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionalInterfaces {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Employee> employees = List.of(
			    new Employee(1, "Amit", "IT", 60000, null),
			    new Employee(2, "Ravi", "IT", 50000, 1),
			    new Employee(3, "Neha", "HR", 45000, null),
			    new Employee(4, "Priya", "HR", 40000, 3),
			    new Employee(5, "Karan", "Finance", 70000, null)
			);
		
		
		// Exercise 1:  Predicate<Employee> → salary > 50k
		System.out.println("Exercise 1----------------------------------------------------------");
		
		//way1
		List<Employee> salaryfilter=employees.stream().filter(x -> x.getSalary()>50000).collect(Collectors.toList());
		System.out.println(salaryfilter);
		
		//way2
		Stream<Employee> salaryfilter2=employees.stream().filter(x -> x.getSalary()>50000);
		salaryfilter2.forEach(System.out::println);
		
		//way3
		Predicate<Employee> pred=e-> e.getSalary()>50000;
		employees.stream().filter(pred).forEach(System.out::println);

		
		System.out.println("********************************************************");
		
		// Exercise 2: Consumer<Employee> → print name
		System.out.println("Exercise 2----------------------------------------------------------");
		
		employees.stream().map(x-> x.getName()+" ").forEach(System.out::print);
		
		System.out.println("\n");
		
		//Using Method referencing
		employees.stream().map(Employee::getName).forEach(System.out::println);
		
		System.out.println("********************************************************");
		
		// Exercise 3: Function<Employee, String> → print name
		System.out.println("Exercise 3----------------------------------------------------------");
		Function<Employee,String> department= x ->x.getDepartment();
		employees.stream().map(department).forEach(System.out::println);
		System.out.println("********************************************************");
		
		// Exercise 4: Supplier<String> → generate UUID
		System.out.println("Exercise 4----------------------------------------------------------");
		
		Supplier<String> uuid=()->UUID.randomUUID().toString();
		System.out.println(uuid.get());
		System.out.println(uuid.get());
		System.out.println("********************************************************");
		
		// Exercise 5: Get names of IT employees
				System.out.println("Exercise 5----------------------------------------------------------");		
		employees.stream().filter(x->x.getDepartment().equals("IT")).map(x->x.getName()).forEach(System.out::println);
		System.out.println("********************************************************");
		
		
		
		// Exercise 6: Convert this into single List<String>
		System.out.println("Exercise 6----------------------------------------------------------");		
		List<List<String>> teams = List.of(
			    List.of("Amit", "Ravi"),
			    List.of("Neha", "Priya"),
			    List.of("Karan")
			);
		
		List<String> teamList=teams.stream().flatMap(x->x.stream()).collect(Collectors.toList());
		List<String> teamList2=teams.stream().flatMap(List::stream).collect(Collectors.toList());
		System.out.println(teamList);
		System.out.println(teamList2);
		
		System.out.println("********************************************************");

		
		
	}

}
