package springjdbc;

public class Student {
private String studentName;
private String email;
private int mobNum;
public String getStudentName() {
	return studentName;
}
public void setStudentName(String studentName) {
	this.studentName = studentName;
}
public String getEmail() {
	return email;
}
@Override
public String toString() {
	return "Student [studentName=" + studentName + ", email=" + email + ", mobNum=" + mobNum + "]";
}
public Student() {
	super();
	// TODO Auto-generated constructor stub
}
public Student(String studentName, String email, int mobNum) {
	super();
	this.studentName = studentName;
	this.email = email;
	this.mobNum = mobNum;
}
public void setEmail(String email) {
	this.email = email;
}
public int getMobNum() {
	return mobNum;
}
public void setMobNum(int mobNum) {
	this.mobNum = mobNum;
}
}