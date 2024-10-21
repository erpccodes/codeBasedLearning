package collectionInjection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmployeeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("collectionInjection/collecConfig.xml");
		Employee employee=(Employee) applicationContext.getBean("Employee1");
		System.out.println(employee);

	}

}
