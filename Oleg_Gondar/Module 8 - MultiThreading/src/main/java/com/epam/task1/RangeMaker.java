package com.epam.task1;

/**
 * Created by o.gondar on 21.04.2016.
 */
public class RangeMaker {

    int startFirstRange;
    int endFirstRange;
    int startSecondRange;
    int endSecondRange;
    int startThirdRange;
    int endThirdRange;
    int startFourthRange;
    int endFourthRange;

    public RangeMaker(int startNumber, int endNumber) {
        makeRanges(startNumber, endNumber);
    }

    public int getStartFirstRange() {
        return startFirstRange;
    }

    public void setStartFirstRange(int startFirstRange) {
        this.startFirstRange = startFirstRange;
    }

    public int getEndFirstRange() {
        return endFirstRange;
    }

    public void setEndFirstRange(int endFirstRange) {
        this.endFirstRange = endFirstRange;
    }

    public int getStartSecondRange() {
        return startSecondRange;
    }

    public void setStartSecondRange(int startSecondRange) {
        this.startSecondRange = startSecondRange;
    }

    public int getEndSecondRange() {
        return endSecondRange;
    }

    public void setEndSecondRange(int endSecondRange) {
        this.endSecondRange = endSecondRange;
    }

    public int getStartThirdRange() {
        return startThirdRange;
    }

    public void setStartThirdRange(int startThirdRange) {
        this.startThirdRange = startThirdRange;
    }

    public int getEndThirdRange() {
        return endThirdRange;
    }

    public void setEndThirdRange(int endThirdRange) {
        this.endThirdRange = endThirdRange;
    }

    public int getStartFourthRange() {
        return startFourthRange;
    }

    public void setStartFourthRange(int startFourthRange) {
        this.startFourthRange = startFourthRange;
    }

    public int getEndFourthRange() {
        return endFourthRange;
    }

    public void setEndFourthRange(int endFourthRange) {
        this.endFourthRange = endFourthRange;
    }

    public void makeRanges(int startNumber, int endNumber) {
        int range = endNumber - startNumber;
        if (range < 8) {
            System.out.println("The range is too small, we work with this range for all threads.");
            setStartFirstRange(startNumber);
            setStartSecondRange(startNumber);
            setStartThirdRange(startNumber);
            setStartFourthRange(startNumber);
            setEndFirstRange(endNumber);
            setEndSecondRange(endNumber);
            setEndThirdRange(endNumber);
            setEndFourthRange(endNumber);
        } else {
            setStartFirstRange(startNumber);
            setEndFirstRange(startNumber + range / 4);
            setStartSecondRange(endFirstRange + 1);
            setEndSecondRange(startSecondRange + range / 4);
            setStartThirdRange(endSecondRange + 1);
            setEndThirdRange(startThirdRange + range / 4);
            if (endNumber - endThirdRange > 4) {
                setStartFourthRange(endThirdRange + 1);
            } else {
                System.out.println("Fill in the fourth range as the third.\nBecause we use a TreeSet identical values there can not be.");
                setStartFourthRange(startThirdRange);
            }
            setEndFourthRange(endNumber);
        }
    }
}
