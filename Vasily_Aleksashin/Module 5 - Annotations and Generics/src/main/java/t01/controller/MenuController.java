package t01.controller;


import t01.exception.ExitException;
import t01.exception.TargetClassPathNotFoundException;

class MenuController {

	void show(final MainController mainController) throws ExitException {
		while (true) {
			mainController.print("============================================");
			mainController.print("\tMAIN TEST MENU");
			mainController.print("============================================");
			mainController.print("[0] Start test in default path 'test'\n" +
					"[1] Running tests in the custom directory\n" +
					"[2] Exit");
			mainController.print("============================================");
			mainController.print("Enter menu item:");
			String input = mainController.read();
			switch (input) {
				case "0":
					try {
						runTest(mainController, MainController.DEFAULT_TEST_DIR);
					} catch (TargetClassPathNotFoundException e) {
						mainController.print(e.getMessage());
						continue;
					}
					break;
				case "1":
					try {
						String classPath = getCustomClassPath(mainController);
						runTest(mainController, classPath);
					} catch (TargetClassPathNotFoundException e) {
						mainController.print(e.getMessage());
						continue;
					}
					break;
				case "2":
					throw new ExitException();
				default:
					mainController.print(String.format("Entered menu item '%s' incorrect, expected 0 - 2", input));
			}
		}

	}

	private String getCustomClassPath(final MainController mainController) {
		mainController.print("Enter target path with class files for testing:");
		return mainController.read();
	}

	private void runTest(final MainController mainController, final String classPath) throws TargetClassPathNotFoundException {
		String result = mainController.getTestController().run(classPath);
		mainController.print(result);
	}
}
