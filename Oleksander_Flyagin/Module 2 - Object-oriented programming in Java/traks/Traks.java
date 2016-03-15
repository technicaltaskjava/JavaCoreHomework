package traks;

import java.util.Objects;

public interface Traks
    {

         String getNameTrak();
         String getActor();
         String getStayle();
         int [] getSattingsEqualize();
         double getDuration();
         String toString();
         boolean equals(Object obj);
         int compareTo(Object obj);
    }
