package starters;

import java.util.HashMap;
import java.util.Map;

public class FrequencyOfArrayElements {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Map<String, Integer> map=new HashMap<>();
		String[] words = {"apple", "banana", "apple", "orange", "banana", "apple"};
		for(String word:words) {
			map.put(word,map.getOrDefault(word, 0)+1);
		}
		System.out.println(map);
	}

}
