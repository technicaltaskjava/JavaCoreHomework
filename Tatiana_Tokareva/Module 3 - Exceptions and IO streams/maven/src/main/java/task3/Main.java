package task3;


import java.io.FileNotFoundException;



public class Main {
	private static final String FILE_NAME = "src\\main\\java\\task1\\Main.java";
	private static final String FILE_NEW ="src\\main\\resources\\javaWordsTask3.txt";
	private static final String[] WORDS = { "abstract","continue","for","new","switch",
		"assert","default","goto","package","synchronized",
		"boolean","do","if","private","this",
		"break","double","implements","protected","throw",
		"byte","else","import","public","throws",
		"case","enum","instanceof","return"	,"transient",
		"catch","extends","int","short","try",
		"char","final","interface","static","void",
		"class	","finally","long","strictfp","volatile",
		"float" ,"native","	super","while"};
	
	
	public static void main(String[] args) throws FileNotFoundException  {
		
		
		Search found = new Search();
		
	String text= found.search(FILE_NAME,WORDS);
	found.write(FILE_NEW, text);
 System.out.println(String.format("File reading:\n%s", found.read(FILE_NEW)));	
	
	
	}

}
