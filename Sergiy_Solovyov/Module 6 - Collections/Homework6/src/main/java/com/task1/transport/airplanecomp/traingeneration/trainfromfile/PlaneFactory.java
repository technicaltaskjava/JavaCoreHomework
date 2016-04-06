package com.task1.transport.airplanecomp.traingeneration.trainfromfile;



import com.task1.transport.planes.AbstractPlane;
import com.task1.transport.planes.CharterPlane;
import com.task1.transport.planes.TransportPlane;
import com.task1.transport.planes.PassengerPlane;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 30.03.2016
 */
public class PlaneFactory {

    private PlaneFactory(){};

    public static AbstractPlane createPlane (String currentLine, String fileName){
        AbstractPlane plane = null;
        String [] arr = currentLine.split(" ");

        if ("CharterPlanes".equals(fileName))
            plane = new CharterPlane(Integer.valueOf(arr[0]),Double.valueOf(arr[1]),
                    Integer.valueOf(arr[2]),arr[3], arr[4],Integer.valueOf(arr[5]));
        if ("TransportPlanes".equals(fileName))
            plane = new TransportPlane(Integer.valueOf(arr[0]),Double.valueOf(arr[1]),
                    Integer.valueOf(arr[2]),arr[3], Integer.valueOf(arr[4]));
        if ("PassengerPlanes".equals(fileName))
            plane = new PassengerPlane(Integer.valueOf(arr[0]),Double.valueOf(arr[1]),
                    Integer.valueOf(arr[2]),arr[3],Integer.valueOf(arr[4]));
        return plane;
    }
}
