package com.epam.strategy;

/**
 * Created by o.gondar on 26.04.2016.
 */
public class OSPFRoutingProcess implements RoutingProcess {
    public void route() {
        System.out.println("OSPF is routing!");
    }
}
