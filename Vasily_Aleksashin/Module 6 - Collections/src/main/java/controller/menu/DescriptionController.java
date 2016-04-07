package controller.menu;

import exception.FileIOException;
import model.task5.Description;
import model.task5.Interface;
import model.task5.TableFormatter;

import java.util.Map;

class DescriptionController {
	private static final String FILE_NAME = "src/main/resources/description_of_collections.properties";
	private static final String SEPARATOR = "------------------------------------------------------------------------" +
			"--------------------------------------------------------------------------------------------\n";

	String get() throws FileIOException {
		Map<String, String> description = Description.getDescription(FILE_NAME);
		StringBuilder builder = new StringBuilder();
		if (!description.isEmpty()) {
			builder.append(SEPARATOR);
			builder.append(TableFormatter.getHeader());
			builder.append(SEPARATOR);
			builder.append(TableFormatter.getTable(description, Interface.SET, false));
			TableFormatter.reset();
			builder.append(SEPARATOR);
			builder.append(TableFormatter.getTable(description, Interface.LIST, false));
			TableFormatter.reset();
			builder.append(SEPARATOR);
			builder.append(TableFormatter.getTable(description, Interface.MAP, false));
			TableFormatter.reset();
			builder.append(SEPARATOR);
			builder.append(TableFormatter.getTable(description, Interface.QUEUE, false));
			TableFormatter.reset();
			builder.append(SEPARATOR);
		} else {
			builder.append("Empty Map");
		}
		return builder.toString();
	}
}
