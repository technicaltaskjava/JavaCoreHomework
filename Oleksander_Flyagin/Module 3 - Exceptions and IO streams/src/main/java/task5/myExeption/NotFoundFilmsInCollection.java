package task5.myExeption;



public class NotFoundFilmsInCollection extends NullPointerException
    {
        public NotFoundFilmsInCollection()
            { super();
            }

        public NotFoundFilmsInCollection(String message)
            {
                super(message);
            }


    }
