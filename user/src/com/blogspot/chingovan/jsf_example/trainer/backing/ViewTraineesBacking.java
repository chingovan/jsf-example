package com.blogspot.chingovan.jsf_example.trainer.backing;

import com.blogspot.chingovan.jsf_example.trainer.accessor.register.UserRegistry;
import com.blogspot.chingovan.jsf_example.trainer.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 * Created by ChiNV on 11/26/2017.
 */
@ManagedBean
public class ViewTraineesBacking extends AbstractBacking {
    private User selectedUser;

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public DataModel<User> getTraineesForCurrentUser() {
        DataModel<User> users = new ListDataModel<User>(UserRegistry.getInstance().getTraineesForTrainer(getCurrentUser()));
        return users;
    }
}
