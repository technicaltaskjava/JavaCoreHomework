package homework.singleton;

import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Constructor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoggerTest {
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(LoggerTest.class);
	private Logger logger = Logger.getInstance();

	@Test
	public void testGetInstance() {
		assertTrue(logger == Logger.getInstance());
	}

	@Test
	public void testSerialize() {
		try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream("src/test/resources/logger.bin"))) {
			out.writeObject(logger);
			out.close();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}


		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/test/resources/logger.bin"))) {
			Logger serializedLogger = (Logger) in.readObject();
			in.close();
			assertTrue(serializedLogger == logger);

		} catch (IOException | ClassNotFoundException e) {
			log.error(e.getMessage(), e);
		}

	}

	@Test
	public void testReflation() {
		Logger logTest;
		try {

			Constructor[] constructors = Logger.class.getDeclaredConstructors();
			for (Constructor constructor : constructors) {
				constructor.setAccessible(true);
				logTest = (Logger) constructor.newInstance();

				assertTrue(logTest == Logger.getInstance());
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Test(expected = IllegalStateException.class)
	public void testClone() throws CloneNotSupportedException {
		logger.clone();
	}

	@Test
	public void testMessage() {
		logger.message("message");
		assertFalse(logger.print().isEmpty());
	}

	@Test
	public void testPrint() {
		assertTrue(logger.print().isEmpty());
	}
	
}