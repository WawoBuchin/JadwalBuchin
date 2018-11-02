package com.example.buchin.jadwalbuchin;

public class TestModel {
    String ID_Test;
    String Username;
    String Tittle;
    String Description;
    String Date;
    String ID_Color;

    public TestModel() {
    }

    public TestModel(String ID_Test, String username, String tittle, String description, String date, String ID_Color) {
        this.ID_Test = ID_Test;
        Username = username;
        Tittle = tittle;
        Description = description;
        Date = date;
        this.ID_Color = ID_Color;
    }

    public String getID_Test() {
        return ID_Test;
    }

    public void setID_Test(String ID_Test) {
        this.ID_Test = ID_Test;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getTittle() {
        return Tittle;
    }

    public void setTittle(String tittle) {
        Tittle = tittle;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getID_Color() {
        return ID_Color;
    }

    public void setID_Color(String ID_Color) {
        this.ID_Color = ID_Color;
    }
}
