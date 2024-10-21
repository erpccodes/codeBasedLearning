package springjdbc;

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
    
   ApplicationContext applicationContext=new ClassPathXmlApplicationContext("springjdbc/config.xml");
   JdbcTemplate template=applicationContext.getBean("jdbcTemplate",JdbcTemplate.class);
   
//   insert query
   String query="insert into info(studentName,email,mobNum) values(?,?,?)";
   
   // fire query
   int result=template.update(query,"Himanshu","erhimanshu",3243535);
 System.out.println("Number of records inserted..."+result);  
}
}