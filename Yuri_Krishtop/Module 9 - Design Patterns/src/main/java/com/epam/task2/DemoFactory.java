package com.epam.task2;

/**
 * Created by Yuriy Krishtop on 24.04.2016.
 */
public class DemoFactory {

    private DemoFactory() {
    }

    public static void main() {
        System.out.println(MotoFactory.makeMoto(MotoType.SPORTBIKE, Location.GERMANY));
        System.out.println(MotoFactory.makeMoto(MotoType.STANDARD, Location.GERMANY));
        System.out.println(MotoFactory.makeMoto(MotoType.CRUISER, Location.GERMANY));
        System.out.println(MotoFactory.makeMoto(MotoType.TOURING, Location.GERMANY));
        System.out.println(MotoFactory.makeMoto(MotoType.OFFROAD, Location.GERMANY));
        System.out.println(MotoFactory.makeMoto(MotoType.SPORTBIKE, Location.JAPAN));
        System.out.println(MotoFactory.makeMoto(MotoType.STANDARD, Location.JAPAN));
        System.out.println(MotoFactory.makeMoto(MotoType.CRUISER, Location.JAPAN));
        System.out.println(MotoFactory.makeMoto(MotoType.TOURING, Location.JAPAN));
        System.out.println(MotoFactory.makeMoto(MotoType.OFFROAD, Location.JAPAN));
        System.out.println(MotoFactory.makeMoto(MotoType.SPORTBIKE, Location.ITALY));
        System.out.println(MotoFactory.makeMoto(MotoType.STANDARD, Location.ITALY));
        System.out.println(MotoFactory.makeMoto(MotoType.CRUISER, Location.ITALY));
        System.out.println(MotoFactory.makeMoto(MotoType.TOURING, Location.ITALY));
        System.out.println(MotoFactory.makeMoto(MotoType.OFFROAD, Location.ITALY));
        System.out.println(MotoFactory.makeMoto(MotoType.SPORTBIKE, Location.CHINA));
        System.out.println(MotoFactory.makeMoto(MotoType.STANDARD, Location.CHINA));
        System.out.println(MotoFactory.makeMoto(MotoType.CRUISER, Location.CHINA));
        System.out.println(MotoFactory.makeMoto(MotoType.TOURING, Location.CHINA));
        System.out.println(MotoFactory.makeMoto(MotoType.OFFROAD, Location.CHINA));
        System.out.println(MotoFactory.makeMoto(MotoType.SPORTBIKE, Location.USA));
        System.out.println(MotoFactory.makeMoto(MotoType.STANDARD, Location.USA));
        System.out.println(MotoFactory.makeMoto(MotoType.CRUISER, Location.USA));
        System.out.println(MotoFactory.makeMoto(MotoType.TOURING, Location.USA));
        System.out.println(MotoFactory.makeMoto(MotoType.OFFROAD, Location.USA));
    }
}
