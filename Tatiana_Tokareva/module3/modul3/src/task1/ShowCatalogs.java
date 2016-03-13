package task1;

import java.io.File;

public class ShowCatalogs {
	
	
	
	public void displayInfo()
	 { 
	 File file=new File("src/task1");

     if(file.isDirectory())

     {
         String list[] = file.list();
         for (int i = 0; i < list.length; i++) {
             System.out.println(list[i]);

              new File("D:\\prog" + "\\" + list[i]);
             //  System.out.println("is file" + list[i] + f1.isDirectory());
         }
     }
 }
}
