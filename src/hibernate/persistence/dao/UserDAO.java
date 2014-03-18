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

    protected String getNameQueryToFindRole() {
        return "user.find.role";
    }

    protected String getNameQueryToHasRole() {
        return "user.has.role";
    }
    
    protected String getNameQueryToRemoveRole() {
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
    public void removeRole(User user, Role role) throws Exception {
        try {
           if (hasRole(user, role) == true) {
                User user2 = (User) find(String.valueOf(user.getId()));
                Set roles = user2.getRole();
                roles.remove(role);
                user2.setRole(roles);
                update(user2);
            }
        } catch (HibernateException e) {
            throw new Exception(e.getCause().getMessage());
        } finally {
            releaseSession(session);
        }       
    }

    @Override
    public boolean hasRole(User user, Role role) throws Exception {
        try {
           User user2 = (User) find(String.valueOf(user.getId()));
           Set roles = user2.getRole();
           return roles.contains(role);
        } catch (HibernateException e) {
            throw new Exception(e.getCause().getMessage());
        } finally {
            releaseSession(session);
        }

    }

    @Override
    public Set findRole(User o) throws Exception {
        try {
            User u = (User) find(String.valueOf(o.getId()));
            return u.getRole();
        } catch (HibernateException e) {
            throw new Exception(e.getCause().getMessage());
        }
    }

}
