package ch.heigvd.res.labs.http.impl;

import ch.heigvd.res.labs.http.interfaces.IHttpHeader;
import ch.heigvd.res.labs.http.interfaces.IHttpRequest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by zoruk on 22.04.15.
 */
public class HttpRequest extends HttpMessage implements IHttpRequest {

    private URI uri;
    private String method;

    public  void setURI(URI uri) {
        this.uri = uri;
        addHeader(new HttpHeader("Host", uri.getHost()));
    }

    public void setURI(String url) throws URISyntaxException {
        setURI(new URI(url));
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public URI getURL() {
        return uri;
    }

    @Override
    public int getPort() {
        if (uri != null) {
            if (uri.getPort() != -1) {
                return uri.getPort();
            } else {
                return 80;
            }
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(method)
                .append(" ")
                .append(uri.getPath())
                .append(" ")
                .append(getProtocolVersion())
                .append("\n");
        Map<String, IHttpHeader> headers = getHeaders();
        for (IHttpHeader h : headers.values()) {
            builder.append(h.format()).append("\n");
        }
        builder.append("\n");
        return builder.toString();
    }
}
