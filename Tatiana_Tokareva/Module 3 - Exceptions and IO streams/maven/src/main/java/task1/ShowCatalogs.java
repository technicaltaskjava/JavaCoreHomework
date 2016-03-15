package task1;

import java.io.File;

public class ShowCatalogs {
	
	public String displayInfo() {
		File file = new File("src\\main\\resources");
		StringBuilder builder = new StringBuilder();
		if (file.isDirectory()) {
			String list[] = file.list();
			for (String element : list) {
				builder.append(element).append("\n");
			}
		}
		return builder.toString();
	}
}

	
	
	
