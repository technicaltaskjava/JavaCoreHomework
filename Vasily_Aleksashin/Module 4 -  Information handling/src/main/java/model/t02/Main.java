package model.t02;

import exeption.ParameterIsNullException;
import util.Constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Main {
	private static final CrazyLogger logger = CrazyLogger.getLogger(Main.class);

	public static void main(String[] args) throws ParameterIsNullException, InterruptedException, ParseException {
		SimpleDateFormat format = new SimpleDateFormat(Constant.DATE_FORMAT);
		long now = System.currentTimeMillis();
		int minute = 60 * 1000;
		long start = now + (2 * minute);
		long end = now + (5 * minute);
		String[] msgSuffix = new String[]{"1", "2", "1", "2", "1", "2", "1", "2", "1", "2"};
		for (int i = 1; i < 10; i++) {
			long time = now + (i * minute);
			Random random = new Random();
			logger.setLog(time, msgSuffix[random.nextInt(2)] + "Test message" + msgSuffix[i]);
		}
		System.out.println(Constant.SEPARATOR);
		System.out.println("\tAll log info:");
		System.out.println(Constant.SEPARATOR);
		System.out.println(logger.printString());
		System.out.println(Constant.SEPARATOR);
		String startDate = format.format(new Date(start));
		String endDate = format.format(new Date(end));
		System.out.println(String.format("\tResult of search\nStart: \t%s\nEnd: \t%s", startDate, endDate));
		System.out.println(Constant.SEPARATOR);
		String searchResult = logger.findLog(start, end);
		System.out.println(searchResult);
		System.out.println(Constant.SEPARATOR);
		System.out.println(String.format("\tResult of search\nMessage: \t%s", "age2"));
		System.out.println(Constant.SEPARATOR);
		searchResult = logger.findLog("age2");
		System.out.println(searchResult);
		System.out.println(Constant.SEPARATOR);
		System.out.println(String.format("\tResult of search\nMessage first: \t%s\nMessage second: \t%s\"", "2Tes", "age2"));
		System.out.println(Constant.SEPARATOR);
		searchResult = logger.findLog("2Tes", "age2");
		System.out.println(searchResult);
		System.out.println(Constant.SEPARATOR);
	}
}
