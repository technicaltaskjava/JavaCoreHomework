package model.task2;

import exception.FileIOException;
import exception.ParameterIncorrectException;
import model.task2.entity.Record;
import utility.Constant;
import utility.Reader;
import utility.Validator;

import java.util.Queue;
import java.util.Scanner;

public class StatementLoader {
	private final TransactionService service;

	public StatementLoader(final TransactionService transactionService) {
		service = transactionService;
	}

	public void load(final String path) throws FileIOException {
		try {
			Validator.isNull(path);
		} catch (ParameterIncorrectException e) {
			throw new FileIOException(e.getMessage(), e);
		}
		final Queue<Record> records = service.getRecords();
		final String statement = Reader.read(path);
		try (Scanner scanner = new Scanner(statement)) {
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				final String[] row = Constant.DELIMITER.split(line);
				final int senderId = Integer.parseInt(row[0]);
				final int recipientId = Integer.parseInt(row[1]);
				final int sum = Integer.parseInt(row[2]);
				Record record = new Record(senderId, recipientId, sum);
				records.offer(record);
			}
		}
	}

}
