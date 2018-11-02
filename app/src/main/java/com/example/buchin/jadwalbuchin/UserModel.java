package com.example.buchin.jadwalbuchin;

public class UserModel {
    String Username;
    String Password;
    String Name;
    String Email;
    //String Foto;


    public UserModel() {
    }

    public UserModel(String username, String password, String name, String email) {
        Username = username;
        Password = password;
        Name = name;
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getNama() {
        return Name;
    }

    public void setNama(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
