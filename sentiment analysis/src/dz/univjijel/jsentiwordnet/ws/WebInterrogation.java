/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dz.univjijel.jsentiwordnet.ws;

import dz.univjijel.jsentiwordnet.dict.Synset;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 *
 * @author Tarek Boutefara <t_boutefara@esi.dz>
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface WebInterrogation {
    
    @WebMethod public Synset getById(String id);
    @WebMethod public ArrayList<Synset> getByTerm(String term);
    
}
