package ch.heigvd.res.labs.http.impl;

import ch.heigvd.res.labs.http.interfaces.IHttpClient;
import ch.heigvd.res.labs.http.interfaces.IHttpRequest;
import ch.heigvd.res.labs.http.interfaces.IHttpResponse;
import ch.heigvd.res.labs.http.interfaces.MalformedHttpResponseException;

import java.io.*;
import java.net.Socket;
import java.net.URI;

/**
 * Created by zoruk on 22.04.15.
 */
public class HttpClient implements IHttpClient {

    @Override
    public IHttpResponse sendRequest(IHttpRequest request) throws IOException, MalformedHttpResponseException {
        URI uri = request.getURL();
        Socket socket = new Socket(uri.getHost(), request.getPort());
        if (!socket.isConnected()) {
            return null;
        }

        request.addHeader(new HttpHeader("Connection", "Close"));

        PrintWriter writer = new PrintWriter(socket.getOutputStream(),
                true);

        writer.write(request.toString());

        writer.flush();


        HttpBufferedInputStream reader = new HttpBufferedInputStream(socket.getInputStream());

        HttpResponse response = new HttpResponse(reader);
        socket.close();
        return response;
    }
}
