package homework.task2;

class Transaction {
	private int sender;
	private int recipient;
	private int amount;

	Transaction(final int sender, final int recipient, final int amount) {
		this.sender = sender;
		this.recipient = recipient;
		this.amount = amount;
	}

	public int getSender() {
		return sender;
	}

	public int getRecipient() {
		return recipient;
	}

	public int getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return "Transaction{" +
				"sender=" + sender +
				", recipient=" + recipient +
				", amount=" + amount +
				'}';
	}
}
