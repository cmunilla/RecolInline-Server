package cmssi.museum.dao.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * A PersistenceService allows to interact with the persistent framework
 * to access to managed entities 
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
abstract class PersistenceService
{	
    private static final SessionFactory SESSION_FACTORY;
     
    static {
        AnnotationConfiguration conf = new AnnotationConfiguration();        
        conf.configure();
        try {
        	SESSION_FACTORY = conf.buildSessionFactory();            
        } catch (Exception e){
            throw new ExceptionInInitializerError(e);
        }       
    }
    
	protected Session getSession() {
		if(SESSION_FACTORY == null){
    		throw new NullPointerException("Null SessionFactory");
    	}
        return SESSION_FACTORY.openSession();
	}	
}
