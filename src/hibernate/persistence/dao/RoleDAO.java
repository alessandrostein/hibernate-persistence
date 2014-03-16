/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.persistence.dao;

import hibernate.persistence.entities.Role;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author alessandro.stein
 */
public class RoleDAO extends AbstractDAO implements IRoleDAO {

    @Override
    protected String getNamedQueryToFindAll() {
        return "role.find.all";
    }

    @Override
    protected String getNamedQueryToFindById() {
        return "role.id.equals";
    }

    @Override
    protected String getNamedQueryToFindByName() {
        return "role.name.equals";
    }

    @Override
    protected String getNamedQueryToCountAll() {
        return "role.count.all";
    }

    @Override
    protected String getNamedQueryToRemoveAll() {
        return "role.remove.all";
    }

    @Override
    protected String getNamedQueryToFindByRange() {
        return "role.find.range";
    }
    
    @Override
    public Object getNewInstance() {
        return new Role();
    }
    

    // Implementação da busca do usuario.
    @Override
    public List findUser(Role o) throws Exception {
        try {
            UserDAO user = new UserDAO();
            
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.getNamedQuery(user.getNameQueryToFindUser());
            q.setInteger("id", o.getID());
            List lst = q.list();
            session.getTransaction().commit();
            return lst;
        } catch (HibernateException e) {
            throw new Exception(e.getCause().getMessage());
        } finally {
            releaseSession(session);
        }

    }

   
}
