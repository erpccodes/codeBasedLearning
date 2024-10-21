package firstSpring;

import javax.naming.Context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.SpringVersion;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext applicationContext=new ClassPathXmlApplicationContext("config.xml");
    	Student student1=(Student) applicationContext.getBean("Student1");
    	Student student2=(Student) applicationContext.getBean("Student2");
    	System.out.println(student1);
    	System.out.print(student2);
    	
    } 
    
    
    
    }
