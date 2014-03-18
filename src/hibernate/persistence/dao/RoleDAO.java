/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.persistence.dao;

import hibernate.persistence.entities.Role;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;

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
    public Set findUser(Role o) throws Exception {
        try {
            Role role = (Role) find(String.valueOf(o.getID()));
            return role.getUser();
        } catch (HibernateException e) {
            throw new Exception(e.getCause().getMessage());
        } finally {
            releaseSession(session);
        }

    }

   
}
