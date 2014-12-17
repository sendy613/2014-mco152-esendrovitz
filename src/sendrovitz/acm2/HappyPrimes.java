
package sendrovitz.acm2;

import java.util.ArrayList;
import java.util.Scanner;

public class HappyPrimes {
	public Boolean getIsHappyPrime(Integer num) {
		if (isPrime(num) && isHappy(num)) {
			return true;
		}
		return false;
	}

	public Boolean isPrime(Integer num) {
		if (num == 1) {
			return false;
		}
		for (int i = 2; i < num; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	public Boolean isHappy(Integer num) {


		String s = num.toString();
		int length = s.length();
		Integer sum = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(num);
		
		do {
			sum = 0;
			for (int i = 0; i < length; i++) {
				sum += Integer.parseInt(s.substring(i, i + 1)) * Integer.parseInt(s.substring(i, i + 1));
			}
			s = sum.toString();
			length = s.length();
			if(!list.contains(sum)){
				list.add(sum);
			}
			
			else{	
				return false;
			
			}

		} while (sum.compareTo(1) != 0);

		return true;
	}

	public static void main(String args[]) {
		HappyPrimes happy = new HappyPrimes();
		StringBuilder builder = new StringBuilder();
		Scanner input = new Scanner(System.in);
		int sets = input.nextInt();
		for (int i = 0; i < sets; i++) {
			int setNum = input.nextInt();
			int num = input.nextInt();
			builder.append(setNum);
			builder.append(" ");
			builder.append(num);
			builder.append(" ");
			if (happy.getIsHappyPrime(num)) {
				builder.append("YES");
			} else {
				builder.append("NO");
			}
			if (i != sets - 1) {
				builder.append("\n");
			}
		}
		System.out.println(builder.toString());
	}
}