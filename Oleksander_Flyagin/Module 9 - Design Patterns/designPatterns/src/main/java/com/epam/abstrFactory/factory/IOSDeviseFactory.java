package com.epam.abstrFactory.factory;

import com.epam.abstrFactory.devise.Phone;
import com.epam.abstrFactory.devise.Tablet;
import com.epam.abstrFactory.devise.Watch;
import com.epam.abstrFactory.ios.PhoneIOS;
import com.epam.abstrFactory.ios.TabletIOS;
import com.epam.abstrFactory.ios.WatchIOS;

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
