package com.epam.abstrFactory;

import com.epam.abstrFactory.factory.AndroidDeviseFactory;
import com.epam.abstrFactory.factory.DeviseFactory;
import com.epam.abstrFactory.factory.IOSDeviseFactory;


public class ChoiceFactory {
    private ChoiceFactory() {
    }


    static DeviseFactory getFactoryOS(String os) {


        switch (os) {
            case "MacOS":
                return new IOSDeviseFactory();

            case "Android":
                return new AndroidDeviseFactory();

            default:
                throw new NoFoundOS(os);
        }

    }

}
