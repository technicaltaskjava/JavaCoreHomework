package task2.MyExeption;

import java.io.FileNotFoundException;

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
