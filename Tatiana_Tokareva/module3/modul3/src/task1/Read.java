package task1;

import java.io.FileReader;
import java.io.IOException;

public class Read {

	
	public void read(){
	    
		 try(
				 FileReader reader = new FileReader("src/task1/file.txt"))
	        {
	           
	            int c;
	            while((c=reader.read())!=-1){
	                 
	                System.out.print((char)c);
	            } 
	        }
	        catch(IOException ex){
	             
	            System.out.println(ex.getMessage());
	        }   
	
}
}