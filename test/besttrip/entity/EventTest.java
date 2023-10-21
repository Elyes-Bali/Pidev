/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besttrip.entity;

import java.sql.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dhouha
 */
public class EventTest {
    
    public EventTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTitre method, of class Event.
     */
    @Test
    public void testGetTitre() {
        System.out.println("getTitre");
        Event instance = null;
        String expResult = "";
        String result = instance.getTitre();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTitre method, of class Event.
     */
    @Test
    public void testSetTitre() {
        System.out.println("setTitre");
        String titre = "";
        Event instance = null;
        instance.setTitre(titre);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescription method, of class Event.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Event instance = null;
        String expResult = "";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescription method, of class Event.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "";
        Event instance = null;
        instance.setDescription(description);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateDebut method, of class Event.
     */
    @Test
    public void testGetDateDebut() {
        System.out.println("getDateDebut");
        Event instance = null;
        Date expResult = null;
        Date result = instance.getDateDebut();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDateDebut method, of class Event.
     */
    @Test
    public void testSetDateDebut() {
        System.out.println("setDateDebut");
        Date dateDebut = null;
        Event instance = null;
        instance.setDateDebut(dateDebut);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateFin method, of class Event.
     */
    @Test
    public void testGetDateFin() {
        System.out.println("getDateFin");
        Event instance = null;
        Date expResult = null;
        Date result = instance.getDateFin();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDateFin method, of class Event.
     */
    @Test
    public void testSetDateFin() {
        System.out.println("setDateFin");
        Date dateFin = null;
        Event instance = null;
        instance.setDateFin(dateFin);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCategorie method, of class Event.
     */
    @Test
    public void testGetCategorie() {
        System.out.println("getCategorie");
        Event instance = null;
        String expResult = "";
     //   String result = instance.getCategorie();
      //  assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCategorie method, of class Event.
     */
    @Test
    public void testSetCategorie() {
        System.out.println("setCategorie");
        String categorie = "";
        Event instance = null;
       // instance.setCategorie(categorie);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLieu method, of class Event.
     */
    @Test
    public void testGetLieu() {
        System.out.println("getLieu");
        Event instance = null;
        String expResult = "";
        String result = instance.getLieu();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLieu method, of class Event.
     */
    @Test
    public void testSetLieu() {
        System.out.println("setLieu");
        String lieu = "";
        Event instance = null;
        instance.setLieu(lieu);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getImage method, of class Event.
     */
    @Test
    public void testGetImage() {
        System.out.println("getImage");
        Event instance = null;
        String expResult = "";
        String result = instance.getImage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setImage method, of class Event.
     */
    @Test
    public void testSetImage() {
        System.out.println("setImage");
        String image = "";
        Event instance = null;
        instance.setImage(image);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getParticipants method, of class Event.
     */
    @Test
    public void testGetParticipants() {
        System.out.println("getParticipants");
        Event instance = null;
        List<Participants> expResult = null;
        List<Participants> result = instance.getParticipants();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setParticipants method, of class Event.
     */
    @Test
    public void testSetParticipants() {
        System.out.println("setParticipants");
        List<Participants> participants = null;
        Event instance = null;
        instance.setParticipants(participants);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Event.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Event instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
