package com.blogspot.chingovan.jsf_example.trainer.model;

import com.blogspot.chingovan.jsf_example.trainer.accessor.register.UserRegistry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * Created by ChiNV on 11/26/2017.
 */
@Entity
@Table(name = "Users")
@NamedQuery( name = "user.getAll", query = "SELECT user FROM Users user")
@ManagedBean
@RequestScoped
public class User extends AbstractEntity {

    private Date dateOfBirth;
    private String email;
    private String firstName;
    private String lastName;
    private User personalTrainer;
    private Long personalTrainerId;
    private int serviceLevel;
    private boolean sex;
    private List<Long> subscribedEventIds;
    private List<TrainingSession> trainingSessions;
    private Long userId;
    private String password;

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User getPersonalTrainer() {
        return personalTrainer;
    }

    public void setPersonalTrainer(User personalTrainer) {
        this.personalTrainer = personalTrainer;
    }

    public Long getPersonalTrainerId() {
        return personalTrainerId;
    }

    public void setPersonalTrainerId(Long personalTrainerId) {
        this.personalTrainerId = personalTrainerId;
    }

    public int getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(int serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public List<Long> getSubscribedEventIds() {
        return subscribedEventIds;
    }

    public void setSubscribedEventIds(List<Long> subscribedEventIds) {
        this.subscribedEventIds = subscribedEventIds;
    }

    public List<TrainingSession> getTrainingSessions() {
        return trainingSessions;
    }

    public void setTrainingSessions(List<TrainingSession> trainingSessions) {
        this.trainingSessions = trainingSessions;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isTrainer() {
        return true;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Event> getMyEvents() {
        return  null;
    }

    public DataModel<TrainingSession> getTrainingSessionsForEvent(Event event) {

        DataModel<TrainingSession> sessionsForEvent = null;
        List<TrainingSession> sessionList = UserRegistry.getInstance().getTrainingSessionsForUserAndEvent(this, event);
        sessionsForEvent = new ListDataModel<>(sessionList);

        return sessionsForEvent;
    }
}
