package dao.util;

import dao.DAO;
import dao.entity.FortuneCookie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author augustprime
 */
public class PredicationList {
    private static int size;
    private static List<Predication> predictions = fillPredicationList();
    private static Random random = new Random();

    private PredicationList() {

    }

    private static List<Predication> fillPredicationList() {

        List<FortuneCookie> fortuneCookieList = DAO.getInstance().getCookiesAccessor().getCookieList();
        size = fortuneCookieList.size();
        List<Predication> predictions = new ArrayList<>(size);

        int index = 0;
        for (FortuneCookie cookie : fortuneCookieList) {
            predictions.add(index, new Predication(cookie.getId(), cookie.getPredication()));
            index++;
        }

        return predictions;
    }

    public static int getSize() {
        return size;
    }

    private static boolean correctIndex(int index) {
        return index >= 0 && index < size;
    }

    public static Predication getPredication(int index) {
        if (correctIndex(index)) {
            return predictions.get(index);
        } else {
            return new Predication(-1, "");
        }
    }

    public static String getRandomPredicationValue() {
        return predictions.get(random.nextInt(size)).getValue();
    }
}
