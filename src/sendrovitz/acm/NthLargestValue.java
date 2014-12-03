package sendrovitz.acm;

import java.util.Arrays;
import java.util.Scanner;

public class NthLargestValue {

	public static void main(String[] args) {
		StringBuilder output = new StringBuilder();
		Scanner keyboard = new Scanner(System.in);
		int numDataSets = keyboard.nextInt();
		int dataSetNum;
		int[] array = new int[10];
		int counter=0;
		while(counter<numDataSets){
			dataSetNum= keyboard.nextInt();
			for(int i=0; i<array.length; i++){
				array[i]= keyboard.nextInt();
			}
			Arrays.sort(array);
			output.append(dataSetNum);
			output.append(" ");
			output.append(array[7]);
			output.append("\n");
			counter++;
			}
    System.out.println(output.toString());
    
	}

}
