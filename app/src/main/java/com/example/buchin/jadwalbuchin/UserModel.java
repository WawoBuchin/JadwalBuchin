package com.example.buchin.jadwalbuchin;

public class UserModel {

    String UserPassword;
    String UserName;
    String UserEmail;

    public UserModel(String userName, String userEmail, String userPassword) {
        UserName = userName;
        UserEmail = userEmail;
        UserPassword = userPassword;
    }
    //String Foto;


    public UserModel(String userPassword, String userEmail) {
        UserPassword = userPassword;
        UserEmail = userEmail;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }
}
