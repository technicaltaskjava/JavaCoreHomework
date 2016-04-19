package model.task2.entity;

public class Record {
	private final int senderId;
	private final int recipientId;
	private final int sum;

	public Record(final int senderId, final int recipientId, final int sum) {
		this.senderId = senderId;
		this.recipientId = recipientId;
		this.sum = sum;
	}

	public int getSenderId() {
		return senderId;
	}

	public int getRecipientId() {
		return recipientId;
	}

	public int getSum() {
		return sum;
	}

	@Override
	public String toString() {
		return "Record{" +
				"senderId=" + senderId +
				", recipientId=" + recipientId +
				", sum=" + sum +
				'}';
	}
}
