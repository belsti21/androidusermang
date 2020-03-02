package com.belstiyezengaw.usermanagement;

public class UserModel {
    private String firstName;
    private String lastName;
    private String userName;
    private String  eMail;
    private  String password;
    private  String mobile;


    public UserModel(String firstName, String lastName, String userName, String eMail, String password, String mobile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.eMail = eMail;
        this.password = password;
        this.mobile = mobile;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String geteMail() {
        return eMail;
    }

    public String getPassword() {
        return password;
    }

    public String getMobile() {
        return mobile;
    }


}
