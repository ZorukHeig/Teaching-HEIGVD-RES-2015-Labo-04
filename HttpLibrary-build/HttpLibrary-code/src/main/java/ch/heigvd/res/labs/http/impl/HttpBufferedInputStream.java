package ch.heigvd.res.labs.http.impl;

import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * Created by zoruk on 24.04.15.
 */
public class HttpBufferedInputStream extends BufferedInputStream {
    public HttpBufferedInputStream(InputStream inputStream) {
        super(inputStream);
    }

    public String readLine() throws IOException {

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int data = read();
        while (data != -1) {
            System.out.print((char)data);
            if ((char)data == '\r') {
                data = read();
                if (data != -1 && (char)data == '\n')
                    break;
                else {
                    buffer.write('\r');
                }
            }
            buffer.write(data);
            data = read();
        }
        return buffer.toString();
    }
}
