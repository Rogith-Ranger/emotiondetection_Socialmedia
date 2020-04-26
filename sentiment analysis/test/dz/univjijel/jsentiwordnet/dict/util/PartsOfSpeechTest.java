/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dz.univjijel.jsentiwordnet.dict.util;

import dz.univjijel.jsentiwordnet.dict.util.PartsOfSpeech;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tarek Boutefara <t_boutefara@esi.dz>
 */
public class PartsOfSpeechTest {
    
    public PartsOfSpeechTest() {
    }
    
    /**
     * Test of valueOf method, of class PartsOfSpeech.
     */
    @Test
    public void testValueOfNoun() {
        System.out.println("valueOf Noun");
        String name = "Noun";
        PartsOfSpeech expResult = PartsOfSpeech.Noun;
        PartsOfSpeech result = PartsOfSpeech.valueOf(name);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of valueOf method, of class PartsOfSpeech.
     */
    @Test
    public void testValueOfVerb() {
        System.out.println("valueOf Verb");
        String name = "Verb";
        PartsOfSpeech expResult = PartsOfSpeech.Verb;
        PartsOfSpeech result = PartsOfSpeech.valueOf(name);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of valueOf method, of class PartsOfSpeech.
     */
    @Test
    public void testValueOfAdjective() {
        System.out.println("valueOf Adjective");
        String name = "Adjective";
        PartsOfSpeech expResult = PartsOfSpeech.Adjective;
        PartsOfSpeech result = PartsOfSpeech.valueOf(name);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of valueOf method, of class PartsOfSpeech.
     */
    @Test
    public void testValueOfAdverb() {
        System.out.println("valueOf Adverb");
        String name = "Adverb";
        PartsOfSpeech expResult = PartsOfSpeech.Adverb;
        PartsOfSpeech result = PartsOfSpeech.valueOf(name);
        assertEquals(expResult, result);
    }
    
}
