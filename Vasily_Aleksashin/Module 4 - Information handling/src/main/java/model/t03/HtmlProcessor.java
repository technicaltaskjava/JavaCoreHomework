package model.t03;

import exeption.ParameterIsNullException;
import model.t02.CrazyLogger;
import util.Constant;
import util.Validator;

import java.util.regex.Matcher;

public class HtmlProcessor {
	private static CrazyLogger logger = CrazyLogger.getLogger(HtmlProcessor.class);

	public static String searchImgLink(final String input) throws ParameterIsNullException {
		logger.log(1, "Validate input string");
		Validator.validate(input);
		StringBuilder builder = new StringBuilder();
		logger.log(1, "Get tag array");
		String[] blockSplit = Constant.PATTERN_BLOCK_DELIMITER.split(input);
		for (int blockIndex = 0; blockIndex < blockSplit.length; blockIndex++) {
			if (blockSplit[blockIndex].length() > 0) {
				logger.log(1, "Remove unnecessary space");
				blockSplit[blockIndex] = blockSplit[blockIndex].replaceAll(Constant.UNNECESSARY_SPACE, Constant.SPACE);
				logger.log(1, "Delete inner tag");
				String[] innerBlockSplit = Constant.PATTERN_INNER_BLOCK_DELIMITER.split(blockSplit[blockIndex]);
				for (String innerBlock : innerBlockSplit) {
					logger.log(1, "Get clause array");
					String[] clauseSplit = Constant.PATTERN_CLAUSE_DELIMITER.split(innerBlock);
					for (int index = 0; index < clauseSplit.length; index++) {
						logger.log(1, "Prepare matcher for image link");
						Matcher matcher = Constant.PATTERN_IMAGE_LINK.matcher(clauseSplit[index]);
						logger.log(1, "Search matches in clause");
						if (matcher.find()) {
							if (index != 0) {
								int firstLatterIndex = innerBlock.indexOf(clauseSplit[index]);
								builder.append(innerBlock.substring(firstLatterIndex - 1, firstLatterIndex));
							}
							if (clauseSplit[index].matches(Constant.CLAUSE_ENDING)) {
								builder.append(clauseSplit[index].substring(0, clauseSplit[index].length() - 2));
							} else {
								builder.append(clauseSplit[index]);
							}
							builder.append("\n");
						}
					}
				}
			}
		}
		logger.log(1, "Return result");
		return builder.toString();
	}
}
