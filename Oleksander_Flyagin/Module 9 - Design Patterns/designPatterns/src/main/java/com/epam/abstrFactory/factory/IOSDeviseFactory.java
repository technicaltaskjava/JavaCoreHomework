package com.epam.abstrfactory.factory;

import com.epam.abstrfactory.devise.Phone;
import com.epam.abstrfactory.devise.Tablet;
import com.epam.abstrfactory.devise.Watch;
import com.epam.abstrfactory.ios.PhoneIOS;
import com.epam.abstrfactory.ios.TabletIOS;
import com.epam.abstrfactory.ios.WatchIOS;

public class IOSDeviseFactory implements DeviseFactory {
    @Override
    public Phone getPhone() {
        return new PhoneIOS();
    }

    @Override
    public Tablet getTablet() {
        return new TabletIOS();
    }

    @Override
    public Watch getWatch() {
        return new WatchIOS();
    }
}
