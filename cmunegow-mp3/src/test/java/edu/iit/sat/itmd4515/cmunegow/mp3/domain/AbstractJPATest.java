package edu.iit.sat.itmd4515.cmunegow.mp3.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Class which contains Entity Life Cycle methods
 * @author Chetan Munegowda
 */
public class AbstractJPATest {
    
    
    private static EntityManagerFactory emf;
    protected EntityManager em;
    protected EntityTransaction tx;
    
   
    
    @BeforeClass
    public static void setUpClass()
    {
       emf = Persistence.createEntityManagerFactory("cmunegowPU");
       
    }
    
    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }
    
    @Before
    public void setUp() {
      em =  emf.createEntityManager();
      tx = em.getTransaction();
    }
    
    @After
    public void tearDown() {
       
    }

    
}
