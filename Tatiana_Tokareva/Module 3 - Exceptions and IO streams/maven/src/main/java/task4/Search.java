package task4;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class Search {
	
	
    
	public String search (String fileName, String[] found) {
		StringBuilder builder = new StringBuilder();
	try 
        (BufferedReader bufferReader = new BufferedReader(new FileReader(fileName))){
        String output;
        int count=0;
        while((output = bufferReader.readLine()) !=null)
        {
           for(int i = 0 ; i<found.length;i++){
           if(output.contains(found[i])){
                       	   
        	   builder.append(found[i]+" ");
            
        	   count++;
               File file = new File(fileName);
               if(!file.exists()){
                   file.createNewFile();
               }
               
           } 
          }     
        }
        builder.append("\namount of words="+count);
        
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    } return builder.toString();
	}
	
	
	public boolean write(String fileName, String text, boolean append) throws FileNotFoundException {
		 try {
		        FileOutputStream fos = new FileOutputStream(fileName);
		        OutputStreamWriter out = new OutputStreamWriter(fos, "UTF8");
		        out.write(text);
		        
		        out.close();
		        return true;
		    } 
		    catch (IOException e) {
		        e.printStackTrace();
		    }return false;
		}
	public boolean write(String fileName, String text) throws FileNotFoundException {
		return write(fileName, text, true);
	}
		
	public String read(String fileName) {
		StringBuilder builder = new StringBuilder();
		try (FileInputStream reader = new FileInputStream(fileName);
		     BufferedInputStream bufferedReader = new BufferedInputStream(reader)) {
			int output;
			
			while ((output = bufferedReader.read()) != -1) {
				builder.append((char)output);
				}   reader.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		return builder.toString();
	}

	
}
