package com.yusufsezer.contract;

import com.yusufsezer.util.JPAUtils;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class AbstractService<T> {

    private final Class<T> entityClass;
    private EntityManager em;

    public AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    public void create(T entity) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Throwable ex) {
            Logger
                    .getLogger(JPAUtils.class.getName())
                    .log(Level.SEVERE, null, ex);
            em.getTransaction().rollback();
        }
    }

    public void edit(T entity) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            em.merge(entity);
            em.getTransaction().commit();
        } catch (Throwable ex) {
            Logger
                    .getLogger(JPAUtils.class.getName())
                    .log(Level.SEVERE, null, ex);
            em.getTransaction().rollback();
        }
    }

    public void remove(T entity) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            em.remove(em.merge(entity));
            em.getTransaction().commit();
        } catch (Throwable ex) {
            Logger
                    .getLogger(JPAUtils.class.getName())
                    .log(Level.SEVERE, null, ex);
            em.getTransaction().rollback();
        }
    }

    public T find(Object id) {
        return em.find(entityClass, id);
    }

    public List<T> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<T> rt = cq.from(entityClass);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
