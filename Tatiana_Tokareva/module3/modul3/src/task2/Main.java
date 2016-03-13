package task2;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	public static final String PATH = "src/task2/resources/config.properties";
	 
    public static void main(String[] args) {
 
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
 