package com.epam.dao;

import com.epam.dao.dao.DaoImplement;
import com.epam.dao.objects.Cookie;
import com.epam.dao.objects.Metadata;
import com.epam.dao.objects.User;

public class DaoRun {

    private DaoRun() {
    }

    public static void daoRun() {
        DaoImplement daoImplement = new DaoImplement();

        User currentUser = new User(0, "user8", "user8@company.com", "111111", "1988-03-03");
        daoImplement.addUser(currentUser);
        System.out.println("User user8 added.\n");

        Cookie currentCookie = new Cookie(0, "Be receptive to popular ideas.", true);
        daoImplement.addCookie(currentCookie);
        System.out.println("Cookie 'Be receptive to popular ideas.' added.\n");

        Metadata currentMetadata = new Metadata(2, 3, "");
        daoImplement.addMetadata(currentMetadata);
        System.out.println("New record at metadata added.\n");

        daoImplement.deleteUser(5);
        daoImplement.deleteCookie(5);
        System.out.println("User with id=5 deleted. Cookie with id=5 deleted.\n");

        System.out.println("\nAll records from users:");
        for (User currentData: daoImplement.getAllUsers()) {
          System.out.println(currentData.getId() + " | " + currentData.getUserName() + " | " +
                            currentData.getEmail() + " | " + currentData.getPassword() + " | " +
                            currentData.getDateOfBirth());
        }

        System.out.println("\nAll records from cookies:");
        for (Cookie currentData: daoImplement.getAllCookies()) {
          System.out.println(currentData.getId() + " | " + currentData.getCookieName() + " | " +
                  currentData.isActive());
        }

        System.out.println("\nAll records from metadata:");
        for (Metadata currentData: daoImplement.getAllMetadata()) {
            System.out.println(currentData.getUserId() + " | " + currentData.getCookieId() + " | " +
                    currentData.getTimeAdded());
        }

        System.out.println("\nUser with id=2:");
        currentUser = daoImplement.getUser(2);
        System.out.println(currentUser.getId() + " | " + currentUser.getUserName() + " | " +
                currentUser.getEmail() + " | " + currentUser.getPassword() + " | " +
                currentUser.getDateOfBirth());

        System.out.println("\nCookie with id=3:");
        currentCookie = daoImplement.getCookie(3);
        System.out.println(currentCookie.getId() + " | " + currentCookie.getCookieName() + " | " +
                currentCookie.isActive());

        System.out.println("\nFind user with name 'admin':");
        for (User currentData: daoImplement.getUserByName("admin")) {
            System.out.println(currentData.getId() + " | " + currentData.getUserName() + " | " +
                    currentData.getEmail() + " | " + currentData.getPassword() + " | " +
                    currentData.getDateOfBirth());
        }

        System.out.println("\nFind cookie which contains 'way':");
        for (Cookie currentData: daoImplement.findCookieByText("way")) {
            System.out.println(currentData.getId() + " | " + currentData.getCookieName() + " | " +
                    currentData.isActive());
        }

        System.out.println("\nFind metadata with user_id=4 and cookie_id=3:");
        for (Metadata currentData: daoImplement.findMetadata(4, 3)) {
            System.out.println(currentData.getUserId() + " | " + currentData.getCookieId() + " | " +
                    currentData.getTimeAdded());
        }

        daoImplement.closePool();
    }
}
