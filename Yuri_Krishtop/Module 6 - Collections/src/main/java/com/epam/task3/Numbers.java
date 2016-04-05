package com.epam.task3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yuriy Krishtop on 03.04.2016.
 */
public class Numbers<T extends Number> {
    private List<Number> nums;
    private static final Logger log = LoggerFactory.getLogger(Numbers.class);

    public Numbers() {
        nums = new LinkedList();
    }

    public void addNum(T newNum) {
        nums.add(newNum);
    }

    public void delNum(Number delNum) {
        Iterator<Number> iterator = nums.iterator();
        while (iterator.hasNext()) {
            Number n = iterator.next();
            if (n.equals(delNum)) {
                iterator.remove();
            }
        }
    }

    public Number findNearest(Number findNum) {
        try {
            Number nearestNum = nums.get(0);
            for (Number n : nums) {
                if (Math.abs(findNum.doubleValue() - n.doubleValue()) < Math.abs(nearestNum.doubleValue() -
                        findNum.doubleValue())) {
                    nearestNum = n;
                }
            }
            return nearestNum;
        } catch (IndexOutOfBoundsException e) {
            log.error("Failed Number", e);
            return null;
        }

    }

    public List<Number> getNumbers() {
        return nums;
    }
}
