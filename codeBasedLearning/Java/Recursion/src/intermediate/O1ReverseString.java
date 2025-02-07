package intermediate;

public class O1ReverseString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="Hello!";
		//ReverseString(str);
		System.out.println(ReverseString(str));
	}
	
	public static String ReverseString(String str) {
		if(str==null || str.length()<=1 ) {
			return str;
		}
		
		return ReverseString(str.substring(1))+str.charAt(0);
	}

}
