/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.persistence.dao;

import hibernate.persistence.entities.Role;
import java.util.ArrayList;

/**
 *
 * @author alessandro.stein
 */
public class RoleDAO extends AbstractDAO implements IRoleDAO{
    
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
    
    public Object getNewInstance() {
        return new Role();
    } 

    
    // Implementação da busca do usuario.
    @Override
    public ArrayList findUser(Role o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
