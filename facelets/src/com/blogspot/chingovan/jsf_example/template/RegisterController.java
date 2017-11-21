package com.blogspot.chingovan.jsf_example.template;

import com.blogspot.chingovan.jsf_example.util.ServiceLevel;
import sun.security.validator.ValidatorException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import java.util.Date;

/**
 * Created by ChiNV on 11/21/2017.
 */
@ManagedBean(name = "registerController")
@SessionScoped
public class RegisterController {

    private String firstName;
    private String lastName;
    private boolean sex;
    private String emailAddress;
    private Date dateOfBirth = new Date();
    private int serviceLevel;

    public RegisterController() {
        this.firstName = "John";
        this.lastName = "MC";
        this.sex = true;
        this.emailAddress = "john@abc.com";
        this.serviceLevel = ServiceLevel.MEDIUM.value;
    }

    //Setter and Getter
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

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(int serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    //Validations
    public void validateEmailAddress(FacesContext facesContext, UIComponent toValidate, Object value) throws ValidatorException {

        String emailStr = (String) value;
        if (emailStr.indexOf("@") < 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation", "Invalid email address");
            throw new ValidatorException(message.toString());
        }
    }

    //Actions
    public String addConfirmedUser() {
        boolean added = true; // actual application may fail to add user
        FacesMessage doneMessage = null;
        String outcome = null;
        if (added) {
            doneMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Successfully added new user");
            outcome = "done";
        } else {
            doneMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failure!", "Failed to add new user");
            outcome = "register";
        }
        FacesContext.getCurrentInstance().addMessage(null, doneMessage);
        return outcome;
    }
}
