package sendrovitz.acm;

import java.util.Scanner;

public class RepeatingCharacters {

	public static void main(String[] args) {
		int numDataSets;
		int dataSetNum;
		int repeatCount=0;
		int counter =0;
		StringBuilder output = new StringBuilder();
		Scanner keyboard = new Scanner(System.in);
		numDataSets = keyboard.nextInt();
		
		while(counter<numDataSets){
		dataSetNum = keyboard.nextInt();
		repeatCount = keyboard.nextInt();
		//keyboard.next();
		String s = keyboard.next();
		char[] c = s.toCharArray();
		output.append(dataSetNum);
		output.append(" ");
		for(int i=0; i<c.length; i++){
			for(int j=0; j<repeatCount; j++){
				output.append(c[i]);
			}
		}
		output.append("\n");
		counter++;
		}
		System.out.println(output.toString());
	}
	

}
