/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.cmunegow.mp3.domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 * CRUD Unit test cases for Shipper entity
 * @author Chetan Munegowda
 */
public class ShipperTest extends AbstractJPATest{
    
    public ShipperTest() {
    }
    
   
   @Test
    public void testCreateShipper(){
        
        Shipper ship1 = new Shipper("FEDEX", "3124568765");
        
        Supplier s1 = new Supplier("Target", "3426", "3124780377", "target@org.com");
        SupplierAddress supAddr1 = new SupplierAddress("3490 S. Cottage Gove Avenue", null, "Chicago", "Illinois", "60613", "United States");
        s1.setSupAddress(supAddr1);

        Supplier s2 = new Supplier("Jewel", "3726", "3124710377", "jewel@org.com");
        SupplierAddress supAddr2 = new SupplierAddress("3550 S. Cottage Gove Avenue", null, "Chicago", "Illinois", "60613", "United States");
        s2.setSupAddress(supAddr2);

        List<Supplier> supList = new ArrayList<>();
        supList.add(s1);
        supList.add(s2);
        
        ship1.setSuppliers(supList);
        
        tx.begin();
        em.persist(ship1);
        tx.commit();
        
        assertNotNull("Shipper Name Should not be empty", ship1.getShipName());
        assertEquals("FEDEX",ship1.getShipName());
        assertNotNull(s1.getUserId());
        assertEquals(s2.getUserName(),"Jewel");
        
    }
    
    
    @Test
    public void testReadShipper(){
        
        Shipper ship = new Shipper("UPS", "7894568765");
        tx.begin();
        em.persist(ship);
        tx.commit();
        
        List<Shipper> shipper = em.createNamedQuery("Shipper.findAll",Shipper.class).getResultList();
        assertNotNull(shipper.isEmpty());
        
        tx.begin();
        for(Shipper s:shipper){
            System.out.println(s.getShipName());
        }  
        tx.commit();
    }
   
    
    @Test
    public void testUpdateShipper(){
        
        Shipper ship1 = new Shipper("DHL", "6784568765");
        
        tx.begin();
        em.persist(ship1);
        tx.commit();
        
        Shipper shipper = em.createNamedQuery("Shipper.findByName",Shipper.class).setParameter("sName", "DHL").getSingleResult();
        assertNotNull(shipper.getShipId());
        
        tx.begin();
        ship1.setShipName("BHL");
        tx.commit();
        assertEquals("BHL", ship1.getShipName());
        
        
        
    }
    
    @Test
    public void testRemoveShipper(){
        
        
        Shipper ship = new Shipper("Amazon", "7634568765");
        
        tx.begin();
        em.persist(ship);
        tx.commit();
        
        Shipper shipper = em.createNamedQuery("Shipper.findByName",Shipper.class).setParameter("sName", "Amazon").getSingleResult();
        assertNotNull(shipper.getShipId());
        
        tx.begin();
        em.remove(shipper);
        tx.commit();
        
        Shipper s1 = em.find(Shipper.class, shipper.getShipId());
        assertNull(s1);
        
    }
     
}
