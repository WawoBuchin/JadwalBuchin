package com.example.buchin.jadwalbuchin;

public class ReminderModel {
    String ID_Reminder;
    String Username;
    String Tittle;
    String Description;
    String Date;
    String Time;
    String ID_Color;

    public ReminderModel() {
    }

    public ReminderModel(String ID_Reminder, String username, String tittle, String description, String date, String time, String ID_Color) {
        this.ID_Reminder = ID_Reminder;
        Username = username;
        Tittle = tittle;
        Description = description;
        Date = date;
        Time = time;
        this.ID_Color = ID_Color;
    }

    public String getID_Reminder() {
        return ID_Reminder;
    }

    public void setID_Reminder(String ID_Reminder) {
        this.ID_Reminder = ID_Reminder;
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

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getID_Color() {
        return ID_Color;
    }

    public void setID_Color(String ID_Color) {
        this.ID_Color = ID_Color;
    }
}
