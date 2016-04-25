package javase.t02.abstractfactory;

import javase.t02.abstractapp.App;

/**
 * Our abstract factory to create an application
 * Created by Yury Vislobodsky on 24.04.2016.
 */
public interface AppFactory {
    App createApp();
}
