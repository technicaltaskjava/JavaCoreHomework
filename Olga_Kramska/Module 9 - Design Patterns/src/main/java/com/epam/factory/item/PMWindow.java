package com.epam.factory.item;

/**
 * Created by Olga Kramska on 24-Apr-16.
 */
public class PMWindow implements Window{
    @Override
    public void draw() {
        System.out.println("Drawing window in a Presentation Manager stile");
    }
}
