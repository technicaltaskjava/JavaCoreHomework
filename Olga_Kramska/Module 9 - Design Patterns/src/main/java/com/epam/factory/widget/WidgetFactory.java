package com.epam.factory.widget;

import com.epam.factory.item.ScrollBar;
import com.epam.factory.item.Window;

/**
 * Created by Olga Kramska on 24-Apr-16.
 */
public abstract class WidgetFactory {
    public abstract ScrollBar createScrollBar();
    public abstract Window createWindow();
}
