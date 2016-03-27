package Task3;

import java.io.IOException;

/**
 * Created by Oleg on 22.03.2016.
 */
public class Run {

    public static void main(String[] args) {

        try {
            GetReferences.showReferences();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
