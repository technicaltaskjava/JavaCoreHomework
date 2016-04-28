package com.epam.abstrfactory;

import com.epam.abstrfactory.factory.AndroidDeviseFactory;
import com.epam.abstrfactory.factory.DeviseFactory;
import com.epam.abstrfactory.factory.IOSDeviseFactory;


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
