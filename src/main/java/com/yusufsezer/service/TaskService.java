package com.yusufsezer.service;

import com.yusufsezer.contract.AbstractService;
import com.yusufsezer.model.Task;
import javax.persistence.EntityManager;

public class TaskService extends AbstractService<Task> {

    public TaskService(EntityManager entityManager) {
        super(Task.class);
        setEntityManager(entityManager);
    }

}
