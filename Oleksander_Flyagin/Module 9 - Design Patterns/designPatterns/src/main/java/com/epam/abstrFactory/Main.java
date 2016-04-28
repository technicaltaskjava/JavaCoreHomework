package com.epam.abstrfactory;

import com.epam.abstrfactory.devise.Phone;
import com.epam.abstrfactory.devise.Tablet;
import com.epam.abstrfactory.devise.Watch;
import com.epam.abstrfactory.factory.DeviseFactory;

public class Main {
    private Main(){}

    public static void main(String[] args) {
        DeviseFactory iFactory = ChoiceFactory.getFactoryOS("MacOS");

        Tablet  iPadAire = iFactory.getTablet();
        Phone   iPhone   = iFactory.getPhone();
        Watch   iWatch   = iFactory.getWatch();

        iPadAire.getCalendar();
        iPadAire.getInternat();
        iPhone.getPhoto();
        iWatch.getPuls();


        DeviseFactory andrFactory = ChoiceFactory.getFactoryOS("Android");
        Tablet   asus    = andrFactory.getTablet();
        Phone    hts     = andrFactory.getPhone();
        Watch smartWatch = andrFactory.getWatch();

        asus.getCalendar();
        asus.getInternat();
        hts.getPhoto();
        smartWatch.getPuls();
        smartWatch.getTime();


    }
}
