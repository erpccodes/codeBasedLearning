package InsertingData.dao;

import java.util.List;

import javax.swing.tree.RowMapper;

import org.springframework.jdbc.core.JdbcTemplate;

import InsertingData.entity.Student;
import rowMapperSingle.RowMapperImp;



public class studentDaoImp implements studentDao {

	private JdbcTemplate jdbcTemplate;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int insert(Student student) {
		// TODO Auto-generated method stub
		String query="insert into demo1(studentName,email,mobNum) values(?,?,?)";
		   int r= this.jdbcTemplate.update(query,student.getStudentName(),student.getEmail(),student.getMobNum());

		return r;
		
	// 
		
	}
	public int update(Student student) {
		String query="update demo1 set studentName=?,email=? where mobNum=?";
		   int r= this.jdbcTemplate.update(query,student.getStudentName(),student.getEmail(),student.getMobNum());

		return r;
	}
	public Student getStudent(int mobNum) {
		String query="select * from demo1 where mobNum=?";
		org.springframework.jdbc.core.RowMapper<Student> rowMapper=new RowMapperImp();
		 Student student=this.jdbcTemplate.queryForObject(query,rowMapper,mobNum);
		 return student;
	}
	public List<Student> getAllStudent() {
		String query="select * from demo1";
		List<Student> student= this.jdbcTemplate.query(query, new RowMapperImp());
		return student;
	}


}
