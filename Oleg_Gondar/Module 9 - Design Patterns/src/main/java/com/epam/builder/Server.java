package com.epam.builder;

/**
 * Created by o.gondar on 26.04.2016.
 */
public class Server {

    private String serverName;
    private String serverStorage;
    private String serverNetwork;

    private Server(){}

    public String getServerName() {
        return serverName;
    }

    public String getServerStorage() {
        return serverStorage;
    }

    public String getServerNetwork() {
        return serverNetwork;
    }

    public static Builder newBuilder(){
        return new Server().new Builder();
    }

    public class Builder{
        private Builder(){}

        public Builder setServerName(String serverName){
            Server.this.serverName = serverName;
            return this;
        }
        public Builder setServerStorage(String serverStorage){
            Server.this.serverStorage = serverStorage;
            return this;
        }
        public Builder setServerNetwork(String serverNetwork){
            Server.this.serverNetwork = serverNetwork;
            return this;
        }
        public Server build(){
            return Server.this;
        }
    }
}
