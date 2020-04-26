/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dz.univjijel.jsentiwordnet.dict.util;

import dz.univjijel.jsentiwordnet.core.Configuration;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tarek Boutefara <t_boutefara@esi.dz>
 */
public class ConfigurationTest {
    
    public ConfigurationTest() {
    }

    /**
     * Test of getDictPath method, of class Configuration.
     */
    @Test
    public void testGetDictPath() {
        System.out.println("getDictPath");
        Configuration instance = new Configuration();
        String expResult = "./SentiWordNet.txt";
        String result = instance.getDictPath();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDictPath method, of class Configuration.
     */
    @Test
    public void testSetDictPath() {
        System.out.println("setDictPath");
        String dictPath = "./NewSentiWordNet.txt";
        Configuration instance = new Configuration();
        instance.setDictPath(dictPath);
        String newPath = instance.getDictPath();
        assertEquals(newPath, dictPath);
    }
    
}
