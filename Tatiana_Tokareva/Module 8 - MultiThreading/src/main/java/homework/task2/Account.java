package homework.task2;

public class Account implements Comparable {
	private int accountId;
	private int balance;

	public Account(int accountId, int balance) {
		this.accountId = accountId;
		this.balance = balance;

	}

	public int getAccountId() {
		return accountId;
	}

	public int getBalance() {
		return balance;
	}

	public int deposit(int amount) {
		balance += amount;
		return balance;
	}

	public int withdrawal(int amount) {
		if (balance < amount) {
			return -1;
		}
		balance -= amount;
		return balance;
	}

	@Override
	public int hashCode() {
		int result = accountId;
		result = 31 * result + balance;
		return result;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Account account = (Account) o;

		return accountId == account.accountId && balance == account.balance;

	}

	@Override
	public String toString() {
		return "Account{" +
				"accountId=" + accountId +
				", balance=" + balance +
				'}';
	}

	@Override
	public int compareTo(final Object o) {
		Account account = (Account) o;
		return accountId - account.getAccountId();
	}
}
