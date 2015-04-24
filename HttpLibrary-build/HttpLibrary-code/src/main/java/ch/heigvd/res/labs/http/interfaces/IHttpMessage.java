/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.res.labs.http.interfaces;

import java.util.Map;

/**
 *
 * @author zoruk
 */
public interface IHttpMessage {
    public Map<String, IHttpHeader> getHeaders();
    public IHttpHeader getHeader(String field);
    public byte[] getBody();
    public int getContentLength();
    public String getProtocolVersion();
}
