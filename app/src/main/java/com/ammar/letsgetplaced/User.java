package com.ammar.letsgetplaced;

public class User {
    public String email,cgpa,schno;

    public User() {

    }

    public User(String email, String cgpa, String schno) {
        this.email = email;
        this.cgpa = cgpa;
        this.schno = schno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCgpa() {
        return cgpa;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }

    public String getSchno() {
        return schno;
    }

    public void setSchno(String schno) {
        this.schno = schno;
    }
}
