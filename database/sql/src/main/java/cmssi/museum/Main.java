package cmssi.museum;
 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import cmssi.museum.dao.entity.Domain;
import cmssi.museum.dao.service.DomainService;

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
        
        DomainService ds = new DomainService();

        Domain d = new Domain();
        d.setRefDomain(new Integer(1));
        d.setLabel("TESTDOMAIN2");
        ds.add(d);
        System.out.println(d.getIdDomain());
        
        ds.remove(domain);
        ds.remove(d);
         
        session.close(); 
    }
}