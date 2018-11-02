package com.example.buchin.jadwalbuchin;

public class HolidayModel {
    String ID_Holiday;
    String Username;
    String Tittle;
    String Description;
    String From;
    String To;
    String ID_Color;

    public HolidayModel() {
    }

    public HolidayModel(String ID_Holiday, String username, String tittle, String description, String from, String to, String ID_Color) {
        this.ID_Holiday = ID_Holiday;
        Username = username;
        Tittle = tittle;
        Description = description;
        From = from;
        To = to;
        this.ID_Color = ID_Color;
    }

    public String getID_Holiday() {
        return ID_Holiday;
    }

    public void setID_Holiday(String ID_Holiday) {
        this.ID_Holiday = ID_Holiday;
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

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public String getID_Color() {
        return ID_Color;
    }

    public void setID_Color(String ID_Color) {
        this.ID_Color = ID_Color;
    }
}
