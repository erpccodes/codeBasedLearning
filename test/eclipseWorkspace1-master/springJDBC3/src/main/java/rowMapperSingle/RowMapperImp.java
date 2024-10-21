package rowMapperSingle;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;

import InsertingData.entity.Student;

public class RowMapperImp implements org.springframework.jdbc.core.RowMapper<Student>{



	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student student=new Student();
		student.setMobNum(rs.getInt(3));
		student.setEmail(rs.getString(2));
		student.setStudentName(rs.getString(1));
		return student;
	}
	
	
}
