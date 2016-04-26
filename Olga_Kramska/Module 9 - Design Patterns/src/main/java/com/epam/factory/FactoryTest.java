package com.epam.factory;

import com.epam.factory.item.ScrollBar;
import com.epam.factory.item.Window;
import com.epam.factory.type.GUI;
import com.epam.factory.widget.WidgetFactory;

/**
 * Created by Olga Kramska on 24-Apr-16.
 */
public class FactoryTest {

    private FactoryTest() {
    }

    public static void main(String[] args) {
        WidgetFactory motifFactory = FactoryProducer.getFactory(GUI.MOTIF);
        ScrollBar motifScrollBar = motifFactory.createScrollBar();
        Window motifWindow = motifFactory.createWindow();
        motifScrollBar.draw();
        motifWindow.draw();

        WidgetFactory pmFactory = FactoryProducer.getFactory(GUI.PM);
        ScrollBar pmScrollBar = pmFactory.createScrollBar();
        Window pmWindow = pmFactory.createWindow();
        pmScrollBar.draw();
        pmWindow.draw();
    }
}
