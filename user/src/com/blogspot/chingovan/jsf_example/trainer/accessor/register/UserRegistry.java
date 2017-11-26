package com.blogspot.chingovan.jsf_example.trainer.accessor.register;

import com.blogspot.chingovan.jsf_example.trainer.accessor.AbstractEntityAccessor;
import com.blogspot.chingovan.jsf_example.trainer.accessor.PersistenceActionWithoutResult;
import com.blogspot.chingovan.jsf_example.trainer.accessor.exception.EntityAccessorException;
import com.blogspot.chingovan.jsf_example.trainer.constant.ApplicationConstant;
import com.blogspot.chingovan.jsf_example.trainer.model.Event;
import com.blogspot.chingovan.jsf_example.trainer.model.TrainingSession;
import com.blogspot.chingovan.jsf_example.trainer.model.User;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ChiNV on 11/27/2017.
 */
@ManagedBean(eager = true)
@ApplicationScoped
public class UserRegistry extends AbstractEntityAccessor{
    private static UserRegistry ourInstance = new UserRegistry();

    public static UserRegistry getInstance() {

        UserRegistry result = null;
        Map<String, Object> appMap = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
        result = (UserRegistry) appMap.get(ApplicationConstant.USER_REGISTRY);
        assert(null != result);

        return result;
    }

    private UserRegistry() {
    }

    @PostConstruct
    public void perApplicationConstructor() {
        try {
            doInTransaction(new PersistenceActionWithoutResult() {
                public void execute(EntityManager entityManager) {
                    Query query = entityManager.createNamedQuery("user.getAll");
                    List<User> results = query.getResultList();
                    if (results.isEmpty()) {
                        populateUsers(entityManager);
                        query = entityManager.createNamedQuery("user.getAll");
                        results = query.getResultList();
                        assert(!results.isEmpty());
                    }
                }
            });
        } catch (EntityAccessorException ex) {
            Logger.getLogger(UserRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //TODO: Waiting!!
    private void populateUsers(EntityManager entityManager) {}

    public void addTrainingSessions(List<TrainingSession> list) {

    }

    public void addUser(User user) throws EntityAccessorException {
        doInTransaction(new PersistenceActionWithoutResult() {
            public void execute(EntityManager entityManager) {
                entityManager.persist(user);
            }
        });
    }

    public List<User> getTraineesForTrainer(User trainer) {

        return null;
    }

    public List<TrainingSession> getTrainingSessionsForUserAndEvent(User user, Event event) {
        return null;
    }

    public User getUserById(Long id) {

        return null;
    }

    public User getUserByUserId(Long userId) {
        return null;
    }

    List<User> getUserList() {
        return null;
    }

    public DataModel<User> getUsers() {
        return null;
    }

    public void removeTrainingSessionForUserAndEvent(User user, Event event) {
    }

    public void updateTrainingSession(TrainingSession t) {
    }

    public void updateTrainingSessionForUser(User user, TrainingSession trainingSession) {
    }

    public void updateUser(User user) throws EntityAccessorException {
    }
}
