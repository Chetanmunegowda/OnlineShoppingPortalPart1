package edu.iit.sat.itmd4515.cmunegow.mp3.domain;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Test the payment for the CRUD operations
 * @author Chetan Munegowda
 */
public class PaymentTest extends AbstractJPATest{
    
    public PaymentTest() {
    }
    
    @Test
    public void testCreatePayment(){
        
        Payment p1 = new Payment("Credit");
        Payment p2 = new Payment("Debit");
        
        tx.begin();
        em.persist(p1);
        em.persist(p2);
        tx.commit();
        
        assertNotNull(p1.getPaymentId());
        assertNotNull(p2.getPaymentId());
        assertEquals(p1.getpMode(), "Credit");
        assertEquals(p2.getpMode(), "Debit");
    }
    
    
    @Test
    public void testReadPayment(){
        
        List<Payment> payment = em.createNamedQuery("Payment.findAll",Payment.class).getResultList();
        assertTrue(payment.size() > 1);
        assertFalse(payment.isEmpty());
        
        for(Payment p : payment){
            System.out.println(p.getpMode());
        }
        
    }
   
    
    @Test
    public void testUpdatePayment(){
        
        List<Payment> payment = em.createNamedQuery("Payment.findAll",Payment.class).getResultList();
        assertFalse(payment.isEmpty());
        
        tx.begin();
        for(Payment p : payment){
            if(p.getpMode().equalsIgnoreCase("CREDIT")){
                p.setpMode("Cash");
            }
        }
        tx.commit();
        Payment pObj = em.createNamedQuery("Payment.findByMode", Payment.class).setParameter("pMode", "Cash").getSingleResult();
        assertNotNull(pObj.getPaymentId());
    }
    
    @Test
    public void testRemovePayment(){
        
        
        tx.begin();
        Payment p1 = new Payment("Credit");
        em.persist(p1);
        tx.commit();
        
        assertNotNull(p1.getPaymentId());
        
        tx.begin();
        Payment pObj = em.createNamedQuery("Payment.findByMode", Payment.class).setParameter("pMode", "Credit").getSingleResult();
        em.remove(pObj);
        tx.commit();
        
        Payment pymtObj = em.find(Payment.class, p1.getPaymentId());
        assertNull(pymtObj);           
    }
}
