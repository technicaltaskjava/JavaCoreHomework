package main;

import java.util.Comparator;
import java.util.TreeSet;
import vegetables.*;
import sorts.SortVegetables;

public class Main {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Recipe of salad Quick and made:");
		 Cucumber cucumber = new Cucumber();	
     	  cucumber.displayInfo();
     	 Tomato tomato = new Tomato();	
    	  tomato.displayInfo();
    	 Cabbage cabbage = new Cabbage();	
    	  cabbage.displayInfo();
        	  
     		
    	  System.out.println("-----------------------------");
    	  System.out.println("After sorts for calorific value:");
		    Comparator<Vegetable> pcomp = new SortVegetables();
		    TreeSet<Vegetable> vegetable = new TreeSet(pcomp);
		    vegetable.add(new Cucumber());
		    vegetable.add(new Tomato());
		    vegetable.add(new Cabbage());
		
		    
		     
		    for(Vegetable  p : vegetable){
		     
		        System.out.println(p.getName()+" "+p.getWeight()+" gram consists "+p.getCalorificValue()+" calories");
		    }
		 
	}

}
