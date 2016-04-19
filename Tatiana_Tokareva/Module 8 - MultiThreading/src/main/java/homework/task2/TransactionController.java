package homework.task2;

import homework.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class TransactionController {
	private static final Logger log = LoggerFactory.getLogger(TransactionController.class);
	private TransactionOperation operation = new TransactionOperation();

	public void execute() {
		operation.parsFile(Constant.FILE_NAME);
		for (int count = 0; count < Constant.AVAILABLE_PROCESSORS; count++) {
			Thread thread = new Thread(new TransferExecutor(operation));
			thread.start();
			try {
				thread.join();
			} catch (InterruptedException e) {
				log.error(e.getMessage(), e);
				thread.interrupt();
			}
		}
	}

	public String getBalance() {
		Set<Account> accounts = operation.getAccounts();

		StringBuilder builder = new StringBuilder("Account list").append("\n");
		for (Account account : accounts) {
			builder.append(account.toString()).append("\n");
		}
		if (accounts.isEmpty()) {
			builder.append("Empty list");
		}
		return builder.toString();
	}
}
