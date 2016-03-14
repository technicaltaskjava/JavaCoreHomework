package t01.view.impl;

import java.io.*;

public class ConsoleMock {
	private UserInputStream in;
	private ByteArrayOutputStream out;

	public ConsoleMock() {
		this.in = new UserInputStream();
		this.out = new ByteArrayOutputStream();

		System.setIn(in);
		System.setOut(new PrintStream(out));
	}

	public void addIn(final String line) {
		in.add(line);
	}

	public String getOut() {
		String result;
		try {
			result = new String(out.toByteArray(), "utf-8");
			out.reset();
		} catch (UnsupportedEncodingException e) {
			result = e.getMessage();
		}
		return result;
	}

	public static class UserInputStream extends InputStream {
		private String line;
		private boolean endLine;

		@Override
		public int read() throws IOException {
			if (line.length() == 0) {
				return -1;
			}
			if (endLine) {
				endLine = false;
				return -1;
			}
			char ch = line.charAt(0);
			line = line.substring(1);
			if (ch == '\n') {
				endLine = true;
			}
			return (int) ch;
		}

		public void add(final String line) {
			if (this.line == null) {
				this.line = line;
			} else {
				this.line += "\n" + line;
			}
		}

		@Override
		public synchronized void reset() throws IOException {
			line = null;
			endLine = false;
		}
	}
}
