package com.example.buchin.jadwalbuchin.Homework;

public class HomeWorkModel {
    String ID_HomeWork;
    String Tittle;
    String Type;
    String Description;
    String Date;
    String Status;

    public HomeWorkModel(String tittle, String type, String description, String date, String status) {

        Tittle = tittle;
        Type = type;
        Description = description;
        Date = date;
        Status = status;
    }

    public HomeWorkModel(String tittle, String type, String description, String date) {
        Tittle = tittle;
        Type = type;
        Description = description;
        Date = date;
    }

    public HomeWorkModel(String ID_HomeWork, String tittle, String type, String description, String date, String status) {
        this.ID_HomeWork = ID_HomeWork;
        Tittle = tittle;
        Type = type;
        Description = description;
        Date = date;
        Status = status;
    }

    public HomeWorkModel() {

    }


    public String getID_HomeWork() {
        return ID_HomeWork;
    }

    public void setID_HomeWork(String ID_HomeWork) {
        this.ID_HomeWork = ID_HomeWork;
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

}
