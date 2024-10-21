package asssignmentLibrary;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class MemberAccount {
	
	String Name;
	int AccountNo;
	Book[] borrowed;

	Connection connection;
	public void borrowBooks() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");

		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Library_Management","root", "Prince@9928");
	
	Statement stmt=connection.createStatement();

	Scanner sc=new Scanner(System.in);
	while(true) {
		System.out.println("Enter the member name : ");
		String Name=sc.next();
		System.out.println("Enter the account number : ");
		int AccountNo=sc.nextInt();
		System.out.println("Enter the book isbn number : ");
		int BookIsbnNo=sc.nextInt();

		String query=String.format("insert into MemberAccount values('%s','%d','%d')", Name,AccountNo,BookIsbnNo);
		stmt.executeUpdate(query);
		System.out.println("Book has been added to your account");

		System.out.println("Do you want to add another book [yes/no]?");
		String flag=sc.next();
		if(flag.equalsIgnoreCase("no")) {
			break;
		}
	}

	}
	public String returnBooks(String BookIsbnNo) throws Exception {
		Scanner sc=new Scanner(System.in);

		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Library_Management","root", "Prince@9928");
		Statement stmt=connection.createStatement();
		
		String sql_query=String.format("delete from MemberAccount where BookIsbnNo = ('%s')", BookIsbnNo);
		int i=stmt.executeUpdate(sql_query);

		if(i==0) {
			System.out.println("Removing of book from account failed");
		}
		else {
			System.out.println("Removed succesfully");
		}

		return BookIsbnNo;

	}
	
	public void memberdetails() throws Exception{
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Library_Management","root", "Prince@9928");
		Statement stmt=connection.createStatement();
		String sql_query="Select * from MemberAccount";
		ResultSet rs=stmt.executeQuery(sql_query);
		if(rs!=null) {
			while(rs.next()) {
				System.out.println(rs.getString(1)+"\t\t\t"+rs.getInt(2)+"\t\t\t"+rs.getInt(3));
			}
		}
	}

}
