package task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Read {


		public String read(String fileName) {
			StringBuilder builder = new StringBuilder();
			try (FileReader reader = new FileReader(fileName);
			     BufferedReader bufferedReader = new BufferedReader(reader)) {
				String output;
				while ((output = bufferedReader.readLine()) != null) {
					builder.append(output);
				}
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
			return builder.toString();
		}
	}
	
	
	
	
	