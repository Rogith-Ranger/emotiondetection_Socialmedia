/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dz.univjijel.jsentiwordnet.ws;

import javax.xml.ws.Endpoint;

/**
 *
 * @author Tarek Boutefara <t_boutefara@esi.dz>
 */
public class WebInterrogationPublisher {
    
    public static void startService(){
        Endpoint.publish("http://localhost:6547/jsentiwordnet/interrogation", new WebInterrogationImpl());
    }
    
}
