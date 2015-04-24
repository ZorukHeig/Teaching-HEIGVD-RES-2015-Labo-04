/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.res.labs.http.interfaces;

import java.io.IOException;

/**
 *
 * @author zoruk
 */
public interface IHttpClient {
    
    /**
     * 
     * @param request
     * @return 
     */
    public IHttpResponse sendRequest(IHttpRequest request) throws IOException, MalformedHttpResponseException;
    //public boolean set
}
