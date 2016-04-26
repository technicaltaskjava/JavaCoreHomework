package com.epam.abstrFactory.factory;

import com.epam.abstrFactory.android.PhoneAndroid;
import com.epam.abstrFactory.android.TabletAndroid;
import com.epam.abstrFactory.android.WatchAndroid;
import com.epam.abstrFactory.devise.Phone;
import com.epam.abstrFactory.devise.Tablet;
import com.epam.abstrFactory.devise.Watch;

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