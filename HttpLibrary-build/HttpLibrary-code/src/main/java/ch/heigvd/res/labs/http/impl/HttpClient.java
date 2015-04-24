package ch.heigvd.res.labs.http.impl;

import ch.heigvd.res.labs.http.interfaces.IHttpClient;
import ch.heigvd.res.labs.http.interfaces.IHttpRequest;
import ch.heigvd.res.labs.http.interfaces.IHttpResponse;
import ch.heigvd.res.labs.http.interfaces.MalformedHttpResponseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

        PrintWriter writer = new PrintWriter(socket.getOutputStream(),
                true);

        System.out.println(request.toString());
        writer.write(request.toString());

        writer.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));


        HttpResponse response = new HttpResponse();
        response.parse(reader);
        return response;
    }
}
