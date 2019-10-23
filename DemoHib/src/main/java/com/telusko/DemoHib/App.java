package com.telusko.DemoHib;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;



public class App 
{
    public static void main( String[] args )
    {
        Alien telusko = new Alien();
        
        telusko.setAid(123);    
        telusko.setAname("Example");
        telusko.setColor("Blue");
        
        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Alien.class);
       
        
        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        
        Session session = sf.openSession();
        
        Transaction tx =  session.beginTransaction();
        
	
		  session.save(telusko);
        tx.commit();
        
        System.out.println(telusko);
    }
}
