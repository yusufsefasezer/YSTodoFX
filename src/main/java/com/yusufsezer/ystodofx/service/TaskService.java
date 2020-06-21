package com.yusufsezer.ystodofx.service;

import com.yusufsezer.ystodofx.contract.AbstractService;
import com.yusufsezer.ystodofx.model.Task;
import javax.persistence.EntityManager;

public class TaskService extends AbstractService<Task> {

    public TaskService(EntityManager entityManager) {
        super(Task.class);
        setEntityManager(entityManager);
    }

}
