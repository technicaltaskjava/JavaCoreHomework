package model.task2;

import exception.ParameterIncorrectException;
import model.task2.entity.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.Validator;

public class Transfer implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(Transfer.class);

	private final TransactionService service;
	private volatile boolean flag = true;


	public Transfer(final TransactionService service) throws ParameterIncorrectException {
		Validator.isNull(service);
		this.service = service;
	}

	@Override
	public void run() {
		while (flag) {
			if (!service.getRecords().isEmpty()) {
				final Record record = service.getRecords().poll();
				boolean result = false;
				try {
					result = transferSum(record);
				} catch (ParameterIncorrectException e) {
					logger.error(e.getMessage(), e);
				}
				if (!result) {
					logger.debug(String.format("Can not process %s", record));
					service.getRecords().offer(record);
				}
			} else {
				flag = false;
			}
		}
	}

	private boolean transferSum(final Record record) throws ParameterIncorrectException {
		Validator.isNull(record);
		final int senderId = record.getSenderId();
		final int recipientId = record.getRecipientId();
		final int sum = record.getSum();
		if (senderId < recipientId) {
			synchronized (service.getAccount(senderId)) {
				synchronized (service.getAccount(recipientId)) {
					return transaction(senderId, recipientId, sum);
				}
			}
		} else {
			synchronized (service.getAccount(recipientId)) {
				synchronized (service.getAccount(senderId)) {
					return transaction(senderId, recipientId, sum);
				}
			}
		}
	}

	private boolean transaction(final int senderId, final int recipientId, final int sum) throws ParameterIncorrectException {
		Validator.isPositive(senderId);
		Validator.isPositive(recipientId);
		boolean recipientFlag;
		if (service.decreaseAccount(senderId, sum)) {
			recipientFlag = service.increaseAccount(recipientId, sum);
		} else {
			return false;
		}
		if (!recipientFlag) {
			service.increaseAccount(senderId, sum);
			return false;
		}
		return true;
	}
}
