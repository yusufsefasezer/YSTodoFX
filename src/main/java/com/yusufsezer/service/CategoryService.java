package com.yusufsezer.service;

import com.yusufsezer.contract.AbstractService;
import com.yusufsezer.model.Category;
import jakarta.persistence.EntityManager;

public class CategoryService extends AbstractService<Category> {

    public CategoryService(EntityManager entityManager) {
        super(Category.class);
        setEntityManager(entityManager);
    }

}
