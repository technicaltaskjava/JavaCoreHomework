package homework.task2;

public class TransferExecutor implements Runnable {
	private TransactionOperation operation;

	public TransferExecutor(final TransactionOperation operation) {
		this.operation = operation;
	}

	@Override
	public void run() {
		Transaction transaction;
		while ((transaction = operation.pollTransaction()) != null) {
			int sender = transaction.getSender();
			int recipient = transaction.getRecipient();
			int amount = transaction.getAmount();
			if (sender < recipient) {
				synchronized (operation.getAccount(sender)) {
					synchronized (operation.getAccount(recipient)) {
						cashFlowFromAccounts(operation.getAccount(sender), operation.getAccount(recipient), amount);
					}
				}
			} else if (recipient < sender) {
				synchronized (operation.getAccount(recipient)) {
					synchronized (operation.getAccount(sender)) {
						cashFlowFromAccounts(operation.getAccount(sender), operation.getAccount(recipient), amount);
					}
				}
			}
		}
	}

	private void cashFlowFromAccounts(final Account sender, final Account recipient, final int amount) {
		sender.withdrawal(amount);
		recipient.deposit(amount);
	}
}
