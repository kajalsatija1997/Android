package com.kj.satijas.orderingapp.Model;

public class Food {
    private String Name,Price,Discount;

    public Food(){

    }

    public Food(String name, String price, String discount) {
        Name = name;
        Price = price;
        Discount = discount;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

}
