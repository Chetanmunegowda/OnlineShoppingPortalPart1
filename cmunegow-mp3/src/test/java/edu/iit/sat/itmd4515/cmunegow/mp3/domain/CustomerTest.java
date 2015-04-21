/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.cmunegow.mp3.domain;

import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * CRUD Unit tests for Customer Entity
 * @author Chetan Munegowda
 */
public class CustomerTest extends AbstractJPATest{
    
    public CustomerTest() {
    }
    
      // ======================================
    // =              Unit tests            =
    // ======================================
    @Test
    public  void testCreateCustomer() {
        
        
        Users userObj = new Users("Chetan", "password");
        //String Street1, String street2, String city, String state, String zipcode, String country) {
        CustomerAddress custAdrObj = new CustomerAddress("3420 S Cottage Grove Avenue", null, "Chicago", "Illinois", "60616", "United States");
        Customer custObj = new Customer("Gopi", "1234", "Gopinath", "1234567891", "gopi@gmail.com", 30, "1234567809874567");
        custObj.setCustAddress(custAdrObj);
        
        tx.begin();
        em.persist(userObj);
        em.persist(custObj);  
        em.persist(custAdrObj);
        tx.commit();
        assertNotNull("UserId should not be null", userObj.getUserId());
        assertNotNull("CustomerId should not be null", custObj.getUserId());
        assertNotNull("AddressId should not be null", custAdrObj.getCustAddressId());
        
        custObj = em.find(Customer.class, custObj.getUserId());
        assertNotNull("Address should not be null", custObj.getCustAddress());
    }
    
    
    @Test
    public void testReadCustomer(){
        
        tx.begin();
        
        //Customer c = em.createNamedQuery("Customer.findByName", Customer.class).setParameter("name", "Gopinath%").getSingleResult();
        CustomerAddress custAdrObj = new CustomerAddress("3420 S Cottage Grove Avenue", null, "Chicago", "Illinois", "60616", "United States");
        Customer custObj = new Customer("Chetu", "8907", "Chetan", "123412311", "chetu@gmail.com", 30,"4567567809874567");
        custObj.setCustAddress(custAdrObj);
        
        em.persist(custObj);  
        em.persist(custAdrObj);
        tx.commit();
        
        assertNotNull("CustomerId should not be null", custObj.getUserId());
        TypedQuery<Customer> q = em.createQuery("SELECT c FROM Customer c WHERE c.custName = ?1", Customer.class);
        q.setParameter(1, "Chetan");
        Customer c = q.getSingleResult();
        assertNotNull("Cusomer Id should not be empty", c.getUserId());
        
    }
    
    @Test
    public void testUpdateUser(){
        
        tx.begin();
        CustomerAddress custAdrObj = new CustomerAddress("3420 S Cottage Grove Avenue", null, "Chicago", "Illinois", "60616", "United States");
        Customer custObj = new Customer("Dipen", "8907", "DipenManiar", "12983209830", "dipen@gmail.com", 30,"5678967809874567");
        custObj.setCustAddress(custAdrObj);
        em.persist(custObj);  
        em.persist(custAdrObj);
        tx.commit();
        
        
        tx.begin();
        Customer c = em.createNamedQuery("Customer.findByName",Customer.class).setParameter("name","DipenManiar").getSingleResult();
        assertNotNull("Customer is not empty",c.getCustName());
        String strCustName = "Balagangodar";
        String strCustEmail = "bala@gmail.com";
        c.setCustName(strCustName);
        c.setCustEmail(strCustEmail);
        tx.commit();
        
        assertTrue(strCustName.equals(c.getCustName()));
        assertEquals(strCustEmail, c.getCustEmail());
        
        
    }
    
    @Test
    public void testDeleteUser(){
        
        tx.begin();
        CustomerAddress custAdrObj = new CustomerAddress("3420 S Cottage Grove Avenue", null, "Chicago", "Illinois", "60616", "United States");
        Customer custObj = new Customer("Pranjal", "2345", "Pranjalmalav", "31245689830", "pranjal@gmail.com", 30, "1236789809874567");
        custObj.setCustAddress(custAdrObj);
        em.persist(custObj);  
        em.persist(custAdrObj);
        tx.commit();
        
        
        tx.begin();
        Customer c = em.createNamedQuery("Customer.findByName",Customer.class).setParameter("name","Pranjalmalav").getSingleResult();
        assertNotNull("Customer is not empty",c.getUserId());
        em.remove(c);
        tx.commit();
        
        Customer cust = em.find(Customer.class, c.getUserId());
        assertNull(cust);
    }

}
