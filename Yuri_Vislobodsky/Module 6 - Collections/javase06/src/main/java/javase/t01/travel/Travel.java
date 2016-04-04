package javase.t01.travel;

import javase.t01.basetour.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for travel (list of tours)
 * Created by Yury Vislobodsky on 30.03.2016.
 * 
 */
public class Travel {
	private static Logger logger = LoggerFactory.getLogger(Travel.class);
	private LinkedList<BaseTour> list;

	public Travel() {
		list = new LinkedList<>();
	}

	public int length() {
		return (list == null) ? 0 : list.size();
	}
	
	public BaseTour getTourByIndex(int index) {
		if (index >= 0 && index < length()) {
			return list.get(index);
		} else {
			return null;
		}
	}
	
	public void addTour(BaseTour newTour) {
		list.addLast(newTour);
	}

	public void deleteTour() {
		if (length() == 0) {
			return;
		}
		list.removeLast();
	}
	
	public int getDaysNumberTotal() {
		int total = 0;
		for (BaseTour tour : list) {
			total += tour.getDaysNumber();
		}		
		return total;
	}

	public double getCostTotal() {
		int total = 0;
		for (BaseTour tour : list) {
			total += tour.getCost();
		}		
		return total;
	}
	
	public void sortByDaysNumber() {
		Collections.sort(list, new Comparator<BaseTour>() {
			@Override
			public int compare(BaseTour o1, BaseTour o2) {
				return Integer.compare(o1.getDaysNumber(), o2.getDaysNumber());
			}
		});
	}
	
	public void sortByCost() {
		Collections.sort(list, new Comparator<BaseTour>() {
			@Override
			public int compare(BaseTour o1, BaseTour o2) {
				return Double.compare(o1.getCost(), o2.getCost());
			}
		});
	}
	
	public void rangeOnDaysNumber(int minNumber, int maxNumber) {
		for (BaseTour tour : list) {
			int number = tour.getDaysNumber();
			tour.setRanged(number>=minNumber && number<=maxNumber);
		}				
	}
	
	public void rangeOnCost(double minCost, double maxCost) {
		for (BaseTour tour : list) {
			double cost = tour.getCost();
			tour.setRanged(cost>=minCost && cost<=maxCost);
		}				
	}	
	
	public void rangeOff() {
		for (BaseTour tour : list) {
			tour.setRanged(true);
		}				
	}

	public void saveToFile(String fileName) throws IOException {
		try (
				ObjectOutputStream oos = new ObjectOutputStream(
						new FileOutputStream(fileName))
		)
		{
			oos.reset();
			oos.writeObject(list);
		} catch (IOException e) {
			logger.error("Exception", e);
		}
	}

	@SuppressWarnings("unchecked")
	public void loadFromFile(String fileName) throws IOException {
		try (
				FileInputStream is = new FileInputStream(fileName);
				ObjectInputStream ois = new ObjectInputStream(is)
		)
		{
			while (is.available() > 0) {
				list = (LinkedList<BaseTour>) ois.readObject();
			}
		} catch (ClassNotFoundException e) {
			logger.error("Class not find", e);
		} catch (FileNotFoundException e) {
			logger.info("File not found", e);
		} catch (IOException e) {
			logger.error("Exception", e);
		}
	}
}
