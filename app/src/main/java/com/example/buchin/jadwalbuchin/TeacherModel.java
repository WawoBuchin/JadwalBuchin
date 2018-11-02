package com.example.buchin.jadwalbuchin;

public class TeacherModel {
    String ID_Teacher;
    String Name;
    String Phone;
    String Email;
    String Office;
    String OfficeHours;
    String Username;

    public TeacherModel() {

    }

    public TeacherModel(String ID_Teacher, String name, String phone, String email, String office, String officeHours, String username) {
        this.ID_Teacher = ID_Teacher;
        Name = name;
        Phone = phone;
        Email = email;
        Office = office;
        OfficeHours = officeHours;
        Username = username;
    }

    public String getID_Teacher() {
        return ID_Teacher;
    }

    public void setID_Teacher(String ID_Teacher) {
        this.ID_Teacher = ID_Teacher;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getOffice() {
        return Office;
    }

    public void setOffice(String office) {
        Office = office;
    }

    public String getOfficeHours() {
        return OfficeHours;
    }

    public void setOfficeHours(String officeHours) {
        OfficeHours = officeHours;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
