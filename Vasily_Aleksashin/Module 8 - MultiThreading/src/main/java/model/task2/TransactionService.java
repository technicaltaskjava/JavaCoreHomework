package model.task2;

import exception.ParameterIncorrectException;
import model.task2.entity.Account;
import model.task2.entity.Record;
import utility.Constant;
import utility.Validator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionService {
	private final AtomicInteger idCounter = new AtomicInteger(1);
	private final Set<Account> accountPool = new HashSet<>();
	private final Queue<Record> records = new ConcurrentLinkedQueue<>();

	public TransactionService(final int count) throws ParameterIncorrectException {
		Validator.isPositive(count);
		for (int index = 0; index < count; index++) {
			addAccount(Constant.SUM);
		}
	}

	public Set<Account> getAccountPool() {
		return accountPool.isEmpty() ? Collections.<Account>emptySet() : Collections.unmodifiableSet(accountPool);
	}

	boolean addAccount() throws ParameterIncorrectException {
		return addAccount(Constant.ZERO_BALANCE);
	}

	private boolean addAccount(final int balance) throws ParameterIncorrectException {
		Validator.isNotNegative(balance);
		int id = incrementId();
		Account account = new Account(id);
		account.increase(balance);
		return accountPool.add(account);
	}

	Account getAccount(final int id) throws ParameterIncorrectException {
		Validator.isPositive(id);
		for (Account account : accountPool) {
			if (account.getId() == id) {
				return account;
			}
		}
		return null;
	}

	int getBalance(final int id) throws ParameterIncorrectException {
		Validator.isPositive(id);
		final Account account = getAccount(id);
		return account.balance();
	}

	public Queue<Record> getRecords() {
		return records;
	}

	boolean increaseAccount(final int id, final int sum) throws ParameterIncorrectException {
		Validator.isPositive(id);
		Validator.isPositive(sum);
		final Account account = getAccount(id);
		return account.increase(sum);
	}

	boolean decreaseAccount(final int id, final int sum) throws ParameterIncorrectException {
		Validator.isPositive(id);
		Validator.isPositive(sum);
		final Account account = getAccount(id);
		return account.decrease(sum);
	}

	private int incrementId() {
		return idCounter.getAndIncrement();
	}
}
