package sendrovitz.acm2;

import java.util.Scanner;

public class MaxSum {
	public int getMaxSum(int[][] array) {

		int sum = 0;
		int tempSum = 0;
		// goes as many times as array.length
		for (int i = 0; i < array.length; i++) {
			int size = i + 1;
			// for each times it will go through the whole array
			for (int j = 0; j < array.length; j++) {
				for (int k = 0; k < array[i].length; k++) {
					
					int[] tempArray = new int[array.length];

					// fills the temp array with the amount of numbers it is
					// currently testing
					if (k <= array.length - size) {
						for (int l = 0; l < size; l++) {
							tempArray[l] = array[j][k + l];
							tempSum += tempArray[l];

							if (tempSum > sum) {
								sum = tempSum;

							}
							for (int m = k + 1; m < array.length; m++) {
								tempArray[m] = array[j][m];
								tempSum += tempArray[m];
								if (tempSum > sum) {
									sum = tempSum;
									
								}
							}
							tempSum = 0;
						}
						if (tempSum > sum) {
							sum = tempSum;

						}
						//tempSum = 0;
					}
					if (j <= array.length - size) {
						//if (size != 1) {
							for (int l = 0; l < size; l++) {
								tempArray[l] = array[j + l][k];
								tempSum += tempArray[l];
								if (tempSum > sum) {
									sum = tempSum;

								}
								//tempSum = 0;
								for (int m = j + 1; m < array.length; m++) {
									
									tempArray[m] = array[m][k];
									tempSum += tempArray[m];
									if (tempSum > sum) {
										sum = tempSum;
										
									}
								}
								tempSum = 0;
							}
						//}
						if (tempSum > sum) {
							sum = tempSum;

						}

						tempSum = 0;
					}
				}
			}
		}
		return sum;
	}

	public static void main(String args[]) {
		Scanner keyboard = new Scanner(System.in);
		int size = keyboard.nextInt();
		int[][] array = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				array[i][j] = keyboard.nextInt();
			}
		}
		MaxSum sum = new MaxSum();
		System.out.println(sum.getMaxSum(array));
	}
}
