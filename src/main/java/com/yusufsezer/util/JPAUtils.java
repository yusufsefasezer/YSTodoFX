package com.yusufsezer.util;

import com.yusufsezer.service.CategoryService;
import com.yusufsezer.service.TaskService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {

    private static final String PERSISTENCE_UNIT_NAME = "YSTodoFXPU";
    private static EntityManagerFactory emf;
    private static EntityManager em;

    static {
        try {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (Throwable ex) {
            Logger.getLogger(JPAUtils.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManager getEntityManager() {
        if (em == null) {
            em = emf.createEntityManager();
        }
        return em;
    }

    public static CategoryService getCategoryService() {
        return new CategoryService(getEntityManager());
    }

    public static TaskService getTaskService() {
        return new TaskService(getEntityManager());
    }
}
