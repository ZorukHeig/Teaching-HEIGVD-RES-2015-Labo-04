package ch.heigvd.res.labs.http.impl;

import ch.heigvd.res.labs.http.interfaces.IHttpHeader;
import ch.heigvd.res.labs.http.interfaces.IHttpMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zoruk on 24.04.15.
 */
class HttpMessage implements IHttpMessage {
    public  HttpMessage() {
        headers = new HashMap<>();
    }

    @Override
    public Map<String, IHttpHeader> getHeaders() {
        return headers;
    }

    @Override
    public IHttpHeader getHeader(String field) {
        return null;
    }

    public void addHeader(IHttpHeader header) {
        headers.put(header.getName().toLowerCase(), header);
    }

    @Override
    public byte[] getBody() {
        return new byte[0];
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
    private Map<String, IHttpHeader> headers;
}
