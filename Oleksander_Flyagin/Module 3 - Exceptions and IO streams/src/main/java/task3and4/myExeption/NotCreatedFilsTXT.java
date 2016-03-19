package task3and4.myExeption;

import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.io.FileNotFoundException;
import java.io.IOException;

public class NotCreatedFilsTXT extends FileNotFoundException
    {

        public NotCreatedFilsTXT()
            {
                super();
            }

        public NotCreatedFilsTXT(String message)
            {
                super(message);
            }
    }
