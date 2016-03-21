package main;

import java.util.Arrays;
import java.util.Scanner;
import vegetables.*;


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
		 
		 
        Vegetable[] veget = new Vegetable[3];
  	    veget[0] = new Cucumber();
  	    veget[1] = new Tomato();
  	    veget[2] = new Cabbage();
  	 
  	    Arrays.sort(veget);
  	                             
  	   
  	                             
  	    for(int i = 0; i < veget.length; i++)
  	    {
  	    	
  	      System.out.println(veget[i].name + " "+
  	    		  veget[i].weight +" gram consists "+veget[i].getCalorificValue()+" calories");
  	    
  	  }   
  	    System.out.print("------------------------------------ \n");
  	    Scanner in = new Scanner(System.in);
   	    System.out.print("Input name of product for searching: ");
  	    String n = in.next();
  	     in.close();
 
  	    for(int i = 0; i < veget.length; i++)
  	    {
  	    if(n.equals(veget[i].name))	
  	    	 {System.out.println(veget[i].name + " "+
  	  	    		  veget[i].weight +
  	  	    		  " gram consists "+veget[i].getCalorificValue()+" calories")  ; 
  	   
  	    	 }
  	   
  	  }  	  
    	  
    	  
	}

	

}
