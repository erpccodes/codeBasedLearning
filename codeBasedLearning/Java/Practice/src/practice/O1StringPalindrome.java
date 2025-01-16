package practice;

// Checking palindrome of string ignoring case sesitivity and alphanumeric characters;
public class O1StringPalindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input="A man, a plan, a canal: Panama";
		System.out.println(isPalindrome(input));
	}

	public static boolean isPalindrome(String str) {
		boolean b=true;
		int r=str.length()-1;
		int i=0;
		
		while(i<r) {
			char c1=str.charAt(i);
			char c2=str.charAt(r);
			if(!(c1 >= 'a' && c1 <= 'z' || c1 >= 'A' && c1 <= 'Z' || c1 >= '0' && c1 <= '9')) {
				i++;
				continue;
			}
			if(!(c2 >= 'a' && c2 <= 'z' || c2 >= 'A' && c2 <= 'Z' || c2 >= '0' && c2 <= '9')) {
				r--;
				continue;
			}
			
			if(Character.toLowerCase(c1)!=Character.toLowerCase(c2)) {
				b=false;
				break;
			}
			i++;
			r--;
		}
		
		return b;
	}
}
