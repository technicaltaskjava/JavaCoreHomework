package controller.menu;

import com.google.common.base.Stopwatch;
import controller.MainController;
import exception.ParameterIncorrectException;
import model.task1.CheckerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.Constant;

import java.util.List;

class CheckerMenuController {
	private static final Logger logger = LoggerFactory.getLogger(CheckerMenuController.class);

	private int start = 0;
	private int end = 0;
	private int threadCount = Constant.AVAILABLE_PROCESSORS;

	private CheckerService service;

	void show(final MainController controller) {
		boolean flag = true;
		while (flag) {
			controller.print(Constant.SEPARATOR);
			controller.print("\tPRIME CHECKER MENU");
			controller.print(Constant.SEPARATOR);
			controller.print("[0] Set input parameters (search interval and count of threads)");
			controller.print(String.format("[1] %s directly", Constant.ITEM));
			controller.print(String.format("[2] %s through threads", Constant.ITEM));
			controller.print("[3] Battle [1] vs [2]");
			controller.print("[4] Back to MAIN MENU");
			final String input = controller.read();
			switch (input) {
				case "0":
					setInputParameter(controller);
					break;
				case "1":
					findByRunnable();
					printPrimes(controller);
					break;
				case "2":
					findByCallable();
					printPrimes(controller);
					break;
				case "3":
					battle(controller);
					break;
				case "4":
					flag = false;
					break;
				default:
					controller.print(String.format("%nEntered menu item '%s' incorrect, expected 0 - 4", input));
			}
		}
	}

	private void battle(final MainController controller) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		findByRunnable();
		controller.print(String.format("%s directly:%n%s mills", Constant.RESULT, stopwatch));
		stopwatch.reset();
		stopwatch.start();
		findByCallable();
		controller.print(String.format("%s through threads:%n%s mills", Constant.RESULT, stopwatch));
	}

	private void findByCallable() {
		try {
			service = new CheckerService(start, end, threadCount);
			service.findPrimeCallable();
		} catch (ParameterIncorrectException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void printPrimes(final MainController controller) {
		final List<Integer> primes = service.getPrimes();
		controller.print(String.format("List of primes number:%n%s", primes));
	}

	private void findByRunnable() {
		try {
			service = new CheckerService(start, end, threadCount);
			service.findPrimeRunnable();
		} catch (ParameterIncorrectException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void setInputParameter(final MainController controller) {
		final int inputStart = inputParameter(controller, "of start range");
		final int inputEnd = inputParameter(controller, "of end range");
		final int count = inputParameter(controller, "of threads");
		start = Math.min(inputStart, inputEnd);
		end = Math.max(inputStart, inputEnd);
		if (count > 0) {
			threadCount = count;
		}

	}

	private int inputParameter(final MainController controller, final String message) {
		while (true) {
			controller.print(String.format("Enter the number %s:", message));
			String input = controller.read();
			try {
				return Integer.parseInt(input);
			} catch (NumberFormatException e) {
				controller.print(String.format("Expected number %s, but entered %s", message, input));
			}
		}
	}
}
