/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.persistence.dao;

import hibernate.persistence.entities.Role;
import hibernate.persistence.entities.User;
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

    public Object getNewInstance() {
        return new User();
    }

    // Implementação dos métodos do IUserDAO
    @Override
    public void addRole(User user, Role role) {
        try {
            if (hasRole(user, role) == false) {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                User usuario = (User) session.load(User.class, user.getId());
                Role regra = (Role) session.load(Role.class, role.getID());
                usuario.getRole().add(regra);
                session.update(usuario);
                session.getTransaction().commit();
            }

        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void removeRole(User user, Role role) throws Exception {
        try {
            if (hasRole(user, role) == false) {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                User usuario = (User) session.load(User.class, user.getId());
                Role regra = (Role) session.load(Role.class, role.getID());
                usuario.getRole().remove(regra);
                session.update(usuario);
                session.getTransaction().commit();
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
