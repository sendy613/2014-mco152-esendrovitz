package sendrovitz.duplicates;

import java.util.HashMap;
import java.util.Map;

public class HowManyDuplicates {

	public static void main(String[] args) {
		
		String list[] = new String[] { "HAPPY", "BIRTHDAY", "TODAY", "IS", "BIRTHDAY", "IS", "YOURS", "HURRY"};
		
		Map<String, Integer> map = new HashMap<String, Integer>(); //HashMap is a class that exists in java and it implements the interface map

		for(String key : list){  //for each String s in list
			Integer value = map.get(key);
			if(value==null){
			map.put(key, 1);
		}
			else{
				map.put(key, value++); //this overwrites the original value and key that is there and adds one to the value
			}
	}

}
}