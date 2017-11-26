package com.blogspot.chingovan.jsf_example.trainer.backing;

import com.blogspot.chingovan.jsf_example.trainer.accessor.exception.EntityAccessorException;
import com.blogspot.chingovan.jsf_example.trainer.accessor.register.UserRegistry;
import com.blogspot.chingovan.jsf_example.trainer.constant.RequestConstant;
import com.blogspot.chingovan.jsf_example.trainer.model.User;
import sun.security.validator.ValidatorException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 * Created by ChiNV on 11/26/2017.
 */
@ManagedBean
public class RegisterBacking extends AbstractBacking {
    private Object password1;

    public void validatePassword1(FacesContext facesContext, UIComponent uiComponent, Object password1) throws ValidatorException{

        if( null == password1 || password1.toString().length() == 0) {
            throw  new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Password is cannot blank"));
        }

        this.password1 = password1;
    }

    public void validatePassword2(FacesContext facesContext, UIComponent uiComponent, Object password2) throws ValidatorException {
        if (!(password1.equals(password2))) {

            throw new ValidatorException( new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Passwords must match."));
        }
    }

    public String registerUser() {

        String result = null;

        User user = (User) getRequestMap().get(RequestConstant.USER);
        user.setPassword((String)getRequestMap().get(RequestConstant.PASSWORD_1));
        try {
            UserRegistry.getInstance().addUser(user);

            setCurrentUser(user);
            result = "/user/all_events?faces-redirect=true";
        } catch (EntityAccessorException ex) {
            getFacesContext().addMessage(null,new FacesMessage("Error when adding user" + ((null != user) ? " " + user.toString() : "") + "."));
        }
        return result;
    }
}
