package task1;


import java.io.FileNotFoundException;
import java.util.Scanner;



public class Main {

	
	public static void main(String[] args) throws FileNotFoundException {
		RecordInFile rec=new RecordInFile();
		System.out.println("folders:");
		ShowCatalogs show = new ShowCatalogs();	
   	  show.displayInfo();
   	System.out.println("\nInput text you want add to file");
   	Scanner in = new Scanner(System.in);
   	String text = in.nextLine();
   	   	in.close();
  	rec.write("src/task1/file.txt", text);	
   	Read reads = new Read();	
   	reads.read();
   	System.out.println("\nfile contents after add text:");
   	rec.append("src/task1/file.txt", "\nAdded text\n");
   	
	reads.read();
	System.out.println("\ncatalog after creation new file: ");
	show.displayInfo();
   	rec.delete("src/task1/file.txt");
   	System.out.println("\ncatalog after deleted file: ");
   	show.displayInfo();
	}
}
