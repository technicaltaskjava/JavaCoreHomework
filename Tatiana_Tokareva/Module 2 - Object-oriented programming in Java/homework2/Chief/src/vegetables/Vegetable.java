package vegetables;



public abstract class Vegetable implements Comparable  {

public String name;
public int weight;
public int calories;

	
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
 public int compareTo(Object obj)
 {
   Vegetable tmp = (Vegetable)obj;
   if(this.getCalorificValue() < tmp.getCalorificValue())
   {
    
     return -1;
   }   
   else if(this.getCalorificValue() > tmp.getCalorificValue())
   {
     
     return 1;
   }
  
   return 0;  
 } 	  
 public void displayInfo()
 {         
	 System.out.println(  name +" "+ weight+" gram");
 }

}
