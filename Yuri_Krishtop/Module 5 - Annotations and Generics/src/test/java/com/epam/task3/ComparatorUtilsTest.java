package com.epam.task3;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Yuriy Krishtop on 28.03.2016.
 */
public class ComparatorUtilsTest {


    @Test
    public void testMin() {
        Human[] people = new Human[5];
        people[0] = new Human(20, "Vasya");
        people[1] = new Human(15, "Petya");
        people[2] = new Human(45, "Kolya");
        people[3] = new Human(25, "Olya");
        people[4] = new Human(30, "Sasha");
        HumanComparatorByAge comparator = new HumanComparatorByAge();
        Assert.assertEquals(15, CompareUtils.min(people, comparator).getAge());
    }

    @Test
    public void testMax() {
        Human[] people = new Human[5];
        people[0] = new Human(20, "Vasya");
        people[1] = new Human(15, "Petya");
        people[2] = new Human(45, "Kolya");
        people[3] = new Human(25, "Olya");
        people[4] = new Human(30, "Sasha");
        HumanComparatorByAge comparator = new HumanComparatorByAge();
        Assert.assertEquals(45, CompareUtils.max(people, comparator).getAge());
    }

    @Test
    public void testMedian() {
        Human[] people = new Human[5];
        people[0] = new Human(20, "Vasya");
        people[1] = new Human(15, "Petya");
        people[2] = new Human(45, "Kolya");
        people[3] = new Human(25, "Olya");
        people[4] = new Human(30, "Sasha");
        HumanComparatorByAge comparator = new HumanComparatorByAge();
        Assert.assertEquals(25, CompareUtils.median(people, comparator).getAge());
    }
}
