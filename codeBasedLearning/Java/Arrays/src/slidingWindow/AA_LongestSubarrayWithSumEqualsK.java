package slidingWindow;

// Brute force approach
public class AA_LongestSubarrayWithSumEqualsK {

	// Find Longest subarray with sum <= K
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		int[] arr= {2,5,1,7,10};
		int n=arr.length;
		int k=14;
		int sum=0,maxlength=0;
		
		for(int i=0;i<n;i++) {
			sum=0;
			for(int j=i;j<n;j++) {
				sum+=arr[j];
				if(sum<=k) {
					maxlength=Math.max(maxlength, j-i+1);
				}else {
					break;
				}
			}
		}
		
		System.out.println(maxlength);
	}
}
