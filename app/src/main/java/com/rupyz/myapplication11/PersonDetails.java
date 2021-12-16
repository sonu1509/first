package com.rupyz.myapplication11;

public class PersonDetails {
    private String name;
    private String email;
    private String phone;
    private String dob;
    private String gender;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // constructor
    public PersonDetails(String name, String email, String phone, String dob,String gender) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.gender=gender;
    }
}

