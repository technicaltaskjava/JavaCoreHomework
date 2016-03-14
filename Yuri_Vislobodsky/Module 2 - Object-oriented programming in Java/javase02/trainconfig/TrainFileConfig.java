package javase02.trainconfig;

import javase02.train.Train;
import javase02.carriages.*;
import javase02.basecarriage.BaseCarriage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Class for data saving and restoring of Train object  
 * @author YURY.VISLOBODSKY
 * 
 */

public class TrainFileConfig { 
	public static boolean loadTrainFromFile(Train train, String fileName) throws Exception {
		String carriageType;
		try {
			train.deleteAll();
			Scanner sc = new Scanner(new File(fileName));
			while(sc.hasNext()) {
				carriageType = sc.next();
				if (carriageType.equals(CompartmentCarriage.class.getName())) {
					train.addCarriage(new CompartmentCarriage(sc.nextInt()));
				} else if (carriageType.equals(SecondClassCarriage.class.getName())) { 
					train.addCarriage(new SecondClassCarriage(sc.nextInt()));
				} else if (carriageType.equals(BaggageCarriage.class.getName())) { 
					train.addCarriage(new BaggageCarriage(sc.nextInt()));												
				}
			}
			sc.close();
			return true;
		}
		catch(FileNotFoundException e) {
			return false;
		}
		catch(Exception e) {
			train.deleteAll();
			e.getMessage();
			return false;
		}
	}	
	
	public static boolean saveTrainToFile(Train train, String fileName) throws Exception {
		try {
			PrintWriter pw = new PrintWriter(new File(fileName));
			for (int index = 0; index < train.length(); index++) {
				BaseCarriage carriage = train.getCarriageByIndex(index);
				if (carriage instanceof CompartmentCarriage) {
					pw.println(CompartmentCarriage.class.getName() + 
							" " + ((CompartmentCarriage)carriage).getCompartmentsNumber());
				} else if (carriage instanceof SecondClassCarriage) {
					pw.println(SecondClassCarriage.class.getName() +
							" " + ((SecondClassCarriage)carriage).getPassengersNumber());
				} else if (carriage instanceof BaggageCarriage) {
					pw.println(BaggageCarriage.class.getName() + 
							" " +((BaggageCarriage)carriage).getBaggageWeightInKg());
				}
			}
			pw.close();
			return true;
		}
		catch(Exception e) {
			e.getMessage();
			return false;
		}            
	}
}
