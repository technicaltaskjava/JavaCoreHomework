package task2;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Properties;


public class Main  {

	public static final String PATH = "src\\main\\resources\\config.properties";
	 
	  public static void main(String[] args) throws FileNotFoundException {
			
		  
		  FileInputStream fileInputStream;
       
        Properties prop = new Properties();
 
        try {
          
            fileInputStream = new FileInputStream(PATH);
            prop.load(fileInputStream);
 
            String name = prop.getProperty("site");
            String loginToSite = prop.getProperty("login");
            String passwordToSite = prop.getProperty("password");
 
            
            System.out.println(
                    "name " + name
                    + "\nloginToSite: " + loginToSite
                    + "\npasswordToSite: " + passwordToSite
            );
 
        } catch (IOException e) {
            System.out.println("file not found");
            e.printStackTrace();
        }
	
 
    }
	
}