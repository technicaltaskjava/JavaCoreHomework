package javase03.t05.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import javase03.common.exception.*;
import javase03.t05.objects.*;
import java.io.ObjectInputStream;

/**
 * Film collection serialization class
 * @author Yury.Vislobodsky
 *
 */
public class FilmCollectionSerialization {
	public static void serializeFilmCollection(String fileName, 
						FilmCollection filmCollection) throws Exception {
		FileOutputStream os = null;
		ObjectOutputStream oos = null;
		try {
			os = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(os);
			oos.reset();
			oos.writeObject(filmCollection);
		} catch (IOException e) {
			throw new MyIOException();
		} finally {	
			oos.close();
			os.close();
		}	
	}
	
	public static FilmCollection deserializeFilmCollection(String fileName) 
															throws Exception {
		FilmCollection filmCollection = null;
		FileInputStream is = null;
		ObjectInputStream ois = null;
		try {
			is = new FileInputStream(fileName);
			try {
				ois = new ObjectInputStream(is);
				while (is.available() > 0) {
					filmCollection = (FilmCollection) ois.readObject();
				}
			} finally {
				if (ois != null) {
					ois.close();
				}
			}							
		} catch (FileNotFoundException e) {
			throw new MyFileNotFoundException(fileName);
		} catch (ClassNotFoundException e) {
			throw new MyClassNotFoundException(FilmCollection.class.getName());			
		} catch (IOException e) {
			throw new MyIOException();
		} finally {	
			if (is != null) {
				is.close();
			}
		}			
		return filmCollection;
	}	
}
