package controller.menu;

import controller.MainController;
import exception.FileIOException;
import exception.ParameterIncorrectException;
import model.task2.StatementLoader;
import model.task2.TransactionService;
import model.task2.Transfer;
import model.task2.entity.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.Constant;

import java.util.Set;

class TransactionMenuController {
	private static final Logger logger = LoggerFactory.getLogger(TransactionMenuController.class);

	private TransactionService service;

	TransactionMenuController() {
		try {
			service = new TransactionService(Constant.DEFAULT_ACCOUNT_COUNT);
		} catch (ParameterIncorrectException e) {
			logger.error(e.getMessage(), e);
		}
	}

	void show(final MainController controller) {
		boolean flag = true;
		while (flag) {
			controller.print(Constant.SEPARATOR);
			controller.print("\tSTATEMENT LOADER MENU");
			controller.print(Constant.SEPARATOR);
			controller.print("[0] Load statement from file");
			controller.print("[1] Run operation with statement");
			controller.print("[2] Print account's balance");
			controller.print("[3] Back to MAIN MENU");
			final String input = controller.read();
			switch (input) {
				case "0":
					loadStatement(controller);
					break;
				case "1":
					statementOperation(controller);
					break;
				case "2":
					printBalance(controller);
					break;
				case "3":
					flag = false;
					break;
				default:
					controller.print(String.format("%nEntered menu item '%s' incorrect, expected 0 - 3", input));
			}
		}
	}

	private void statementOperation(final MainController controller) {
		if (service.getRecords().isEmpty()) {
			controller.print("Statement is empty.");
		}
		for (int index = 0; index < Constant.AVAILABLE_PROCESSORS; index++) {
			Thread thread;
			try {
				thread = new Thread(new Transfer(service));
				thread.start();
				thread.join();
			} catch (ParameterIncorrectException | InterruptedException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	private void printBalance(final MainController controller) {
		controller.print("Account list:");
		final Set<Account> accountPool = service.getAccountPool();
		for (Account account : accountPool) {
			controller.print(account.toString());
		}
	}

	private void loadStatement(final MainController controller) {
		StatementLoader loader = new StatementLoader(service);
		controller.print("Enter statement file name or empty for default:");
		final String path = controller.read();
		try {
			if ("".equals(path)) {
				loader.load(Constant.STATEMENT_FILE);
			} else {
				loader.load(path);
			}
		} catch (FileIOException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
