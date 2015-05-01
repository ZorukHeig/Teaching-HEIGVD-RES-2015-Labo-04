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

    public HttpHeader(String value) {
        this();
        String[] pair = value.split(":", 2);
        pair[0] = pair[0].trim();
        pair[1] = pair[1].trim();
        this.name = pair[0];
        String[] values = pair[1].split(",");
        for (String s : values)
            this.values.add(s);
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
        String[] v = new String[values.size()];
        for (int i = 0; i < v.length; ++i) v[i] = values.get(i);
        return v;
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
