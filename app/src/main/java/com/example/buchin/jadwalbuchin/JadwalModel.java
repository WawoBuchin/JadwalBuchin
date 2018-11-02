package com.example.buchin.jadwalbuchin;

public class JadwalModel {
    String ID_Jadwal;
    String ID_Hari;
    String Username;
    String Subject;
    String ID_Teacher;
    String Room;
    String From;
    String To;
    String ID_Color;

    public JadwalModel() {
    }

    public JadwalModel(String ID_Jadwal, String ID_Hari, String username, String subject, String ID_Teacher, String room, String from, String to, String ID_Color) {
        this.ID_Jadwal = ID_Jadwal;
        this.ID_Hari = ID_Hari;
        Username = username;
        Subject = subject;
        this.ID_Teacher = ID_Teacher;
        Room = room;
        From = from;
        To = to;
        this.ID_Color = ID_Color;
    }

    public String getID_Jadwal() {
        return ID_Jadwal;
    }

    public void setID_Jadwal(String ID_Jadwal) {
        this.ID_Jadwal = ID_Jadwal;
    }

    public String getID_Hari() {
        return ID_Hari;
    }

    public void setID_Hari(String ID_Hari) {
        this.ID_Hari = ID_Hari;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getID_Teacher() {
        return ID_Teacher;
    }

    public void setID_Teacher(String ID_Teacher) {
        this.ID_Teacher = ID_Teacher;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
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
