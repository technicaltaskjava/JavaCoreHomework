package dao.entity;

import dao.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author augustprime
 */
public class PredicationList {
    private static int size;
    private static List<String> predictions = fillPredicationList();
    private static Random random = new Random();

    private PredicationList() {

    }

    private static List<String> fillPredicationList() {

        List<FortuneCookie> fortuneCookieList = DAO.getInstance().getCookiesAccessor().getCookieList();
        size = fortuneCookieList.size();
        List<String> predictions = new ArrayList<>(size);

        int index = 0;
        for (FortuneCookie cookie : fortuneCookieList) {
            predictions.add(index, cookie.getPredication());
            index++;
        }

        return predictions;
    }

    public static String getRandomPredication() {
        return predictions.get(random.nextInt(size));
    }
}
