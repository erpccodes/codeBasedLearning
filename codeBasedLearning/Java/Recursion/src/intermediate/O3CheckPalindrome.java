package intermediate;

public class O3CheckPalindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        // Test cases
        System.out.println(isPalindrome("madam"));     // true
        System.out.println(isPalindrome("level"));     // true
        System.out.println(isPalindrome("hello"));     // false
        System.out.println(isPalindrome("racecar"));   // true
        System.out.println(isPalindrome("abcba"));     // true
        System.out.println(isPalindrome("abcd"));      // false
	}
	
	public static boolean isPalindrome(String str) {
		
		
		if(str.length()<=1 || str==null)
			return true;
		
		if(str.charAt(0)!=str.charAt(str.length()-1))
			return false;
	
		return isPalindrome(str.substring(1,str.length()-1));
	}
}