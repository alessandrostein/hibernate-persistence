/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.persistence.dao;

import hibernate.persistence.entities.Role;
import hibernate.persistence.entities.User;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author alessandro.stein
 */
public class UserDAO extends AbstractDAO implements IUserDAO {

    @Override
    protected String getNamedQueryToFindAll() {
        return "user.find.all";
    }

    @Override
    protected String getNamedQueryToFindById() {
        return "user.id.equals";
    }

    @Override
    protected String getNamedQueryToFindByName() {
        return "user.name.equals";
    }

    @Override
    protected String getNamedQueryToCountAll() {
        return "user.count.all";
    }

    @Override
    protected String getNamedQueryToRemoveAll() {
        return "user.remove.all";
    }

    @Override
    protected String getNamedQueryToFindByRange() {
        return "user.find.range";
    }

    protected String getNameQueryToFindUser() {
        return "user.find.role";
    }

    protected String getNameQueryToHasRole() {
        return "user.has.role";
    }
    
    protected String getNameQueryToHasRole() {
        return "user.remove.role";
    }

    public Object getNewInstance() {
        return new User();
    }

    // Implementação dos métodos do IUserDAO
    @Override
    public void addRole(User user, Role role) {
        try {
            if (hasRole(user, role) == true) {
                User user2 = (User) find(String.valueOf(user.getId()));
                Set roles = user2.getRole();
                roles.add(role);
                user2.setRole((Set) roles);
                update(user2);
            }

        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void removeRole(User user, Role role) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query q = session.getNamedQuery(getNamedQueryToRemoveRole());
            q.setInteger("roleid", role.getId());
            q.setInteger("userid", user.getId());
            q.executeUpdate();
        } catch (HibernateException e) {
            throw new Exception(e.getCause().getMessage());
        } finally {
            releaseSession(session);
        }       
    }

    @Override
    public boolean hasRole(User user, Role role) throws Exception {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query q = session.getNamedQuery(getNameQueryToHasRole());
            q.setInteger("roleid", role.getID());
            q.setInteger("userid", user.getId());

            if (q.uniqueResult() != null) {
                return false;
            } else {
                return true;
            }
        } catch (HibernateException e) {
            throw new Exception(e.getCause().getMessage());
        } finally {
            releaseSession(session);
        }

    }

    @Override
    public ArrayList findRole(User o) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query q = session.getNamedQuery(getNamedQueryToFindRole());
            q.setInteger("id", o.getId());
            List lst = q.list();
            return (ArrayList) lst;
        } catch (HibernateException e) {
            throw new Exception(e.getCause().getMessage());
        }
    }

}
