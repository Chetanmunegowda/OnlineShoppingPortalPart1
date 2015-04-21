package edu.iit.sat.itmd4515.cmunegow.mp3.domain;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;

/**
 * CRUD Unit Test cases for Supplier Entity
 * @author Chetan Munegowda
 */
public class SupplierTest extends AbstractJPATest {

    public SupplierTest() {
    }

    @Test
    public void testCreateSupplier() {

        Supplier s = new Supplier("Flipkart", "3456", "3124780877", "flipkart@org.com");
        SupplierAddress supAddr = new SupplierAddress("3440 S. Cottage Gove Avenue", null, "Chicago", "Illinois", "60613", "United States");

        Shipper ship1 = new Shipper("Flipkart Courier", "3124568789");
        Shipper ship2 = new Shipper("Kaage Courier", "3124569789");
        
        
        Items i1 = new Items("Notebook", 2, 100, Boolean.TRUE);
        Items i2 = new Items("Pen", 2, 200, Boolean.TRUE);
       

        List<Shipper> shipperList = new ArrayList<>();
        List<Items> itemList = new ArrayList<>();
        shipperList.add(ship1);
        shipperList.add(ship2);
        
        itemList.add(i1);
        itemList.add(i2);
        
        s.setShippers(shipperList);
        s.setSupAddress(supAddr);
        s.setSupItems(itemList);
        

        tx.begin();
        em.persist(supAddr);
        em.persist(ship1);
        em.persist(ship2);
        em.persist(i1);
        em.persist(i2);
        em.persist(s);
        tx.commit();

        assertNotNull(s.getUserId());
        assertNotNull(ship1.getShipId());
        assertNotNull(supAddr.getSupAddressId());
        assertNotNull(i1.getItemId());
    }

    @Test
    public void testReadSupplier() {

        Supplier s = new Supplier("Amazon", "3456", "3124780345", "amazon@org.com");
        SupplierAddress supAddr = new SupplierAddress("3678 S State Street", null, "Chicago", "Illinois", "60618", "United States");
        Shipper ship1 = new Shipper("Amazon Courier", "312456765");
        Items i1 = new Items("Sharpner", 2, 100, Boolean.TRUE);
        Items i2 = new Items("Pencil", 2, 200, Boolean.TRUE);
       

        List<Shipper> shipperList = new ArrayList<>();
        List<Items> itemList = new ArrayList<>();
        
        shipperList.add(ship1);
        s.setShippers(shipperList);
        s.setSupAddress(supAddr);
        itemList.add(i1);
        itemList.add(i2);
        s.setSupItems(itemList);
       

        tx.begin();
        em.persist(supAddr);
        em.persist(s);
        em.persist(ship1);
        em.persist(i1);
        em.persist(i2);
        tx.commit();
        assertNotNull(s.getUserId());
        assertNotNull(ship1.getShipId());
        assertNotNull(supAddr.getSupAddressId());

        List<Supplier> sup = em.createNamedQuery("Supplier.findAll", Supplier.class).getResultList();
        assertFalse(sup.isEmpty());
        for (Supplier supplr : sup) {
            System.out.println(supplr.toString());
            System.out.println(supplr.getSupAddress().toString());

            for (Shipper shipr : supplr.getShippers()) {
                System.out.println(shipr);
            }
            
            for(Items item: supplr.getSupItems()){
                System.out.println(item);
            }

        }

    }

    @Test
    public void testUpdateSupplier() {

        Supplier s = new Supplier("WallMart", "3434", "3128980877", "wallmart@org.com");
        SupplierAddress supAddr = new SupplierAddress("3440 S. Hallsted Street", null, "Chicago", "Illinois", "60618", "United States");
        s.setSupAddress(supAddr);

        tx.begin();
        em.persist(s);
        em.persist(supAddr);
        tx.commit();

        List<Supplier> sup = em.createNamedQuery("Supplier.findAll", Supplier.class).getResultList();
        assertFalse(sup.isEmpty());

        tx.begin();
        for (Supplier supplr : sup) {
            if (supplr.getSupEmail().equalsIgnoreCase("wallmart@org.com")) {
                supplr.setSupEmail("wallmarts.org.com");
            }
        }
        tx.commit();

        int count = 0;
        for (Supplier supplr : sup) {
            if (supplr.getSupEmail().equalsIgnoreCase("wallmarts.org.com")) {
                count = count + 1;
            }
        }

        assertEquals(1, count);

    }

    
    @Test
    public void testRemoveSupplier() {

        Supplier s = new Supplier("BestBuy", "3434", "6788980877", "bestbuy@org.com");
        SupplierAddress supAddr = new SupplierAddress("3440 S Adam Street", null, "Chicago", "Illinois", "60612", "United States");
        s.setSupAddress(supAddr);

        tx.begin();
        em.persist(s);
        em.persist(supAddr);
        tx.commit();

        tx.begin();
        List<Supplier> sup = em.createNamedQuery("Supplier.findAll", Supplier.class).getResultList();
        for (Supplier supplr : sup) {
            for(Items item: supplr.getSupItems()){
                em.remove(item);
            }
            
            for(Shipper ship: supplr.getShippers()){
                em.remove(ship);
            }
            
            SupplierAddress supAdd = supplr.getSupAddress();
            em.remove(supAdd);
            
            em.remove(supplr);
        }

        tx.commit();

       Supplier supl = em.find(Supplier.class, 1L);
       assertNull(supl);
       

    }

}
