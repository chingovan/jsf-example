package com.blogspot.chingovan.jsf_example.trainer.backing;

import com.blogspot.chingovan.jsf_example.trainer.constant.SessionConstant;
import com.blogspot.chingovan.jsf_example.trainer.model.User;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ChiNV on 11/26/2017.
 */
@RequestScoped
public abstract class AbstractBacking {

    @ManagedProperty(value = "#{facesContext}")
    private FacesContext facesContext;

    @ManagedProperty(value = "#{requestMap}")
    private Map<String, Object> requestMap;

    @ManagedProperty(value = "#{sessionMap}")
    private Map<String, Object> sessionMap;

    /**
     * Returns current user
     * @return A {@link User} object or <code>null</code>
     */
    public User getCurrentUser() {
        return (User) getSessionMap().get(SessionConstant.CURRENT_USER);
    }

    /**
     * Sets current user
     * @param currentUser The logged in user
     */
    public void setCurrentUser(User currentUser) {

        getSessionMap().remove(currentUser);
        if( null != currentUser) {
            getSessionMap().put(SessionConstant.CURRENT_USER, currentUser);
        }
    }

    /**
     * Returns the logged in status of {@link User}
     * @return {@code true} if user logged in, otherwise {@code false}.
     */
    public boolean isUserLoggedIn() {

        return  getSessionMap().containsKey(SessionConstant.CURRENT_USER);
    }

    public FacesContext getFacesContext() {
        return facesContext;
    }

    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }

    public Flash getFlash() {
        return getFacesContext().getExternalContext().getFlash();
    }

    public Map<String, Object> getRequestMap() {
        if( requestMap == null) {
            requestMap = new HashMap<>();
        }
        return requestMap;
    }

    public Map<String, Object> getSessionMap() {
        if( sessionMap == null) {
            sessionMap = new HashMap<>();
        }
        return sessionMap;
    }
}
