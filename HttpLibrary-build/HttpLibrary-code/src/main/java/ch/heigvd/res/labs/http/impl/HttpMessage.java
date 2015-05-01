package ch.heigvd.res.labs.http.impl;

import ch.heigvd.res.labs.http.interfaces.IHttpHeader;
import ch.heigvd.res.labs.http.interfaces.IHttpMessage;

import java.util.*;

/**
 * Created by zoruk on 24.04.15.
 */
class HttpMessage implements IHttpMessage {
    public  HttpMessage() {
        body = new byte[0];
        headers = new HashMap<>();
    }

    @Override
    public Map<String, List<IHttpHeader>> getHeaders() {
        return headers;
    }

    @Override
    public List<IHttpHeader> getHeader(String field) {
        return headers.get(field.toLowerCase());
    }

    public void addHeader(IHttpHeader header) {
        if (!headers.containsKey(header.getName().toLowerCase()))
            headers.put(header.getName().toLowerCase(), new LinkedList<IHttpHeader>());

        headers.get(header.getName().toLowerCase()).add(header);
    }

    @Override
    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = Arrays.copyOf(body, body.length);
    }

    @Override
    public int getContentLength() {
        return 0;
    }

    @Override
    public String getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    private String protocolVersion;
    private Map<String, List<IHttpHeader>> headers;
    protected byte[] body;
}
