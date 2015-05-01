package ch.heigvd.res.labs.http.impl;


import ch.heigvd.res.labs.http.interfaces.IHttpClient;
import ch.heigvd.res.labs.http.interfaces.IHttpHeader;
import ch.heigvd.res.labs.http.interfaces.IHttpResponse;
import ch.heigvd.res.labs.http.interfaces.MalformedHttpResponseException;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 * @author Olivier Liechti
 */
public class HttpClientTest {

    static final String HTTP_200_URI = "http://www.heig-vd.ch/";
    static final String HTTP_302_URI = "http://www.google.com/";
    static final String HTTP_CUNKED_BODY = "http://www.httpwatch.com/httpgallery/chunked/chunkedimage.aspx";

    @Test
    public void itShouldBePossibleToSendAGETRequest() throws IOException, URISyntaxException, MalformedHttpResponseException {
        IHttpClient client = new HttpClient();
        HttpRequest request = new HttpRequest();
        request.setMethod("GET");
        request.setProtocolVersion("HTTP/1.1");
        request.setURI(HTTP_200_URI);
        IHttpResponse response = client.sendRequest(request);

        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void isShouldBePossibleToGetA302Response() throws IOException, URISyntaxException, MalformedHttpResponseException {
        IHttpClient client = new HttpClient();
        HttpRequest request = new HttpRequest();
        request.setMethod("GET");
        request.setProtocolVersion("HTTP/1.1");
        request.setURI(HTTP_302_URI);
        IHttpResponse response = client.sendRequest(request);

        assertEquals(302, response.getStatusCode());
    }

    @Test
    public void itShouldBePossibleToGetHeaders() throws IOException, URISyntaxException, MalformedHttpResponseException {
        IHttpClient client = new HttpClient();
        HttpRequest request = new HttpRequest();
        request.setMethod("GET");
        request.setProtocolVersion("HTTP/1.1");
        request.setURI(HTTP_200_URI);
        IHttpResponse response = client.sendRequest(request);

        assertEquals(200, response.getStatusCode());
        assertNotEquals(null, response.getHeaders());
        assertNotEquals(0, response.getHeaders().size());
    }

    @Test
    public void itShouldBePossibleToGetABodyBasedOnContentLength() throws IOException, URISyntaxException, MalformedHttpResponseException {
        IHttpClient client = new HttpClient();
        HttpRequest request = new HttpRequest();
        request.setMethod("GET");
        request.setProtocolVersion("HTTP/1.1");
        request.setURI(HTTP_200_URI);
        IHttpResponse response = client.sendRequest(request);

        assertEquals(200, response.getStatusCode());

        byte[] b = response.getBody();
        assertNotEquals(null, b);
        assertNotEquals(0, b.length);
    }

    @Test
    public void isShouldBePossibleToReadAChunkedResponse() throws URISyntaxException, IOException, MalformedHttpResponseException, NoSuchAlgorithmException {
        IHttpClient client = new HttpClient();
        HttpRequest request = new HttpRequest();
        request.setMethod("GET");
        request.setProtocolVersion("HTTP/1.1");
        request.setURI(HTTP_CUNKED_BODY);
        IHttpResponse response = client.sendRequest(request);

        for (List<IHttpHeader> headers : response.getHeaders().values())
            for (IHttpHeader h : headers)
                System.out.println(h.format());

        FileOutputStream fos = new FileOutputStream("/tmp/img.jpg");
        fos.write(response.getBody());
        fos.close();

        assertEquals(200, response.getStatusCode());
    }
}
