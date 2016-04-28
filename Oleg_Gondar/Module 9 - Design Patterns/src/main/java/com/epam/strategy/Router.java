package com.epam.strategy;

/**
 * Created by o.gondar on 26.04.2016.
 */
public class Router {
    private RoutingProcess routingProcess;

    public Router(RoutingProcess routingProcess) {
        this.routingProcess = routingProcess;
    }

    public void setRoutingProcess(RoutingProcess routingProcess) {
        this.routingProcess = routingProcess;
    }

    public void route() {
        routingProcess.route();
    }
}
