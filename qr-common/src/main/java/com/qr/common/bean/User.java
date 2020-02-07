package com.qr.common.bean;

import java.util.Set;

/**
 * @author:Jascon
 * @date:2020-01-16 11:26
 */
public class User {
    private String id;
    private String userName;
    private String password;

    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }



    public User() {
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }


    public String getPassword() {
        return password;
    }

    public User(String id, String userName, String password, Set<Role> roles) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
