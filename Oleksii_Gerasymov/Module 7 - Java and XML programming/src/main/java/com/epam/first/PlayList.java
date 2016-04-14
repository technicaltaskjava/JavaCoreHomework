package com.epam.first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayList {
    private List<Speaker> speakerList;

    public PlayList() {
        this.speakerList = new ArrayList<>();
    }

    public List<Speaker> getSpeakerList() {
        return speakerList;
    }

    public int getNumberOfSpeakers(){
        return this.speakerList.size();
    }

    public void addSpeaker(Speaker nextSpeaker) {
        boolean isNewSpeaker = true;
        for (Speaker currentSpeaker: speakerList) {
            if (currentSpeaker.getName().equals(nextSpeaker.getName())) {
                addNewSpeech(currentSpeaker, nextSpeaker);
                isNewSpeaker = false;
            }
        }
        if (isNewSpeaker) {
            addNewSpeaker(nextSpeaker);
        }
    }

    private void addNewSpeaker(Speaker currentSpeaker) {
        this.speakerList.add(currentSpeaker);
    }

    private void addNewSpeech(Speaker currentSpeaker, Speaker nextSpeaker) {
        currentSpeaker.setNumberOfSpeech(currentSpeaker.getNumberOfSpeech() + 1);
        float newAverage = ((currentSpeaker.getAverageSpeechWords() * currentSpeaker.getNumberOfSpeech()) +
                nextSpeaker.getAverageSpeechWords()) / (currentSpeaker.getNumberOfSpeech() + 1);
        currentSpeaker.setAverageSpeechWords(newAverage);
    }

    public Speaker[] sortSpeaker() {
        Speaker[] sortedSpeakerList = new Speaker[this.speakerList.size()];
        System.arraycopy(this.speakerList.toArray(), 0, sortedSpeakerList, 0, this.speakerList.size());
        Arrays.sort(sortedSpeakerList);
        return sortedSpeakerList;
    }

    public String outPlayListData() {
        String resultData = "";
        Speaker[] resultList = sortSpeaker();
        for (Speaker currentSpeaker: resultList) {
            resultData = resultData + currentSpeaker.outSpeakerData();
        }
        return resultData;
    }
}
