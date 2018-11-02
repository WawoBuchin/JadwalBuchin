package com.example.buchin.jadwalbuchin;

public class ProposalModel {
    String ID_Proposal;
    String Username;
    String Grade;
    String Description;
    String Date;

    public ProposalModel() {
    }

    public ProposalModel(String ID_Proposal, String username, String grade, String description, String date) {
        this.ID_Proposal = ID_Proposal;
        Username = username;
        Grade = grade;
        Description = description;
        Date = date;
    }

    public String getID_Proposal() {
        return ID_Proposal;
    }

    public void setID_Proposal(String ID_Proposal) {
        this.ID_Proposal = ID_Proposal;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
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
}
