/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dz.univjijel.jsentiwordnet.ws;

import dz.univjijel.jsentiwordnet.core.JSentiWordNet;
import dz.univjijel.jsentiwordnet.dict.Synset;
import java.util.ArrayList;
import javax.jws.WebService;

/**
 *
 * @author Tarek Boutefara <t_boutefara@esi.dz>
 */
@WebService(endpointInterface = "dz.univjijel.jsentiwordnet.ws.WebInterrogation")
public class WebInterrogationImpl implements WebInterrogation {

    @Override
    public Synset getById(String id) {
        return JSentiWordNet.findById(id);
    }

    @Override
    public ArrayList<Synset> getByTerm(String term) {
        return (ArrayList)JSentiWordNet.findTerm(term);
    }
    
}
