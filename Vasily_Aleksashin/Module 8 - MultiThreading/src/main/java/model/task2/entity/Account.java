package model.task2.entity;

public class Account {
	private final int id;
	private int amount = 0;

	public Account(final int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int balance() {
		return amount;
	}

	@SuppressWarnings("SameReturnValue")
	public boolean increase(final int sum) {
		amount += sum;
		return true;
	}

	public boolean decrease(final int sum) {
		if (amount < sum) {
			return false;
		}
		amount -= sum;
		return true;
	}

	@Override
	public int hashCode() {
		return id;
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
		return id == account.id;
	}

	@Override
	public String toString() {
		return "Account: id=" + id + ", balance=" + amount;
	}
}
