package starters;

import java.util.HashMap;
import java.util.Map;

// The majority element is the element that appears more than n / 2 times.
//You may assume that the majority element always exists in the array.

public class HighestFreqKey {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			int[] array= {2,2,1,1,1,2,2};
			System.out.println(majorityElement(array));
	}
	
	
	 public static int majorityElement(int[] nums) {
	        Map<Integer,Integer> map=new HashMap<>();
	        int target=nums.length/2;
	        // finding frequency of each element
	        for(int num: nums){
	            map.put(num,map.getOrDefault(num,0)+1);
	        }
	        // check if the key has frequency more than n/2
	        for(int key: map.keySet() ){
	            if(map.get(key)>target){
	                return key;
	            }
	        }
	        return -1;
	    }

}
