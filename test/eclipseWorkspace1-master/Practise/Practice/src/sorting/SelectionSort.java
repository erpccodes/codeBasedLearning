package sorting;

public class SelectionSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

			int arr[]=new int[] {7,3,1,2,8};     
			for(int i=0;i<arr.length-1;i++) {    
				int min=i;
				for(int j=i+1;j<arr.length;j++) {           
					if(arr[j]<arr[min])    {   
						min=j;
					}
				}   
				int temp=arr[i];
				arr[i]=arr[min];
				arr[min]=temp;   
//				System.out.println(min);  
			}
			for(int k=0;k<arr.length;k++)
				System.out.println(arr[k]);
	}

}
