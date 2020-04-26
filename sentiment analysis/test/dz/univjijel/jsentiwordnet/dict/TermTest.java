/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dz.univjijel.jsentiwordnet.dict;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tarek Boutefara <t_boutefara@esi.dz>
 */
public class TermTest {
    
    Term term;
    
    public TermTest() {
        term = new Term("t1", 1);
    }

    /**
     * Test of getValue method, of class Term.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Term instance = term;
        String expResult = "t1";
        String result = instance.getValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumber method, of class Term.
     */
    @Test
    public void testGetNumber() {
        System.out.println("getNumber");
        Term instance = term;
        int expResult = 1;
        int result = instance.getNumber();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getNumber method, of class Term.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Term result = term;
        Term expResult = new Term("t1", 1);
        assertEquals(expResult, result);
    }
    
    
}
