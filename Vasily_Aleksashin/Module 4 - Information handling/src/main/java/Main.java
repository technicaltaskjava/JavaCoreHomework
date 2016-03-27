import controller.MainController;
import exeption.ExitException;
import model.t02.CrazyLogger;

public class Main {
	private static CrazyLogger logger = CrazyLogger.getLogger(Main.class);

	public static void main(String[] args) {
		MainController controller = new MainController();
		try {
			logger.log(1, "Start Main Controller");
			controller.run();
		} catch (ExitException e) {
			logger.log(1, "App is closed");
			System.out.println(e.getMessage());
		}
	}

}
