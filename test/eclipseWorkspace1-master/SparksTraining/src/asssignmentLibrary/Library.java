package asssignmentLibrary;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Library {
	
	String LibraryName;
    String Address;
	String LibraryRegno;
	Book[] books;
    
    
    public Book[] getBooks() {
		return books;
	}
	public void setBooks(Book[] books) {
		this.books = books;
	}
	
	public void showAllBooks() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Library_Management","root", "Prince@9928");
		Statement stmt=(Statement) connection.createStatement();
		String sql_query="Select * from Book";
		ResultSet rs=((java.sql.Statement) stmt).executeQuery(sql_query);
		if(rs!=null) {
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3));
			}
		}
	}

}
