package sait.dataaccess;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DBUtil {
    private final static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("FinalProjectJPAPU");
    
    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
    
    public static void closeEMFactory() {
        emf.close();
    }
}
