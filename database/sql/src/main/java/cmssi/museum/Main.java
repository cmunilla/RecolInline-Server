package cmssi.museum;
 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import cmssi.museum.dao.entity.Domain;

public class Main {
 
    public static void main(String[] args) {
    	
    	AnnotationConfiguration conf = new AnnotationConfiguration();        
        conf.configure();
        SessionFactory sessionFactory = conf.buildSessionFactory();
        Session session = sessionFactory.openSession();
        
        session.beginTransaction();
         
        Domain domain = new Domain();
        domain.setRefDomain(new Integer(1));
        domain.setLabel("TESTDOMAIN");
         
        session.save(domain);
        session.getTransaction().commit();
         
        session.close(); 
    }
}