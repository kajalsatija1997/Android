package com.kj.satijas.orderingapp.Model;

public class CustomerClass {
   private String name;
   private String table;
   private String IsStaff;

    public CustomerClass()
    {

    }

    public String getIsStaff() {
        return IsStaff;
    }

    public void setIsStaff(String isStaff) {
        IsStaff = isStaff;
    }

    public CustomerClass(String Name , String Booktable) {
        name = Name;
        table=Booktable;
        IsStaff="false";
    }

    public String getBooktable() {
        return table;
    }

    public void setBooktable(String table) {
        this.table = table;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
