package com.blogspot.chingovan.jsf_example.trainer.accessor;

import com.blogspot.chingovan.jsf_example.trainer.accessor.exception.EntityAccessorException;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ChiNV on 11/26/2017.
 */
public class AbstractEntityAccessor {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Resource
    private UserTransaction userTransaction;

    protected final <T> T doInTransaction(PersistenceAction<T> action) throws EntityAccessorException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            userTransaction.begin();

            T result = action.execute(entityManager);
            userTransaction.commit();
            return result;
        } catch (Exception e) {
            try {
                userTransaction.rollback();
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
            throw new EntityAccessorException(e);
        } finally {
            entityManager.close();
        }
    }

    protected final void doInTransaction(PersistenceActionWithoutResult action) throws EntityAccessorException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            userTransaction.begin();
            action.execute(entityManager);
            userTransaction.commit();
        } catch (Exception e) {
            try {
                userTransaction.rollback();
            } catch (Exception ex) {
                Logger.getLogger(AbstractEntityAccessor.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
            throw new EntityAccessorException(e);
        } finally {
            entityManager.close();
        }
    }
}
