package InsertingData.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import InsertingData.entity.Student;



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
	}
	public int update(Student student) {
		String query="update demo1 set studentName=?,email=? where mobNum=?";
		   int r= this.jdbcTemplate.update(query,student.getStudentName(),student.getEmail(),student.getMobNum());

		return r;
	}

}
