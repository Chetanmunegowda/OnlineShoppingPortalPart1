package edu.iit.sat.itmd4515.cmunegow.mp3.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;

/**
 * CRUD Unit Test for Orders Entity
 * @author Chetan Munegowda
 */
public class OrdersTest extends AbstractJPATest{
    
    public OrdersTest() {
    }
    
    @Test
    public void testCreateOrders(){
        
        Date date = new GregorianCalendar(2015, 3, 29).getTime();
        Orders order = new Orders("Pending", 1000, date);
             
        Category  cat1 = new Category("Toys");
        Items item1 = new Items("Lego Builder",10,300,Boolean.TRUE);
        Items item2 = new Items("Vidoe Game",10,300,Boolean.TRUE);
        
        List<Items> itemList = new ArrayList<>();
        itemList.add(item1);
        cat1.setItems(itemList);
        
        Payment pymt = new Payment("Credit");
        order.setPymt(pymt);
        
        
        tx.begin();
        em.persist(cat1);
        em.persist(item1);
        em.persist(item2);
        tx.commit();
        
        assertNotNull(item1.getItemId());
        
        List<OrderItems> ordrItemList = new ArrayList<>();
        
        OrderItems ordItm1 = new OrderItems(item1.getItemId(),10,item1.getItemCost());
        OrderItems ordItm2 = new OrderItems(item2.getItemId(),10, item2.getItemCost());
        
        ordrItemList.add(ordItm1);
        ordrItemList.add(ordItm2);
        
        CustomerAddress custAdrObj = new CustomerAddress("3420 S Cottage Grove Avenue", null, "Chicago", "Illinois", "60616", "United States");
        Customer custObj = new Customer("varunsri", "1234", "Varun", "1234567891", "varun@gmail.com", 30, "12382167809874567");
        custObj.setCustAddress(custAdrObj);
        order.setOrdItems(ordrItemList);
        
        List<Orders> orderList = new ArrayList<>();
        orderList.add(order);
        custObj.setOrder(orderList);
        
        tx.begin();
        em.persist(custObj);
        em.persist(order);
        tx.commit();
        
        assertNotNull(order.getOrderId());
        
        
    }
    
    
    @Test
    public void testReadOrders(){
        
        Category  cat1 = new Category("Mobile");
        Items item1 = new Items("Samsung S1",150,50,Boolean.TRUE);
        Items item2 = new Items("Motorla M1",200,50,Boolean.TRUE);
        
        List<Items> itemList = new ArrayList<>();
        itemList.add(item1);
        cat1.setItems(itemList);
         
        Date date = new GregorianCalendar(2015, 3, 29).getTime();
        Orders order = new Orders("Pending", 350, date);     
        
        Payment pymt = new Payment("Debit");
        order.setPymt(pymt);
        
        tx.begin();
        em.persist(cat1);
        em.persist(item1);
        em.persist(item2);
        tx.commit();
        
        assertNotNull(item1.getItemId());
        
        List<OrderItems> ordrItemList = new ArrayList<>();
        
        OrderItems ordItm1 = new OrderItems(item1.getItemId(),10,item1.getItemCost());
        OrderItems ordItm2 = new OrderItems(item2.getItemId(),10, item2.getItemCost());
        ordrItemList.add(ordItm1);
        ordrItemList.add(ordItm2);
        
         
        CustomerAddress custAdrObj = new CustomerAddress("5670 S Cottage Grove Avenue", null, "Chicago", "Illinois", "60616", "United States");
        Customer custObj = new Customer("pratiksampat", "1234", "Pratik", "6785467891", "pratik@gmail.com", 30, "12382789809874567");
        custObj.setCustAddress(custAdrObj);
        order.setOrdItems(ordrItemList);
        
        List<Orders> orderLst = new ArrayList<>();
        orderLst.add(order);
        custObj.setOrder(orderLst);
       
        
        order.setOrdItems(ordrItemList);
        
        tx.begin();
        em.persist(custObj);
        em.persist(order);
        tx.commit();
        
        assertNotNull(order.getOrderId());
        
        List<Orders> orderList = em.createNamedQuery("Orders.findAll",Orders.class).getResultList();
        assertTrue(!orderList.isEmpty());
        
        
        for(Orders ordObj: orderList){
            
            System.out.println(ordObj);
            
            for(OrderItems ordItem :ordObj.getOrdItems()){
                System.out.println(ordItem);
            }
            
            
            
        } 
    }
   
    
    @Test
    public void testUpdateOrders(){
        
        Category  cat1 = new Category("Perfumes");
        Items item1 = new Items("Denim",100,50,Boolean.TRUE);
        Items item2 = new Items("Burberry",200,50,Boolean.TRUE);
        
        List<Items> itemList = new ArrayList<>();
        itemList.add(item1);
        cat1.setItems(itemList);
         
        Date date = new GregorianCalendar(2015, 3, 29).getTime();
        Orders order = new Orders("Pending", 300, date);     
        
        Payment pymt = new Payment("Cash");
        order.setPymt(pymt);
        
        tx.begin();
        em.persist(cat1);
        em.persist(item1);
        em.persist(item2);
        tx.commit();
        
        assertNotNull(item1.getItemId());
        
        List<OrderItems> ordrItemList = new ArrayList<>();
        
        OrderItems ordItm1 = new OrderItems(item1.getItemId(),10,item1.getItemCost());
        OrderItems ordItm2 = new OrderItems(item2.getItemId(),10, item2.getItemCost());
        ordrItemList.add(ordItm1);
        ordrItemList.add(ordItm2);
        
        order.setOrdItems(ordrItemList);
        
        CustomerAddress custAdrObj = new CustomerAddress("2470 S Cottage Grove Avenue", null, "Chicago", "Illinois", "60616", "United States");
        Customer custObj = new Customer("prasanna", "3426", "prasannahegde", "9085467891", "pras@gmail.com", 30, "98382789809874598");
        custObj.setCustAddress(custAdrObj);
        order.setOrdItems(ordrItemList);
        
        List<Orders> orderLst = new ArrayList<>();
        orderLst.add(order);
        custObj.setOrder(orderLst);
        order.setOrdItems(ordrItemList);
          
        tx.begin();
        em.persist(custObj);
        em.persist(order);
        tx.commit();
        assertNotNull(order.getOrderId());
        
        Orders orderObj = em.find(Orders.class, order.getOrderId());
        tx.begin();
        Date deliverDate = new GregorianCalendar(2015, 03, 31).getTime();
        orderObj.setOrdDeliverDate(deliverDate);
        orderObj.setOrdStatus("On Delivery");
        tx.commit();
        
        assertNotNull(orderObj.getOrdDeliverDate());
        assertEquals(orderObj.getOrdStatus(),"On Delivery");
           
        
    }
    
