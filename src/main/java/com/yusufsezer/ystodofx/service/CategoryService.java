package com.yusufsezer.ystodofx.service;

import com.yusufsezer.ystodofx.contract.AbstractService;
import com.yusufsezer.ystodofx.model.Category;
import javax.persistence.EntityManager;

public class CategoryService extends AbstractService<Category> {

    public CategoryService(EntityManager entityManager) {
        super(Category.class);
        setEntityManager(entityManager);
    }

}
