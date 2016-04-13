package utility;

import exception.ParameterIsNullException;
import model.task2.entity.DependElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LoadDepend {
	private static final Logger logger = LoggerFactory.getLogger(LoadDepend.class);

	private LoadDepend() {
	}

	public static Set<DependElement> loadDependency(final String data) {
		Set<DependElement> elements = new HashSet<>();
		try {
			Validator.isNull(data);
		} catch (ParameterIsNullException e) {
			logger.error(e.getMessage(), e);
			return Collections.emptySet();
		}
		try (Scanner scanner = new Scanner(data)) {
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				final String[] split = line.split("[|]");
				if (split.length < 3) {
					continue;
				}
				DependElement element = new DependElement(split[0], split[1], split[2]);
				elements.add(element);
			}
		} catch (ParameterIsNullException e) {
			logger.error(e.getMessage(), e);
			return Collections.emptySet();
		}
		return elements;
	}
}
