package com.epam.factory.widget;

import com.epam.factory.item.MotifScrollBar;
import com.epam.factory.item.MotifWindow;
import com.epam.factory.item.ScrollBar;
import com.epam.factory.item.Window;

/**
 * Created by Olga Kramska on 24-Apr-16.
 */
public class MotifWidgetFactory extends WidgetFactory {
    @Override
    public ScrollBar createScrollBar() {
        return new MotifScrollBar();
    }

    @Override
    public Window createWindow() {
        return new MotifWindow();
    }
}
