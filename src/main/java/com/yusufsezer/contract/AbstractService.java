package com.yusufsezer.contract;

import com.yusufsezer.util.JPAUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractService<T> {

    private final Class<T> entityClass;
    private EntityManager entityManager;

    public AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(T entity) {
        try {
            if (!entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().begin();
            }
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        } catch (Throwable ex) {
            Logger.getLogger(JPAUtils.class.getName()).log(Level.SEVERE, null, ex);
            entityManager.getTransaction().rollback();
        }
    }

    public void edit(T entity) {
        try {
            if (!entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().begin();
            }
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        } catch (Throwable ex) {
            Logger.getLogger(JPAUtils.class.getName()).log(Level.SEVERE, null, ex);
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(T entity) {
        try {
            if (!entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().begin();
            }
            entityManager.remove(entityManager.merge(entity));
            entityManager.getTransaction().commit();
        } catch (Throwable ex) {
            Logger.getLogger(JPAUtils.class.getName()).log(Level.SEVERE, null, ex);
            entityManager.getTransaction().rollback();
        }
    }

    public T find(Object id) {
        return entityManager.find(entityClass, id);
    }

    public List<T> findAll() {
        CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = entityManager.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        Root<T> rt = cq.from(entityClass);
        cq.select(entityManager.getCriteriaBuilder().count(rt));
        Query q = entityManager.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
