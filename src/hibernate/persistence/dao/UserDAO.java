/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.persistence.dao;

import hibernate.persistence.entities.Role;
import hibernate.persistence.entities.User;
import java.util.ArrayList;

/**
 *
 * @author alessandro.stein
 */
public class UserDAO extends AbstractDAO implements IUserDAO{
    
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
    
    public Object getNewInstance() {
        return new User();
    } 
    
    // Implementação dos métodos do IUserDAO
    @Override
    public void addRole(User user, Role role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeRole(User user, Role role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasRole(User user, Role role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList findRole(User o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
