package com.task1.transport.airplanecomp.traingeneration.trainfromfile;


import com.task1.transport.airplanecomp.AirCompany;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 30.03.2016
 */
public class ReadFromFile {

    private AirCompany company = new AirCompany();;
    private String [] files = new String[]{"CharterPlanes", "TransportPlanes","PassengerPlanes"};
    private static final Logger LOGGER = LoggerFactory.getLogger(ReadFromFile.class);

    public void readPlaneFromFile(){

        for (int i = 0; i < files.length; i++ ){
            readPlaneFromFile(files[i]);
        }
    }

    private void readPlaneFromFile(String fileName){

        File file = new File(fileName + ".txt");



        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));){

            String currentLine;

            while ((currentLine = br.readLine()) != null) {

                company.addPlane(PlaneFactory.createPlane(currentLine, fileName));
            }

        } catch (IOException e) {
            LOGGER.info(e.getMessage(), e);

        }
    }

    public AirCompany getAirCompany() {
        return company;
    }
}

