package com.epam.builder;

/**
 * Created by o.gondar on 26.04.2016.
 */
public class Run {
    private Run() {
    }

    public static void main(String[] args) {
        Server hpServer = Server.newBuilder()
                .setServerName("HP server")
                .setServerNetwork("HP server network")
                .setServerStorage("HP server storage")
                .build();

        Server.Builder serverBuilder = Server.newBuilder();
        serverBuilder.setServerName("IBM server");
        serverBuilder.setServerNetwork("IBM server network");
        serverBuilder.setServerStorage("IBM server storage");
        Server ibmServer = serverBuilder.build();

        showServerConfig(hpServer);
        showServerConfig(ibmServer);

    }

    public static void showServerConfig(Server server) {
        System.out.println(server.getServerName());
        System.out.println(server.getServerStorage());
        System.out.println(server.getServerNetwork());
    }
}
