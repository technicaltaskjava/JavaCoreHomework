package com.epam.abstrfactory.factory;

import com.epam.abstrfactory.devise.Phone;
import com.epam.abstrfactory.devise.Tablet;
import com.epam.abstrfactory.devise.Watch;

public interface DeviseFactory {
    Phone  getPhone();
    Tablet getTablet();
    Watch  getWatch();

}
