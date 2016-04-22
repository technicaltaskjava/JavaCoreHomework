package com.epam.taskMultithreading;


public class Main {
    private Main() {
    }


    public static void main(String[] args) {
        Commands commands = new Commands();
        System.out.println("Save in deferent colletctions");
        commands.writeListsToList();
        System.out.println("Save in one colletction");
        commands.writeInOneList();
    }
}
