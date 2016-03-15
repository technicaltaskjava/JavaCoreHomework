package task1;


import java.io.FileNotFoundException;
import java.util.Scanner;



public class Main {
	private static final String FILE_NAME = "src\\main\\resources\\file.txt";
	
	public static void main(String[] args) throws FileNotFoundException {
		RecordInFile record=new RecordInFile();
		ShowCatalogs show = new ShowCatalogs();	
		Read reads = new Read();
		
		System.out.println(String.format("Files in resources:\n%s", show.displayInfo()));
		
  
   	System.out.println("Input text you want add to file");
   	String text;
	try (Scanner in = new Scanner(System.in)){
		text = in.nextLine();
	}
	
	record.write(FILE_NAME, text);
  	
  
  	System.out.println(String.format("File reading:\n%s", reads.read(FILE_NAME)));		
  	record.append(FILE_NAME, "\nAdded text\n");
	System.out.println(String.format("File reading after add text:\n%s", reads.read(FILE_NAME)));
 	System.out.println(String.format("catalog after creation new file\n%s", show.displayInfo()));

 	record.delete(FILE_NAME);
   
   	System.out.println(String.format("catalog after deleted file:\n%s", show.displayInfo()));
	}
}
