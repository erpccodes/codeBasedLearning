package collectionInjection;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Employee {
public Properties getProp() {
		return prop;
	}
	public void setProp(Properties prop) {
		this.prop = prop;
	}
private int employeeId;
private List<String> phones;
private Map<String,String> courses;
private Set<String> address;
private Properties prop;



public int getEmployeeId() {
	return employeeId;
}
public void setEmployeeId(int employeeId) {
	this.employeeId = employeeId;
}
public List<String> getPhones() {
	return phones;
}
public void setPhones(List<String> phones) {
	this.phones = phones;
}
public Map<String, String> getCourses() {
	return courses;
}
public void setCourses(Map<String, String> courses) {
	this.courses = courses;
}
public Set<String> getAddress() {
	return address;
}
public void setAddress(Set<String> address) {
	this.address = address;
}

public Employee(int employeeId, List<String> phones, Map<String, String> courses, Set<String> address,
		Properties prop) {
	super();
	this.employeeId = employeeId;
	this.phones = phones;
	this.courses = courses;
	this.address = address;
	this.prop = prop;
}
public Employee() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Employee [employeeId=" + employeeId + ", phones=" + phones + ", courses=" + courses + ", address=" + address
			+ ", credentials=" + prop + "]";
}

}
