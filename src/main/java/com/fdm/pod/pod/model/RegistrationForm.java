package com.fdm.pod.pod.model;

import java.util.Date;

public class RegistrationForm {

    private String firstName;
    private String lastName;
    private String email;
    private Date DOB;

    public RegistrationForm() {
        super();
    }

    public RegistrationForm(String firstName, String lastName, String email, Date DOB) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.DOB = DOB;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }
}
