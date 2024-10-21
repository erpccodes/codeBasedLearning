package impQuestions;
public class ReverseWords {

/*	Not correct approach since 
 *  String concatenation using += inside a loop creates multiple intermediate String objects,
 *   which is inefficient in Java since String is immutable.
 * 
 * So we should use StringBuilder here
 *
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="Hello World!! You are in matrix";
		String reverse="";
		String[] arr=str.split(" ");
		int n=arr.length-1;
		for(int i=n;i>=0;i--) {
			if(i!=n) {
				reverse+=" ";
			}
			reverse+=arr[i];
		}
		System.out.println(reverse);
	}
}
*/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="Hello World!! You are in matrix";
		StringBuilder reverse=new StringBuilder();
		String[] arr=str.split(" ");
		int n=arr.length-1;
		for(int i=n;i>=0;i--) {
			if(i!=n) {
				reverse.append(" ");
			}
			reverse.append(arr[i]);
		}
		System.out.println(reverse);
	}
}