import com.epam.gems.precious.*;
import com.epam.gems.semiprecious.*;
import com.epam.gems.*;
import java.util.Arrays;

public class Test {

	public static void main(String[] args) throws CloneNotSupportedException {
		Gem[] gems = new Gem[8];
		gems[0] = new Diamond(0.5, "round", "IF", 'D');
		gems[1] = new Sapphire(4.4, "markuize", "VS2", 3);
		gems[2] = new Amber(22, "sphere", "SI1");
		gems[3] = new Jade(75, "asscher", "I2");
		gems[4] = new Diamond(3, "heart", "IF", 'M');
		gems[5] = new Sapphire(1.4, "princess", "VVS1", 4);
		gems[6] = new Amber(40, "pear", "SI2");
		gems[7] = new Jade(31, "oval", "I1");
		System.out.println("Necklace:");
		Necklace necklace = new Necklace(gems);
		necklace.showInfo();
		System.out.println("Weight of necklace is " + necklace.getNecklaceCaratWeight() + " carat");
		System.out.printf("Price of necklace is " + "%.2f" + " $\n", necklace.getPrice());
		Arrays.sort(gems);
		Necklace sortedNecklace = new Necklace(gems);
		System.out.println("Necklace after sotring by weight:");
		sortedNecklace.showInfo();
		System.out.println("Selected gems: ");
		Necklace selectedGems = new Necklace();
		selectedGems = necklace.getByPriceRange(100000, 500000);
		selectedGems.showInfo();
		
	}

}
