package ch.heigvd.res.labs.http.impl;

import ch.heigvd.res.labs.http.interfaces.IHttpHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zoruk on 24.04.15.
 */
public class HttpHeader implements IHttpHeader {
    private List<String> values;
    private String name;

    public HttpHeader() {
        values = new ArrayList<>();
    }

    public HttpHeader(String name, String value) {
        this();
        this.name = name;
        values.add(value);
    }

    public void addValue(String value) {
        values.add(value);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String[] getValues() {
        return (String[])values.toArray();
    }

    @Override
    public String format() {
        StringBuilder builder = new StringBuilder();
        builder.append(name).append(": ");
        boolean first = true;
        for (String s : values) {
            if (!first) {
                builder.append(",");
            }
            first = false;
            builder.append(s);
        }
        return builder.toString();
    }

    public void setName(String name) {
        this.name = name;
    }
}
