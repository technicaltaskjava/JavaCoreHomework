package com.epam.factory;

import com.epam.factory.type.GUI;
import com.epam.factory.widget.MotifWidgetFactory;
import com.epam.factory.widget.PMWidgetFactory;
import com.epam.factory.widget.WidgetFactory;

/**
 * Created by Olga Kramska on 24-Apr-16.
 */
public class FactoryProducer {

    private FactoryProducer() {
    }

    public static WidgetFactory getFactory(final GUI type) {
        if (type == GUI.MOTIF) {
            return new MotifWidgetFactory();
        } else {
            return new PMWidgetFactory();
        }
    }
}
