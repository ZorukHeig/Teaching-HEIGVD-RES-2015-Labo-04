package ch.heigvd.res.labs.http.impl;

import ch.heigvd.res.labs.http.interfaces.IHttpResponse;
import ch.heigvd.res.labs.http.interfaces.MalformedHttpResponseException;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by zoruk on 24.04.15.
 */
public class HttpResponse extends HttpMessage implements IHttpResponse {
    private int statusCode;
    private String reasonPhrase;

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void parse(BufferedReader reader) throws IOException, MalformedHttpResponseException {
        String request = reader.readLine();
        String[] requestSplited = request.split(" ", 3);
        if (requestSplited.length != 3) {
            throw new MalformedHttpResponseException();
        }
        setProtocolVersion(requestSplited[0]);
        statusCode = Integer.parseInt(requestSplited[1]);
        reasonPhrase = requestSplited[2];
    }
}
