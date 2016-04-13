package javase.t01.play;

import java.util.Map;
import java.util.TreeMap;

/**
 * Class for play (map of speeches)
 * Created by Yury Vislobodsky on 06.04.2016.
 */
public class Play {
    private Map<String, Totals> list;

    public Play() {
        list = new TreeMap<>();
    }

    public void add(Speech speech) {
        if (speech == null) {
            return;
        }
        for (int index = 0; index < speech.getSpeakersCount(); index++) {
            String speaker = speech.getSpeaker(index);
            Totals totals = getTotals(speaker);
            totals.setSpeechCount(totals.getSpeechCount() + 1);
            totals.setWordsCount(totals.getWordsCount() + speech.getWordsCount());
        }
    }

    public void printStatistics(String title) {
        System.out.println();
        System.out.println(title);
        System.out.println("Speaker          Speech count    Avg words/speech");
        System.out.println("-------------------------------------------------");
        for (Map.Entry entry : list.entrySet()) {
            String speaker = (String) entry.getKey();
            Totals totals = (Totals) entry.getValue();
            System.out.println(String.format("%-20s   %6d              %6d",
                    speaker, totals.getSpeechCount(), totals.getAverageWordsCountPerSpeech()));
        }
        System.out.println("--------------------------------------------------");
    }

    private Totals getTotals(String speaker) {
        Totals totals = list.get(speaker);
        if (totals == null) {
            totals = new Totals();
            list.put(speaker, totals);
        }
        return totals;
    }
}
