package ch.heigvd.res.labs.http.impl;


import ch.heigvd.res.labs.http.interfaces.IHttpClient;
import ch.heigvd.res.labs.http.interfaces.IHttpResponse;
import ch.heigvd.res.labs.http.interfaces.MalformedHttpResponseException;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

/**
 *
 * @author Olivier Liechti
 */
public class HttpClientTest {
    @Test
    public void itShouldBePossibleToSendAGETRequest() throws IOException, URISyntaxException, MalformedHttpResponseException {
        IHttpClient client = new HttpClient();
        HttpRequest request = new HttpRequest();
        request.setMethod("GET");
        request.setProtocolVersion("HTTP/1.1");
        request.setURI("http://www.heig-vd.ch/");
        IHttpResponse response = client.sendRequest(request);

        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void isShouldBePossibleToGetA302Response() throws IOException, URISyntaxException, MalformedHttpResponseException {
        IHttpClient client = new HttpClient();
        HttpRequest request = new HttpRequest();
        request.setMethod("GET");
        request.setProtocolVersion("HTTP/1.1");
        request.setURI("http://www.google.com/");
        IHttpResponse response = client.sendRequest(request);

        assertEquals(302, response.getStatusCode());
    }
}
