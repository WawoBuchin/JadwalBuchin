package com.example.buchin.jadwalbuchin.FragmentHari;

public class Schedule_Model {
    String Subject_Id;
    String Subject_Name;
    String Subject_Teacher;
    String Subject_Room;
    String Subject_Time;
    String Subject_Day;
    //String Subject_Week;
    String User_Email;

    public Schedule_Model() {
    }

    public Schedule_Model(String subject_Id,String subject_Name, String subject_Teacher, String subject_Room, String subject_Time) {
        Subject_Id = subject_Id;
        Subject_Name = subject_Name;
        Subject_Teacher = subject_Teacher;
        Subject_Room = subject_Room;
        Subject_Time = subject_Time;
    }

    public Schedule_Model(String subject_Name, String subject_Teacher, String subject_Room, String subject_Time) {
        Subject_Name = subject_Name;
        Subject_Teacher = subject_Teacher;
        Subject_Room = subject_Room;
        Subject_Time = subject_Time;
    }

    public Schedule_Model(String subject_Name, String subject_Teacher, String subject_Room, String subject_Time, String subject_Day, String user_Email) {
       if(user_Email.contains("@")){
           Subject_Name = subject_Name;
           Subject_Teacher = subject_Teacher;
           Subject_Room = subject_Room;
           Subject_Time = subject_Time;
           Subject_Day = subject_Day;
           User_Email = user_Email;
       }else{
           Subject_Name = subject_Name;
           Subject_Teacher = subject_Teacher;
           Subject_Room = subject_Room;
           Subject_Time = subject_Time;
           Subject_Day = subject_Day;
           Subject_Id = user_Email;
       }



    }



    public String getSubject_Id() {
        return Subject_Id;
    }

    public void setSubject_Id(String subject_Id) {
        Subject_Id = subject_Id;
    }

    public String getSubject_Name() {
        return Subject_Name;
    }

    public void setSubject_Name(String subject_Name) {
        Subject_Name = subject_Name;
    }

    public String getSubject_Teacher() {
        return Subject_Teacher;
    }

    public void setSubject_Teacher(String subject_Teacher) {
        Subject_Teacher = subject_Teacher;
    }

    public String getSubject_Room() {
        return Subject_Room;
    }

    public void setSubject_Room(String subject_Room) {
        Subject_Room = subject_Room;
    }

    public String getSubject_Time() {
        return Subject_Time;
    }

    public void setSubject_Time(String subject_Time) {
        Subject_Time = subject_Time;
    }

    public String getUser_Email() {
        return User_Email;
    }

    public void setUser_Email(String user_Email) {
        User_Email = user_Email;
    }

    public String getSubject_Day() {
        return Subject_Day;
    }

    public void setSubject_Day(String subject_Day) {
        Subject_Day = subject_Day;
    }
}
