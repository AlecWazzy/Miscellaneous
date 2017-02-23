package sait.dataaccess;

import javax.persistence.EntityManager;
import sait.domainmodel.Role;

/**
 * @author 645111
 */
public class RoleRepository {
    public Role getRole (int roleId)  {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Role role = em.find(Role.class, roleId);
            return role;
        } finally {
            em.close();    
        }
    }
    
    public int update(Role role) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(role);
            em.getTransaction().commit();
            return 1;
        } finally {
            em.close();
        }
    }
}
