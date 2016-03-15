package ua.valentin.fileworks;

import ua.valentin.fileworks.controller.Controller;

/**
 * Created by valentin.yakimenko on 15.03.16.
 */
public class Run {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.commandLine();
    }
 }
