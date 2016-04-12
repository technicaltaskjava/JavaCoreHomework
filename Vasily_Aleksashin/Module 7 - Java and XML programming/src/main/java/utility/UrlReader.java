package utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class UrlReader {
	private static final Logger logger = LoggerFactory.getLogger(UrlReader.class);
	private static final List<String> xmlFileName = new ArrayList<>();
	private static String htmlPage;

	private UrlReader() {
	}

	public static List<String> getXmlFiles() {
		List<String> xmlFiles = new ArrayList<>();
		getHtmlPage();
		getXmlFileName();
		for (String fileName : xmlFileName) {
			xmlFiles.add(createXmlFile(fileName));
		}
		return xmlFiles;
	}

	private static void getHtmlPage() {
		try {
			URL url = new URL(Constant.MAIN_URL);
			URLConnection connection = url.openConnection();
			try (InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
			     BufferedReader inputStream = new BufferedReader(inputStreamReader)) {
				StringBuilder builder = new StringBuilder();
				String line;
				while ((line = inputStream.readLine()) != null) {
					builder.append(line).append("\n");
				}
				htmlPage = builder.toString();
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private static void getXmlFileName() {
		Matcher matcher = Constant.PATTERN_HREF.matcher(htmlPage);
		while (matcher.find()) {
			final String ref = matcher.group(1);
			Matcher innerMatcher = Constant.PATTERN_XML_FILENAME.matcher(ref);
			while (innerMatcher.find()) {
				final String link = innerMatcher.group(1);
				xmlFileName.add(link.substring(1, link.length() - 1));
			}
		}
	}

	private static String createXmlFile(final String xmlFileName) {
		final String pathName = System.getProperty("user.dir") + Constant.DEST_PATH;
		File file = new File(pathName);
		try {
			if (!file.exists()) {
				file.mkdir();
			}
			file = new File(pathName + xmlFileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			URL xmlUrl = new URL(Constant.MAIN_URL + xmlFileName);
			URLConnection connection = xmlUrl.openConnection();
			try (InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
			     BufferedReader reader = new BufferedReader(inputStreamReader);
			     FileWriter fileWriter = new FileWriter(file);
			     BufferedWriter writer = new BufferedWriter(fileWriter)) {
				String line;
				while ((line = reader.readLine()) != null) {
					line += "\n";
					writer.write(line);
				}
				writer.flush();
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return file.getAbsolutePath();
	}
}
