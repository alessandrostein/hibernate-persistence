/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.persistence.dao;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alessandro.stein
 */
public interface IDAO {
    
    Object save(Object o) throws Exception;
    
    void update(Object o) throws Exception;
    
    void remove(Object o) throws Exception;
    
    Object  find(String id) throws Exception;
    
    List findAll() throws Exception;
    
    List list(Integer firstResult, Integer maxResults) throws Exception;
    
    Long coutAll() throws Exception;
    
    void removeAll() throws Exception;
    
    Object getNewInstance();
           
    
}
