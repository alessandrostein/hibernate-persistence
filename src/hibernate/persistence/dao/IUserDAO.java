/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernate.persistence.dao;

import hibernate.persistence.entities.Role;
import hibernate.persistence.entities.User;
import java.util.ArrayList;

/**
 *
 * @author alessandrostein
 */
public interface IUserDAO extends IDAO{
    
    void addRole(User user, Role role);
    
    void removeRole(User user, Role role);
    
    boolean hasRole(User user, Role role);
    
    ArrayList findRole(User o);
    
}
