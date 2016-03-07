package sorts;
import java.util.Comparator;
import vegetables.Vegetable;

public class SortVegetables implements Comparator <Vegetable> {

		  public int compare(Vegetable a, Vegetable b){
		      
		        if(a.getCalorificValue()> b.getCalorificValue())
		            return 1;
		        else if(a.getCalorificValue()< b.getCalorificValue())
		            return -1;
		        else
		            return 0;
		    }
}







