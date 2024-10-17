package ru.mirea.gurovts.pkmn;

import java.io.Serializable;

public class Student implements Serializable {
    private String firstName, surName, familyName, group;
    public static final long serialVersionUID = 1L;

    public Student(String firstName, String surName, String familyName, String group) {
        this.firstName = firstName;
        this.surName = surName;
        this.familyName = familyName;
        this.group = group;
    }

    public Student()
    {

    }


    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' + "\n" +
                "surName='" + surName + '\'' + "\n" +
                "familyName='" + familyName + '\'' + "\n" +
                "group='" + group + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
