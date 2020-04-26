/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dz.univjijel.jsentiwordnet.dict;

import dz.univjijel.jsentiwordnet.dict.util.PartsOfSpeech;
import dz.univjijel.jsentiwordnet.dict.util.IncorrectSynsetString;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tarek Boutefara <t_boutefara@esi.dz>
 */
public class SynsetTest {
    
    Synset synset;
    Term term1;
    Term term2;
    
    public SynsetTest() {
        try {
            synset = new Synset("1", PartsOfSpeech.Noun, 0.25, 0.75, "Example");
            term1 = new Term("t1", 1);
            term2 = new Term("t2", 2);
            synset.getTerms().add(term1);
            synset.getTerms().add(term2);
        } catch (IncorrectSynsetString ex) {
            fail("Constructor Exception");
        }
    }

    /**
     * Test of getId method, of class Synset.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Synset instance = synset;
        String expResult = "1";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIntId method, of class Synset.
     */
    @Test
    public void testGetIntId() {
        System.out.println("getIntId");
        Synset instance = synset;
        int expResult = 1;
        int result = instance.getIntId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPos method, of class Synset.
     */
    @Test
    public void testGetPos() {
        System.out.println("getPos");
        Synset instance = synset;
        PartsOfSpeech expResult = PartsOfSpeech.Noun;
        PartsOfSpeech result = instance.getPos();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPosScore method, of class Synset.
     */
    @Test
    public void testGetPosScore() {
        System.out.println("getPosScore");
        Synset instance = synset;
        double expResult = 0.25;
        double result = instance.getPosScore();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getNegScore method, of class Synset.
     */
    @Test
    public void testGetNegScore() {
        System.out.println("getNegScore");
        Synset instance = synset;
        double expResult = 0.75;
        double result = instance.getNegScore();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getTerms method, of class Synset.
     */
    @Test
    public void testGetTerms() {
        System.out.println("getTerms");
        Synset instance = synset;
        List<Term> expResult = new ArrayList<>();
        Term expTerm1 = new Term("t1", 1);
        Term expTerm2 = new Term("t2", 2);
        expResult.add(expTerm1);
        expResult.add(expTerm2);
        List<Term> result = instance.getTerms();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGloss method, of class Synset.
     */
    @Test
    public void testGetGloss() {
        System.out.println("getGloss");
        Synset instance = synset;
        String expResult = "Example";
        String result = instance.getGloss();
        assertEquals(expResult, result);
    }

    /**
     * Test of synsetFactory method, of class Synset.
     */
    @Test
    public void testSynsetFactory() throws Exception {
        System.out.println("synsetFactory");
        String toParse = "n\t1\t0.25\t0.75\tt1#1 t2#2\tExample";
        Synset expResult = synset;
        Synset result = Synset.synsetFactory(toParse);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Synset.
     */
    @Test
    public void testEquals() {
        try {
            System.out.println("equals");
            Synset expResult = new Synset("1", PartsOfSpeech.Noun, 0.25, 0.75, "Example");
            Term expTerm1 = new Term("t1", 1);
            Term expTerm2 = new Term("t2", 2);
            synset.getTerms().add(expTerm2);
            synset.getTerms().add(expTerm1);;
            Synset result = synset;
            assertEquals(expResult, result);
        } catch (IncorrectSynsetString ex) {
            fail("Constructor Exception");
        }
    }
    
}
