package com.kj.satijas.yochefserverchef.Model;

public class User {
private String name,Password,booktable,phone,IsStaff;

    public User(String name, String password) {
        this.name = name;
        Password = password;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getBooktable() {
        return booktable;
    }

    public void setBooktable(String booktable) {
        this.booktable = booktable;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIsStaff() {
        return IsStaff;
    }

    public void setIsStaff(String isStaff) {
        IsStaff = isStaff;
    }
}
