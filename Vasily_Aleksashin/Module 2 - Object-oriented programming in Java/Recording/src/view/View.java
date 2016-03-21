package view;

public interface View {
	void print(final String output);

	String read();

	boolean closeResources();
}
