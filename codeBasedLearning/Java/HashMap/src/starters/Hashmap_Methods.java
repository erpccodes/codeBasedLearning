package starters;
import java.util.HashMap;
import java.util.Map;

public class Hashmap_Methods {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Map<String,Integer> map=new HashMap<>();
		map.put("Himanshu", 12435);
		map.put("Amit", 12235);
		map.put("Akhil", 1215);
		map.put("Allu", 10005);
		map.put("Aman", 17775);
		map.put("Rishab",null);
		map.put("Ritik",null);

		
		//{Aman=17775, Akhil=1215, Amit=12235, Himanshu=12435, Allu=10005}
		System.out.println(map);   
		
		//true
		System.out.println(map.containsKey("Himanshu"));
		
		//true
		System.out.println(map.containsValue(17775));
		
		//12435
		System.out.println(map.get("Himanshu"));
		
		//null
		System.out.println(map.get("12435"));
		
		// [Aman, Akhil, Amit, Himanshu, Allu]
		System.out.println(map.keySet());
		
		
	}

}
