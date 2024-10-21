package rowMapperMulti;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import InsertingData.dao.studentDao;
import InsertingData.dao.studentDaoImp;
import InsertingData.entity.Student;

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
  
   studentDao dao=applicationContext.getBean("studentDao",studentDao.class);
   	
   List<Student> student=dao.getAllStudent();
   for(Student st:student)
   System.out.println(st);
}
}