package klu.Hibernate_crud_hql_;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        
        SessionFactory factory = config.buildSessionFactory();
        
        Session session = factory.openSession();
        
        Transaction tx = session.beginTransaction();
        
        //Create or insert
        product e1 = new product();
        e1.setName("Laptop");
        e1.setDescription("Gaming laptop with 16GB RAM");
        e1.setPrice(75000.50);
        e1.setQuantity(10);
        product e2 = new product();
        e2.setName("Laptop");
        e2.setDescription("laptop with 32GB RAM");
        e2.setPrice(85000.50);
        e2.setQuantity(10);
        product e3 = new product();
        e3.setName("Laptop");
        e3.setDescription("desktop");
        e3.setPrice(2500.50);
        e3.setQuantity(10);


        
        session.persist(e1); 
        session.persist(e2);
        session.persist(e3);// Hibernate 6+ compatible
        tx.commit();
        System.out.println("Record inserted Successfully!");
        
        
        //retrieve
        
      
        
        
        session.close();
    }
}