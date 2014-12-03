package sendrovitz.vendingmachine;

public class Money {

	private int numDollars;
	private int numQuarters;
	private int numDimes;
	private int numNickles;

	public Money() {

	}

	public Money(int numDollars, int numQuarters, int numDimes, int numNickles) {
		this.numDollars = numDollars;
		this.numQuarters = numQuarters;
		this.numDimes = numDimes;
		this.numNickles = numNickles;
	}

	public void add(Money money) {
		if (money.numDollars > 0) {
			numDollars += money.numDollars;
		}
		if (money.numQuarters > 0) {
			numQuarters += money.numQuarters;
		}
		if (money.numDimes > 0) {
			numDimes += money.numDimes;
		}
		if (money.numNickles > 0) {
			numNickles += money.numNickles;
		}
	}

	public Money remove(double amount) throws NotEnoughChangeException {
		if (amount > this.getTotal()) {
			throw new NotEnoughChangeException();
		} else {
			Money a = new Money();
			double left = Math.round(amount * 100.0) / 100.0;
			while (left >= 1 && numDollars > 0) {
				a.numDollars++;
				numDollars -= 1;
				left = Math.round((left - 1) * 100.0) / 100.0;
			}
			while (left >= .25 && numQuarters > 0) {
				a.numQuarters++;
				numQuarters -= 1;
				left = Math.round((left - .25) * 100.0) / 100.0;
			}
			while (left >= .1 && numDimes > 0) {
				a.numDimes++;
				numDimes -= 1;
				left = Math.round((left - .1) * 100.0) / 100.0;
			}
			while (left >= .05 && numNickles > 0) {
				a.numNickles++;
				numNickles -= 1;
				left = Math.round((left - .05 )* 100.0) / 100.0;
			}
			if (left > 0) {
				throw new NotEnoughChangeException();
			}
			return a;
		}
	}

	public double getTotal() {
		return Math
				.round(((1.00 * numDollars) + (.25 * numQuarters) + (.1 * numDimes) + (.05 * numNickles)) * 100.0) / 100.0;
	}

	public int getNumDollars() {
		return numDollars;
	}

	public void setNumDollars(int numDollars) {
		this.numDollars = numDollars;
	}

	public int getNumQuarters() {
		return numQuarters;
	}

	public void setNumQuarters(int numQuarters) {
		this.numQuarters = numQuarters;
	}

	public int getNumNickles() {
		return numNickles;
	}

	public void setNumNickles(int numNickles) {
		this.numNickles = numNickles;
	}

	public int getNumDimes() {
		return numDimes;
	}

	public void setNumDimes(int numDimes) {
		this.numDimes = numDimes;
	}

}
