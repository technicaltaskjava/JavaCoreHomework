package tests.task2;

import java.util.Comparator;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 25.03.2016
 */
public class Apple implements Comparable<Apple>, Comparator<Apple>{

    public int anInt;

    public Apple() {
    }
    public Apple(int anInt) {
        this.anInt = anInt;
    }

    @Override
    public int compareTo(Apple o) {

        return  this.anInt - o.anInt;
    }

    @Override
    public int compare(Apple o1, Apple o2) {

        return  o1.anInt - o2.anInt;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Apple apple = (Apple) obj;

        if (apple.anInt == this.anInt)return true;
        return false;}

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
