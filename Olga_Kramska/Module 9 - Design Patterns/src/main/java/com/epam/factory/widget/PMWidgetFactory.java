package com.epam.factory.widget;

import com.epam.factory.item.PMScrollBar;
import com.epam.factory.item.PMWindow;
import com.epam.factory.item.ScrollBar;
import com.epam.factory.item.Window;

/**
 * Created by Olga Kramska on 24-Apr-16.
 */
public class PMWidgetFactory extends WidgetFactory {
    @Override
    public ScrollBar createScrollBar() {
        return new PMScrollBar();
    }

    @Override
    public Window createWindow() {
        return new PMWindow();
    }
}
