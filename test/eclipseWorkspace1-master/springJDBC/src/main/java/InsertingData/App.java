package InsertingData;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Program Started" );
    
   ApplicationContext applicationContext=new ClassPathXmlApplicationContext("InsertingData/config.xml");
   JdbcTemplate template=applicationContext.getBean("jdbcTemplate",JdbcTemplate.class);
   
//   insert query
   String query1="create table if not exists demo1(studentName varchar(20),email varchar(20),mobNum int)";
   String query2="insert into demo1(studentName,email,mobNum) values(?,?,?)";
   String query3="select * from demo1";
   
   
   // fire query
   template.execute(query1);
   Scanner sc=new Scanner(System.in);
   System.out.println("Enter student Name");
   String name=sc.nextLine();
   System.out.println("Enter student email");
   String email=sc.nextLine();
   System.out.println("Enter student Phone number");
   int mobile=sc.nextInt();
   template.update(query2,name,email,mobile);
   System.out.println("Number of records inserted...");  
   template.execute(query3);
}
}