package javase.t02.concretefactory;

import javase.t02.abstractapp.App;
import javase.t02.abstractfactory.AppFactory;
import javase.t02.concreteapp.WindowsApp;

/**
 * Concrete factory to create a Windows application
 * Created by Yury Vislobodsky on 24.04.2016.
 */
public class WindowsAppFactory implements AppFactory {

    @Override
    public App createApp() {
        return new WindowsApp();
    }
}
