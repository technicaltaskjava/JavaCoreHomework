package task5.actors;

import java.io.Serializable;

public class Actor implements Serializable
    {
        private String lName;
        private String fName;

        public Actor(String fName, String lName)
            {
                this.lName = lName;
                this.fName = fName;
            }
        public Actor()
            {
            }

        public String getfName()
            {
                return fName;
            }

        public String getlName()
            {
                return lName;
            }
    }
