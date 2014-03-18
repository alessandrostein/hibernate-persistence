/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernate.persistence.dao;

import hibernate.persistence.entities.Role;
import hibernate.persistence.entities.User;
import java.util.Set;

/**
 *
 * @author alessandrostein
 */
public interface IUserDAO extends IDAO{
    
    void addRole(User user, Role role) throws Exception;
    
    void removeRole(User user, Role role) throws Exception;
    
    boolean hasRole(User user, Role role) throws Exception;
    
    Set findRole(User o) throws Exception;
    
}