    @Test
    public void testRemoveOrders(){
        
        Category  cat1 = new Category("Vegetables");
        Items item1 = new Items("Onion",1,50,Boolean.TRUE);
        Items item2 = new Items("Potato",2,50,Boolean.TRUE);
        
        List<Items> itemList = new ArrayList<>();
        itemList.add(item1);
        cat1.setItems(itemList);
         
        Date date = new GregorianCalendar(2015, 3, 29).getTime();
        Orders order = new Orders("Pending", 10, date);     
        
        Payment pymt = new Payment("Gift Card");
        order.setPymt(pymt);
        
        tx.begin();
        em.persist(cat1);
        em.persist(item1);
        em.persist(item2);
        tx.commit();
        
        assertNotNull(item1.getItemId());
        
        List<OrderItems> ordrItemList = new ArrayList<>();
        
        OrderItems ordItm1 = new OrderItems(item1.getItemId(),4,item1.getItemCost());
        OrderItems ordItm2 = new OrderItems(item2.getItemId(),6, item2.getItemCost());
        ordrItemList.add(ordItm1);
        ordrItemList.add(ordItm2);
        
        order.setOrdItems(ordrItemList);
        
        CustomerAddress custAdrObj = new CustomerAddress("9087 S Cottage Grove Avenue", null, "Chicago", "Illinois", "60616", "United States");
        Customer custObj = new Customer("fran", "3426", "francisco", "8095467891", "fran@gmail.com", 30, "78962789809874598");
        custObj.setCustAddress(custAdrObj);
        order.setOrdItems(ordrItemList);
        
        List<Orders> orderLst = new ArrayList<>();
        orderLst.add(order);
        custObj.setOrder(orderLst);
        order.setOrdItems(ordrItemList);
       
        
        tx.begin();
        em.persist(custObj);
        em.persist(order);
        tx.commit();
        assertNotNull(order.getOrderId());
        
        Orders orderObj = em.find(Orders.class, order.getOrderId());
        tx.begin();
        em.remove(orderObj);
        tx.commit();
        
        Orders orderObj1 = em.find(Orders.class, order.getOrderId());
        assertNull(orderObj1);
        
    }   
}
