package slidingWindow;

//Find Longest subarray with sum <= K with better sol
public class AB_LongestSubarrayWithSumEqualsKBetterSol {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] arr= {2,5,1,7,10};
		int n=arr.length;
		int k=14;
		int l=0,r=0;
		int sum=0,maxlength=0;
		
		while(r<n) {
			sum+=arr[r];
			
			 while(sum>k) {
				sum-=arr[l];
				l++;
			 }
				maxlength=Math.max(maxlength, r-l+1);
			r++;
		}
		System.out.println(maxlength);
	}
}
