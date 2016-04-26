package com.epam.strategy;

/**
 * Created by o.gondar on 26.04.2016.
 */
public class Run {
    private Run() {
    }

    public static void main(String[] args) {
        Router router = new Router(new OSPFRoutingProcess());
        router.route();
        //Changing behavior
        router.setRoutingProcess(new BGPRoutingProcess());
        router.route();
    }

}
