/*
 * Problem:
You need to construct a dynamic SQL query where multiple conditions are appended,
 and performance is a priority.
 */

package string_vs_stringbuilder_vs_stringbuffer;

public class StringBuilder_Mutability {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String tableName = "users";
        StringBuilder query = new StringBuilder();
        
        // Start building the query
        query.append("SELECT * FROM ");
        query.append(tableName);
        query.append(" WHERE 1=1");
        
        // Add dynamic conditions
        String userName = "JohnDoe";
        if (userName != null && !userName.isEmpty()) {
            query.append(" AND username = '").append(userName).append("'");
        }
        
        int age = 25;
        if (age > 0) {
            query.append(" AND age > ").append(age);
        }
        
        // Output the final query
        System.out.println(query.toString());
    }

}


/*
 * Why StringBuilder?
You need to modify the string (SQL query) dynamically with several append operations.
StringBuilder is mutable and more efficient for such operations compared to String, 
especially when concatenation is inside a loop or involves multiple steps.
It is not synchronized, which makes it faster for single-threaded use cases.

 * */
