package transport.train.traingeneration.trainfromfile;

import transport.carriages.DiningCar;
import transport.carriages.LuggageVan;
import transport.carriages.SleepingCar;
import transport.train.Train;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 07.03.2016
 */
public class ReadFromFile {
    private Train train;
    private String [] files = new String[]{"DiningCars", "LuggageVans","SleepingCars"};

    public ReadFromFile(){
        train = new Train();
    }

    public void readTrainFromFile(){

        for (int i = 0; i < files.length; i++ ){
            readCarriageFromFile(files[i]);
        }
    }

    private void readCarriageFromFile(String fileName){

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName+".txt").getFile());

        BufferedReader br = null;

        try {

            String currentLine;

            br = new BufferedReader(new FileReader(file));

            while ((currentLine = br.readLine()) != null) {

                train.addCarriage(CarriageFact.createCarriage(currentLine, fileName));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public Train getTrain() {
        return train;
    }
}

