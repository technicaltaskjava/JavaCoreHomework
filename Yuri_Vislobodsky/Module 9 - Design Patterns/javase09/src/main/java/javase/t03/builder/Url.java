package javase.t03.builder;

/**
 * Class to generate correct URL string
 * Created by Yury Vislobodsky on 21.04.2016.
 */
public class Url {
    private final Protocol protocol;
    private final String domain;
    private final int port;
    private final String path;
    private final String query;
    private final String reference;

    private Url(Builder builder) {
        this.protocol = builder.protocol;
        this.domain = builder.domain;
        this.port = builder.port;
        this.path = builder.path;
        this.query = builder.query;
        this.reference = builder.reference;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public String getDomain() {
        return domain;
    }

    public int getPort() {
        return port;
    }

    public String getPath() {
        return path;
    }

    public String getQuery() {
        return query;
    }

    public String getReference() {
        return reference;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        if (protocol != null) {
            string.append(protocol.toString().toLowerCase()).append("://");
        }
        string.append(domain);
        if (port != 0) {
            string.append(":").append(port);
        }
        if (path != null && !"".equals(path)) {
            string.append("/").append(path);
        }
        if (query != null && !"".equals(query)) {
            string.append("?").append(query);
        }
        if (reference != null && !"".equals(reference)) {
            string.append("#").append(reference);
        }
        return string.toString();
    }

    public enum Protocol {
        HTTP, HTTPS, FTP
    }

    public static class Builder {
        private Protocol protocol = Protocol.HTTP;      // default
        private String domain;                          // required
        private int port = 0;
        private String path = "";
        private String query = "";
        private String reference = "";

        public Builder(String domain) {
            this.domain = domain;
        }

        public Builder setProtocol(Protocol protocol) {
            this.protocol = protocol;
            return this;
        }

        public Builder setPort(int port) {
            this.port = port;
            return this;
        }

        public Builder setPath(String path) {
            this.path = path;
            return this;
        }

        public Builder setQuery(String query) {
            this.query = query;
            return this;
        }

        public Builder setReference(String reference) {
            this.reference = reference;
            return this;
        }

        public Url createUrl() {
            if (domain == null) {
                throw new IllegalArgumentException();
            }
            return new Url(this);
        }
    }
}
