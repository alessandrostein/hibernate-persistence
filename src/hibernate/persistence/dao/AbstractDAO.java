/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.persistence.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author alessandro.stein
 */
public abstract class AbstractDAO implements IDAO{

    protected Session session;
    
    @Override
    public Object save(Object o) throws Exception {
    
        try {
            session = obtainSession();
            session.beginTransaction();
            session.save(o);
            session.flush();
            session.getTransaction().commit();
            return o;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            throw new Exception(e.getCause().getMessage());
        } finally {
            releaseSession(session);
        }
    }
    
    @Override
    public void update(Object o) throws Exception {
    
        try {
            session = obtainSession();
            session.beginTransaction();
            session.update(o);
            session.flush();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            throw new Exception(e.getCause().getMessage());
        } finally {
            releaseSession(session);
        }
    }
    

    @Override
    public void remove(Object o) throws Exception {
        try {
            session = obtainSession();
            session.beginTransaction();
            session.delete(o);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            throw new Exception(e.getCause().getMessage());
        } finally {
            releaseSession(session);
        }
    
    }

    @Override
    public Object find(String id) throws Exception {
         try {
            session = HibernateUtil.getSessionFactory().openSession();
            //Query q = session.getNamedQuery("id.igual");
            Query q = session.getNamedQuery(getNamedQueryToFindById());
            q.setString("id", id.toString());
            Object o = q.uniqueResult();
            return o;
        } catch (HibernateException e) {
            throw new Exception(e.getCause().getMessage());
        } finally {
            releaseSession(session);
        }
    }

    @Override
    public List findAll() throws Exception {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            //Query q = session.getNamedQuery("find.all");
            Query q = session.getNamedQuery(getNamedQueryToFindAll());
            List lst = q.list();
            session.getTransaction().commit();
            return lst;
        } catch (HibernateException e) {
            throw new Exception(e.getCause().getMessage());
        } finally {
            releaseSession(session);
        }
    }
    
    public List list(Integer firstResult, Integer maxResults) throws Exception {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            //Query q = session.getNamedQuery("find.all");
            Query q = session.getNamedQuery(getNamedQueryToFindByRange());
            q.setFirstResult(firstResult);
            q.setMaxResults(maxResults);
            List lst = q.list();
            return lst;
        } catch (HibernateException e) {
            throw new Exception(e.getCause().getMessage());
        } finally {
            releaseSession(session);
        }
    }

    @Override
    public Long coutAll() throws Exception {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            //Query q = session.getNamedQuery("count.all");
            Query q = session.getNamedQuery(getNamedQueryToCountAll());
            Long count = (Long) q.uniqueResult();
            session.getTransaction().commit();
            return count;
        } catch (HibernateException e) {
            throw new Exception(e.getCause().getMessage());
        } finally {
            releaseSession(session);
        }
    }

    @Override
    public void removeAll() throws Exception {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            //Query q = session.getNamedQuery("count.all");
            Query q = session.getNamedQuery(getNamedQueryToRemoveAll());
            q.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new Exception(e.getCause().getMessage());
        } finally {
            releaseSession(session);
        }    
    }
    
    protected Session obtainSession() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }

    protected void releaseSession(Session session) {
        // Do nothing...
    }
    
    /*
     * Abstract Members...
     */
    protected abstract String getNamedQueryToFindAll();

    protected abstract String getNamedQueryToFindById();

    protected abstract String getNamedQueryToFindByName();

    protected abstract String getNamedQueryToCountAll();

    protected abstract String getNamedQueryToRemoveAll();

    protected abstract String getNamedQueryToFindByRange();
    
}
