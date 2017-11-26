package com.blogspot.chingovan.jsf_example.trainer.backing;

import com.blogspot.chingovan.jsf_example.trainer.accessor.register.UserRegistry;
import com.blogspot.chingovan.jsf_example.trainer.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

/**
 * Created by ChiNV on 11/26/2017.
 */
@ManagedBean
public class LoginBacking extends AbstractBacking{

    private UIInput loginOutcomeChoiceList;
    private User nonAuthenticatedUser;

    //Getters and Setter


    public UIInput getLoginOutcomeChoiceList() {
        return loginOutcomeChoiceList;
    }

    public User getNonAuthenticatedUser() {
        return nonAuthenticatedUser;
    }

    public void forwardToLoginIfNotLoggedIn(ComponentSystemEvent componentSystemEvent) {

        String viewId = getFacesContext().getViewRoot().getViewId();

        if( !isUserLoggedIn() && !viewId.startsWith("/login") && !viewId.startsWith("/register")) {
            getFacesContext().getApplication().getNavigationHandler().handleNavigation(getFacesContext(), null, "/login?faces-redirect=true");
        }
    }

    public void forwardToMainIfNotTrainer(ComponentSystemEvent cse) {
        User user;
        if (null != (user = getCurrentUser()) && !user.isTrainer()) {
            getFacesContext().getApplication().getNavigationHandler().
                    handleNavigation(getFacesContext(), null, "/user/all_events?faces-redirect=true");
        }
    }

    public boolean userIdIsValid(Long userId) {

        UserRegistry registry = UserRegistry.getInstance();
        return (null != (nonAuthenticatedUser = registry.getUserByUserId(userId)));
    }

    public boolean passwordIsValid(String password) {
        boolean result = false;
        if( null != nonAuthenticatedUser) {
            String userPassword = nonAuthenticatedUser.getPassword();
            if( null != userPassword && userPassword.equals( password)) {
                setCurrentUser(nonAuthenticatedUser);
                result = true;
            }
        }
        nonAuthenticatedUser = null;
        return result;
    }

    public String getSuccessOutcome() {
        String choice = (String) getLoginOutcomeChoiceList().getValue();
        return "/user/" + choice + "?faces-redirect=true";
    }

    public String getFailureOutcome() {
        String choice = (String) getLoginOutcomeChoiceList().getValue();
        return "/user/" + choice + "?faces-redirect=true";
    }

    public String performLogout() {
        String result = "/login?faces-redirect=true";
        setCurrentUser(null);
        getFacesContext().getExternalContext().invalidateSession();
        return result;
    }
}
