package com.epam.abstrfactory.factory;

import com.epam.abstrfactory.android.PhoneAndroid;
import com.epam.abstrfactory.android.TabletAndroid;
import com.epam.abstrfactory.android.WatchAndroid;
import com.epam.abstrfactory.devise.Phone;
import com.epam.abstrfactory.devise.Tablet;
import com.epam.abstrfactory.devise.Watch;

public class AndroidDeviseFactory implements DeviseFactory {
    @Override
    public Phone getPhone() {
        return new PhoneAndroid();
    }

    @Override
    public Tablet getTablet() {
        return new TabletAndroid();
    }

    @Override
    public Watch getWatch() {
        return new WatchAndroid();
    }
}