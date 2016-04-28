package com.epam.abstrFactory.factory;

import com.epam.abstrFactory.devise.Phone;
import com.epam.abstrFactory.devise.Tablet;
import com.epam.abstrFactory.devise.Watch;

public interface DeviseFactory {
    Phone  getPhone();
    Tablet getTablet();
    Watch  getWatch();

}
