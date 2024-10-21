package impQuestions;

public class PalindromeCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String str="abcdedcba";
		int n=str.length();
		boolean isPalindrome=true;
		for(int i=0;i<n/2;i++) {
			if(!(str.charAt(i)==str.charAt(n-i-1))) {
				isPalindrome=false;
				break;
			}
		}
		if(isPalindrome) {
			System.out.println("String is Palindrome");
		}else
			System.out.println("String is not Palindrome");
		
	}

}
