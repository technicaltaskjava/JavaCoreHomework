package homework.task2;

import homework.util.Constant;
import homework.util.ReaderFile;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

class TransactionOperation {
	private Queue<Transaction> transactions = new ConcurrentLinkedQueue<>();
	private Set<Account> accounts = new TreeSet<>();

	TransactionOperation() {
		for (int index = 0; index < 5; index++) {
			accounts.add(new Account(index + 1, 100));
		}
	}

	void parsFile(String file) {
		String statement = ReaderFile.read(file);
		if (statement != null) {
			try (Scanner scanner = new Scanner(statement)) {
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					String[] split = Constant.PATTERN.split(line);
					int sender = Integer.parseInt(split[0]);
					int recipient = Integer.parseInt(split[1]);
					int amount = Integer.parseInt(split[2]);
					Transaction transaction = new Transaction(sender, recipient, amount);
					transactions.add(transaction);
				}
			}
		}
	}

	Transaction pollTransaction() {
		return transactions.poll();
	}

	Account getAccount(int id) {
		for (Account account : accounts) {
			if (account.getAccountId() == id) {
				return account;
			}
		}
		return null;
	}

	public Set<Account> getAccounts() {
		return accounts.isEmpty() ? Collections.<Account>emptySet() : Collections.unmodifiableSet(accounts);
	}
}
