package console;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 09.03.2016
 */
public class Console {

    private BufferedReader bufferedReader;

    public     String  stringInput(){
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String str = null;
        do {
            try {
                str = bufferedReader.readLine().trim();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }while (str.isEmpty());
        return str;
    }
    public   int consoleMenuInput(File[] paths){
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int menu = 0;
        String str = null;

        do {
            try {
                str = bufferedReader.readLine();
                menu = Integer.parseInt(str.trim());
                if (menu < 0 || menu > paths.length - 1){
                    System.out.println("Please, enter the number in front of the folder or the file");
                    menu = consoleMenuInput(paths);}
            } catch (Exception e) {
                System.out.println("Please, enter only numbers in front of the folder or the file");
            }
        } while (str.isEmpty());


        return menu;
    }
}
