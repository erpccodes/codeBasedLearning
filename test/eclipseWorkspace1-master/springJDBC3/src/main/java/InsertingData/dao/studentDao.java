package InsertingData.dao;

import java.util.List;

import InsertingData.entity.Student;

public interface studentDao {
	
	public int insert(Student student);
	public int update(Student student);
	public Student getStudent(int mobNum);
	public List<Student> getAllStudent();

}
