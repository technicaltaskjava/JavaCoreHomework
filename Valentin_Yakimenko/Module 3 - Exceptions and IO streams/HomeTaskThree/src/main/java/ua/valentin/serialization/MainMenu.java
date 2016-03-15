package ua.valentin.serialization;

/**
 * Created by valentin.yakimenko on 15.03.16.
 */
public enum  MainMenu implements IMenu {
    VIEW_ALL ("1", "View movie collection"){
        public String option(){
            return "";
        }
    },

    ADD_NEW ("2", "Add new movie to collection"){
        public String option(){
            return "";
        }
    },

    DEL ("3", "Delete movie from collection"){
        public String option(){
            return "";
        }
    },

    EXIT ("4", "Exit") {
        public String option(){
            return "";
        }
    };

    private final String number;
    private final String text;
    MainMenu(String number, String text){
        this.number = number;
        this.text = text;
    }

    public String getNumber(){
        return number;
    }

    @Override
    public String toString(){
        return number + ". " + text;
    }

 }
