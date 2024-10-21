package autoWireAnnotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DinnerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("autoWireAnnotation/autoWire.xml");
//		Menu dinnerList=applicationContext.getBean("menu",Menu.class);
//		System.out.println(dinnerList);
		
		Dinner dinner=applicationContext.getBean("dinner",Dinner.class);
		System.out.println(dinner);
	}

}
