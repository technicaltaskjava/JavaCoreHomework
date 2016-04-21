package com.epam.task1;

import java.util.Set;

/**
 * Created by o.gondar on 21.04.2016.
 */
public class ResulstDTO {

    private Set<Integer> simpleNumbers;
    private long runtime;

    public ResulstDTO() {
        //For making empty object
    }

    public ResulstDTO(Set<Integer> simpleNumbers, long runtime) {
        this.simpleNumbers = simpleNumbers;
        this.runtime = runtime;
    }

    public Set<Integer> getSimpleNumbers() {
        return simpleNumbers;
    }

    public void setSimpleNumbers(Set<Integer> simpleNumbers) {
        this.simpleNumbers = simpleNumbers;
    }

    public void setRuntime(long runtime) {
        this.runtime = runtime;
    }

    public long getRuntime() {
        return runtime;
    }
}
