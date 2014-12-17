package sendrovitz.acm2;

import java.util.Scanner;

public class RascalTriangle {
	public int getValue(int n, int m){
		//n,m= m*(n-m)+1. need to multiply m by n-m rows and then add 1 because it started with 1.'
		int value =( m*(n-m))+1;
		return value;
		
	}

	public static void main(String args[]) {
		Scanner keyboard = new Scanner(System.in);
		StringBuilder builder = new StringBuilder();
		int dataSets = keyboard.nextInt();
	    for(int i=0; i<dataSets; i++){
	    int set = keyboard.nextInt();
		int n = keyboard.nextInt();
		int m = keyboard.nextInt();
		RascalTriangle triangle = new RascalTriangle();
		builder.append(set);
		builder.append(" ");
		builder.append(triangle.getValue(n,m));
		if(i<dataSets-1){
			builder.append("\n");
				}
		}
		System.out.println(builder.toString());
	}
}
