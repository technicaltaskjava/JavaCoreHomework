package javase.t03.builder;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test for Url class
 * Checks URL string, generated according to initial parameters
 * Created by Yury on 22.04.2016.
 */
public class UrlTest {

    @Test
    public void testUrl() {

        assertTrue("error in HTTP Url", "http://domain.com/doc.html#bookmark".equals(
                new Url.Builder("domain.com").setProtocol(Url.Protocol.HTTP).
                        setPath("doc.html").setReference("bookmark").createUrl().toString()));

        assertTrue("error in HTTPS Url", "https://website.com:3000?user=guest".equals(
                new Url.Builder("website.com").setProtocol(Url.Protocol.HTTPS).
                        setPort(3000).setQuery("user=guest").createUrl().toString()));

        assertTrue("error in FTP Url", "ftp://myhost.com:21?login=user".equals(
                new Url.Builder("myhost.com").setProtocol(Url.Protocol.FTP).setPort(21).
                        setQuery("login=user").createUrl().toString()));
    }
}