package edu.iit.sat.itmd4515.cmunegow.mp3.domain;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 * Unit Test to test CRUD operations on Category Table
 *
 * @author Chetan Munegowda
 */
public class CategoryTest extends AbstractJPATest{

    public CategoryTest() {
    }

    @Test
    public void testCreateCategory() {
        
        Category  cat1 = new Category("Stationary");
        Items item1 = new Items("Notebook", 2, 100, Boolean.TRUE);
        Items item2 = new Items("Pen", 2, 200, Boolean.TRUE);
       
        
        List<Items> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        
        cat1.setItems(itemList);
        
        
        tx.begin();
        em.persist(cat1);
        tx.commit();
        
        assertNotNull(item1.getItemId());
        assertNotNull(item2.getItemId());
        assertNotNull(cat1.getCatId());
  
    }

    @Test
    public void testReadCategory() {
        
        
        Category  cat1 = new Category("Study Materials");
        Items item1 = new Items("Eraser",1,300,Boolean.TRUE);
        Items item2 = new Items("Pencil",1,500,Boolean.TRUE);
        
        List<Items> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        
        cat1.setItems(itemList);
        
        
        tx.begin();
        em.persist(cat1);
        tx.commit();
        
        assertNotNull(item1.getItemId());
        assertNotNull(item2.getItemId());
       
        List<Items> items = em.createNamedQuery("Items.findAll",Items.class).getResultList();
        assertTrue(items.size()>1);
        for(Items item: items){
            System.out.println(item.getItemName());
        }
    }

    @Test
    public void testUpdateCategory() {
        
        Category  cat1 = new Category("Kitchen Appliances");
        Items item1 = new Items("Lighter",10,100,Boolean.TRUE);
        Items item2 = new Items("Stove",50,100,Boolean.TRUE);
        
        List<Items> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        
        cat1.setItems(itemList);
        
        
        tx.begin();
        em.persist(cat1);
        tx.commit();
        
        assertNotNull(item1.getItemId());
        assertNotNull(item2.getItemId());
        
        List<Category> category = em.createNamedQuery("Category.findAll",Category.class).getResultList();
        assertTrue(category.size()!=0);
        
        
        for(Category cat : category){
            System.out.println(cat.getCatName());
            
            for(Items item: cat.getItems()){
                System.out.println(item.getItemName());
            }
        }
        
         
        Category cat = em.createNamedQuery("Category.findByName",Category.class).setParameter("catName", "Kitchen Appliances").getSingleResult();
        assertNotNull(cat.getCatId());
        
        tx.begin();
        cat.setCatName("Kitchen Tools");
        tx.commit();
        
        
        assertEquals(cat.getCatName(),"Kitchen Tools");
    }

    @Test
    public void testRemoveCategory() {

        Category  cat1 = new Category("Clothing");
        Items item1 = new Items("Shirt",3,400,Boolean.TRUE);
        Items item2 = new Items("Pant",3,400,Boolean.TRUE);
        
        List<Items> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        cat1.setItems(itemList);
        
        tx.begin();
        em.persist(cat1);
        tx.commit();
        
        assertNotNull(cat1.getCatId());
        
        
        List<Category> catList = em.createNamedQuery("Category.findAll",Category.class).getResultList();
        tx.begin();
        for(Category cat: catList){
            for(Items item: cat.getItems()){
                em.remove(item);
            }
            em.remove(cat);
            
        }
        tx.commit();
        
        Category catObj = em.find(Category.class, 1L);
        assertNull(catObj);
        
    }
}
