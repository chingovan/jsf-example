package com.blogspot.chingovan.jsf_example.trainer.accessor;

import javax.persistence.EntityManager;

/**
 * Created by ChiNV on 11/26/2017.
 */
public interface PersistenceActionWithoutResult {

    public void execute(EntityManager entityManager);
}
