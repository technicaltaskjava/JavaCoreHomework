package com.epam.t01;

public class VowelProportionSet {
    private VowelProportion [] proportionSet = new VowelProportion [0];

    public VowelProportion[] getProportionSet() {
        return proportionSet;
    }

    public void addProportionItem(String word, Float proportion) {
        VowelProportion newProportion = new VowelProportion(word, proportion);
        VowelProportion[] tempProportion = new VowelProportion[proportionSet.length + 1];
        System.arraycopy(proportionSet, 0, tempProportion, 0, proportionSet.length);
        tempProportion[proportionSet.length] = newProportion;
        proportionSet = tempProportion;
    }
}
