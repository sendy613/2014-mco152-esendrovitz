package sendrovitz.acm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PenneyGame {
	public static void main(String args[]) {

		StringBuilder output = new StringBuilder();
		Scanner keyboard = new Scanner(System.in);
		int numDataSets = keyboard.nextInt();
		int counter = 0;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		while (counter < numDataSets) {
			map.put("TTT", 0);
			map.put("TTH", 0);
			map.put("THT", 0);
			map.put("THH", 0);
			map.put("HHH", 0);
			map.put("HTH", 0);
			map.put("HTT", 0);
			map.put("HHT", 0);
			int dataSetNum = keyboard.nextInt();
			keyboard.nextLine();
			String sequence = keyboard.nextLine();
			char[] c = sequence.toCharArray();
			
			//int tTT = 0;
			//int tTH = 0;
			//int tHT = 0;
			//int tHH = 0;
			//int hHH = 0;
			//int hTH = 0;
			//int hTT = 0;
			//int hHT = 0;

			for (int i = 0; i < c.length - 2; i++) {
				StringBuilder s = new StringBuilder();
				s.append(c[i]);
				s.append(c[i + 1]);
				s.append(c[i + 2]);
				Integer value = map.get(s.toString());
				map.put(s.toString(), ++value);

//				switch (s.toString()) {
//
//				case "TTT":
//					tTT++;
//					break;
//				case "TTH":
//					tTH++;
//					break;
//				case "THT":
//					tHT++;
//					break;
//				case "THH":
//					tHH++;
//					break;
//				case "HHH":
//					hHH++;
//					break;
//				case "HTH":
//					hTH++;
//					break;
//				case "HTT":
//					hTT++;
//					break;
//				case "HHT":
//					hHT++;
//					break;
//				}
			}
			if(dataSetNum!=1){
			output.append("\n");
			}
			output.append(dataSetNum);
			output.append(" ");
			output.append(map.get("TTT"));
			output.append(" ");
			output.append(map.get("TTH"));
			output.append(" ");
			output.append(map.get("THT"));
			output.append(" ");
			output.append(map.get("THH"));
			output.append(" ");
			output.append(map.get("HTT"));
			output.append(" ");
			output.append(map.get("HTH"));
			output.append(" ");
			output.append(map.get("HHT"));
			output.append(" ");
			output.append(map.get("HHH"));
			

			counter++;
			
		}
		System.out.println(output.toString());

	}
}