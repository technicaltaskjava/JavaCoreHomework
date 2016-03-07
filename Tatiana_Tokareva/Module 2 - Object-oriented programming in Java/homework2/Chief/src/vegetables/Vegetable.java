package vegetables;

public class Vegetable {

private String name;
private int weight;
private int calories;

	
  public double getCalorificValue() 
  {
	 double u=calories*(weight/100.0);
	 return u;
  }
 
	
  public String getName() 
  {
	  return name; 
  }
   public int getCalories()
   {
	  return calories; 
   }
   public int getWeight() 
   {
	   return weight; 
   }
	     
 public Vegetable (String name, int calories, int weight)
 { 
 this.name=name;
 this.calories=calories;
 this.weight=weight;
	        
 }
	  
 public void displayInfo()
 {         
	 System.out.println(  name +" "+ weight+" gram");
 }

}
