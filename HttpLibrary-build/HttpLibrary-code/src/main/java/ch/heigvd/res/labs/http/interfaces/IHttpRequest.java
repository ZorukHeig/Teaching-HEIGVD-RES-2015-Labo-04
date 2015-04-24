/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.res.labs.http.interfaces;

import java.net.URI;

/**
 *
 * @author zoruk
 */
public interface IHttpRequest extends IHttpMessage {
    public String getMethod();
    public URI getURL();
    public int getPort();

    @Override
    public String toString();
}
