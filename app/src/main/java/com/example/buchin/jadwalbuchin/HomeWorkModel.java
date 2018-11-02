package com.example.buchin.jadwalbuchin;

public class HomeWorkModel {
    String ID_HomeWork;
    String Username;
    String Tittle;
    String Type;
    String Description;
    String Date;
    String Status;
    String ID_Color;

    public HomeWorkModel() {
    }

    public HomeWorkModel(String ID_HomeWork, String username, String tittle, String type, String description, String date, String status, String ID_Color) {
        this.ID_HomeWork = ID_HomeWork;
        Username = username;
        Tittle = tittle;
        Type = type;
        Description = description;
        Date = date;
        Status = status;
        this.ID_Color = ID_Color;
    }

    public String getID_HomeWork() {
        return ID_HomeWork;
    }

    public void setID_HomeWork(String ID_HomeWork) {
        this.ID_HomeWork = ID_HomeWork;
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

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getID_Color() {
        return ID_Color;
    }

    public void setID_Color(String ID_Color) {
        this.ID_Color = ID_Color;
    }
}
