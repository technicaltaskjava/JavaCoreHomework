package javase.t03.demo;

import javase.t03.builder.Url;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demo class to show results of applying the Builder pattern
 * Created by Yury Vislobodsky on 21.04.2016.
 */
public class UrlDemo {
    private static Logger logger = LoggerFactory.getLogger(UrlDemo.class);

    private UrlDemo() {}

    public static void logUrl(Url url) {
        logger.info("URL       : " + url);
        logger.info("protocol  : " + url.getProtocol());
        logger.info("domain    : " + url.getDomain());
        logger.info("port      : " + url.getPort());
        logger.info("path      : " + url.getPath());
        logger.info("query     : " + url.getQuery());
        logger.info("reference : " + url.getReference());
        logger.info("----------");
    }

    public static void main(String[] args) {
        Url httpUrl = new Url.Builder("domain.com").setProtocol(Url.Protocol.HTTP).
                setPath("doc.html").setReference("bookmark").createUrl();
        logUrl(httpUrl);

        Url httpsUrl = new Url.Builder("website.com").setProtocol(Url.Protocol.HTTPS).setPort(3000).
                setQuery("user=guest").createUrl();
        logUrl(httpsUrl);

        Url ftpUrl = new Url.Builder("myhost.com").setProtocol(Url.Protocol.FTP).setPort(21).
                setQuery("login=user").createUrl();
        logUrl(ftpUrl);
    }
}
