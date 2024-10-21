package impQuestions;

public class ReverseWordByWord {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="Hello World!! You are in matrix";
		StringBuilder reverse=new StringBuilder();
		for(int i=str.length()-1;i>=0;i--) {
			reverse.append(str.charAt(i));
		}
		System.out.println(reverse.toString());
	}

}
