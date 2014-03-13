/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernate.persistence.dao;

import hibernate.persistence.entities.Role;
import java.util.List;

/**
 *
 * @author alessandrostein
 */
public interface IRoleDAO extends IDAO{
    
    List findUser(Role o) throws Exception;
    
}
