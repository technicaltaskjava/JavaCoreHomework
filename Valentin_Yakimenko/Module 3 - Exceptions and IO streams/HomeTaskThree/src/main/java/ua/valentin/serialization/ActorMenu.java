package ua.valentin.serialization;

/**
 * Created by valentin.yakimenko on 15.03.16.
 */
public enum ActorMenu implements IMenu {
    EXIT("1", "Exit") {
        public String option() {
            return null;
        }
    },

    ADD_NEW("2", "Add new Actor") {
        public String option() {
            return null;
        }
    },

    FROM_LIST("3", "Get from list") {
        public String option() {
            return null;
        }
    };

    private final String number;
    private final String text;
    ActorMenu(String number, String text) {
        this.number = number;
        this.text = text;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return number + ". " + text;
    }

 }
