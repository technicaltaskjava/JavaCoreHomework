package homework.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger instance = new Logger();
	private StringBuilder builder = new StringBuilder();

	private Logger() {
		if (instance != null) {
			throw new IllegalStateException("Logger already exist");
		}
	}

	public static Logger getInstance() {
		return instance;
	}

	public Object readResolve() throws ObjectStreamException {
		return instance;
	}

	public Object writeReplace() throws ObjectStreamException {
		return instance;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {   //NOSONAR  it is protection from cloning
		throw new IllegalStateException("Logger can not be cloned");

	}

	public void message(String message) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
		builder.append(dateFormat.format(calendar.getTime())).append(" - ").append(message);
	}

	public String print() {
		return builder.toString();
	}
}
