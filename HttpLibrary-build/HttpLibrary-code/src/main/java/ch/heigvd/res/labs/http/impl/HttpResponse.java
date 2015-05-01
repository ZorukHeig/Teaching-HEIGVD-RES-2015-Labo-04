package ch.heigvd.res.labs.http.impl;

import ch.heigvd.res.labs.http.interfaces.IHttpHeader;
import ch.heigvd.res.labs.http.interfaces.IHttpResponse;
import ch.heigvd.res.labs.http.interfaces.MalformedHttpResponseException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by zoruk on 24.04.15.
 */
public class HttpResponse extends HttpMessage implements IHttpResponse {
    private int statusCode;
    private String reasonPhrase;

    public HttpResponse() {
        super();
    }

    public HttpResponse(HttpBufferedInputStream reader) throws MalformedHttpResponseException, IOException {
        this();
        String line = reader.readLine();
        String[] requestSplit = line.split(" ", 3);
        if (requestSplit.length != 3) {
            throw new MalformedHttpResponseException();
        }
        setProtocolVersion(requestSplit[0]);
        statusCode = Integer.parseInt(requestSplit[1]);
        reasonPhrase = requestSplit[2];

        line = reader.readLine();
        while(line.length() != 0) {
            addHeader(new HttpHeader(line));
            line = reader.readLine();
        }

        List<IHttpHeader> contentLengthHeader = getHeader("Content-Length");
        List<IHttpHeader> transferEncodingHeader = getHeader("Transfer-Encoding");
        if (transferEncodingHeader != null) {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            String chunkHeader[] = reader.readLine().split(" ", 2);
            int chunkSize = Integer.parseInt(chunkHeader[0], 16);
            while (chunkSize > 0) {
                byte[] tmp = new byte[chunkSize];
                reader.read(tmp, 0, chunkSize);
                reader.skip(2); // CRLF
                buffer.write(tmp);
                chunkHeader = reader.readLine().split(" ", 2);
                chunkSize = Integer.parseInt(chunkHeader[0], 16);
            }
            body = buffer.toByteArray();

            if (contentLengthHeader != null) {
                contentLengthHeader.clear();
            }

            addHeader(new HttpHeader("Content-Length", String.valueOf(body.length)));

        } else if (contentLengthHeader != null) {
            System.out.println(contentLengthHeader.get(0).format() + " " +  reader.available());
            IHttpHeader h = contentLengthHeader.get(0);
            int length = Integer.parseInt(h.getValues()[0]);
            body = new byte[length];
            reader.read(body, 0, length);
        }
    }
    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getReasonPhrase() {
        return reasonPhrase;
    }
}
