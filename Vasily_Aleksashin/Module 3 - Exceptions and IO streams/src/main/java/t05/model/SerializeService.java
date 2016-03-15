package t05.model;

import t01.exception.ModelException;

import java.io.*;

public class SerializeService {
	private String fileName = "movies.bin";
	private String filePath = System.getProperty("user.dir") + "/src/main/resources/";

	public File serialize(final MoviesService service, final String filePath) throws ModelException {
		validate(service);
		File file;
		if (filePath != null) {
			file = new File(filePath + File.separator + fileName);
		} else {
			file = new File(this.filePath + fileName);
		}
		try (FileOutputStream out = new FileOutputStream(file);
		     ObjectOutputStream outputStream = new ObjectOutputStream(out)) {

			outputStream.writeObject(service);
			outputStream.flush();
			return file;

		} catch (IOException e) {
			throw new ModelException(e.getMessage());
		}
	}

	public MoviesService deserialize(final String filePath) throws ModelException {
		File file;
		if (filePath != null) {
			file = new File(filePath + File.separator + fileName);
		} else {
			file = new File(this.filePath + fileName);
		}
		try (FileInputStream in = new FileInputStream(file);
		     ObjectInputStream inputStream = new ObjectInputStream(in)) {

			return  (MoviesService) inputStream.readObject();

		} catch (IOException | ClassNotFoundException e) {
			throw new ModelException(e.getMessage());
		}
	}

	private void validate(final Object object) throws ModelException {
		if (object == null) {
			throw new ModelException("Parameter can not be NULL");
		}
		if (!(object instanceof Serializable)) {
			throw new ModelException("Object must implements interface java.io.Serializable");
		}
	}
}
