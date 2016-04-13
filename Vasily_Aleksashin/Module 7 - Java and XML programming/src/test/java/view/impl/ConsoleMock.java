package view.impl;

import java.io.*;

class ConsoleMock {
	private final UserInputStream in;
	private final ByteArrayOutputStream out;

	ConsoleMock() {
		this.in = new UserInputStream();
		this.out = new ByteArrayOutputStream();

		System.setIn(in);
		System.setOut(new PrintStream(out));
	}

	void addIn(@SuppressWarnings("SameParameterValue") final String line) {
		in.add(line);
	}

	String getOut() throws UnsupportedEncodingException {
		String result = new String(out.toByteArray(), "utf-8");
		out.reset();
		return result;
	}

	private static class UserInputStream extends InputStream {
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

		@Override
		public synchronized void reset() throws IOException {
			line = null;
			endLine = false;
		}

		void add(final String line) {
			if (this.line == null) {
				this.line = line;
			} else {
				this.line += "\n" + line;
			}
		}
	}
}
