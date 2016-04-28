package com.epam.abstrFactory.android;

import com.epam.abstrFactory.devise.Phone;

public class PhoneAndroid  extends AndroidOS implements Phone{
    private String type ="--------------PhoneAndroid------------------";

    @Override
    public void getCalle() {
        System.out.println(type);
        System.out.println("Hello! How are you?");
    }

    @Override
    public void getPhoto() {
        System.out.println(type);
        System.out.println("Your nuber's 077 77 77 777");
    }
}
