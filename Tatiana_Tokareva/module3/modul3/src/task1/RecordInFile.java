package task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RecordInFile {
	
	
	public  void write(String fileName, String text)throws FileNotFoundException {
	    
	    File file = new File(fileName);
	  
	 
	    try {
	        
	        if(!file.exists()){
	            file.createNewFile();
	        }
	 
	    
	        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
	 
	        try {
	           
	            out.print(text);
	        } finally {
	            
	            out.close();
	        }
	    } catch(IOException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	
	
	public  void delete(String fileName) {
		
		if(!new File(fileName).delete())
			  System.out.println("Delete failed");
		else
			System.out.println("file deleted");
	}

public  void append (String fileName,String text) throws FileNotFoundException {
	
	
	
    File file = new File(fileName);
	
	 
	   try {
	        
	        if(!file.exists()){
	            file.createNewFile();
	        }
	 
	    
	        FileWriter addText = new FileWriter(fileName, true);
	 
	        try {
	           
	        	addText.append(text);
	        	addText.flush();
	        } finally {
	            
	        	addText.close();
	        }
	    } catch(IOException e) {
	        throw new RuntimeException(e);
	    }
	}
	

	
	
}
