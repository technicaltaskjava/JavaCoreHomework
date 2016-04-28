package com.epam.strategy;

/**
 * Created by o.gondar on 26.04.2016.
 */
public class BGPRoutingProcess implements RoutingProcess {
    public void route() {
        System.out.println("BGP is routing!");
    }
}
