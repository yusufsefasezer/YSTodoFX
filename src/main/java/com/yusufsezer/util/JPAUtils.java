package com.yusufsezer.util;

import com.yusufsezer.service.CategoryService;
import com.yusufsezer.service.TaskService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JPAUtils {

    private static final String PERSISTENCE_UNIT_NAME = "YSTodoFXPU";
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private static CategoryService categoryService;
    private static TaskService taskService;

    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (Throwable ex) {
            Logger.getLogger(JPAUtils.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

    public static CategoryService getCategoryService() {
        if (categoryService == null) {
            categoryService = new CategoryService(getEntityManager());
        }
        return categoryService;
    }

    public static TaskService getTaskService() {
        if (taskService == null) {
            taskService = new TaskService(getEntityManager());
        }
        return taskService;
    }

}
