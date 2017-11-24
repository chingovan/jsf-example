package com.blogspot.chingovan.jsf_example.login;

import javax.faces.bean.ManagedBean;

/**
 * Created by ChiNV on 11/24/2017.
 */
@ManagedBean(name = "loginController")
public class LoginController {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Actions
    public String loginAction() {
        if (username.equals("guest") && password.equals("welcome")) {
            return "success";
        } else {
            return "failure";
        }
    }
}
