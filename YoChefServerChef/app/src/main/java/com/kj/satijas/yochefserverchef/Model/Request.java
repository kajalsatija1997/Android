package com.kj.satijas.yochefserverchef.Model;

import java.util.List;

public class Request {
    private String name;
    private String total;
    private String table_no;
    private List<Order> foods;
    private String status;

    public Request() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTable_no() {
        return table_no;
    }

    public void setTable_no(String table_no) {
        this.table_no = table_no;
    }

    public List<Order> getFoods() {
        return foods;
    }

    public void setFoods(List<Order> foods) {
        this.foods = foods;
    }

    public Request(String name, String total, String table_no, List<Order> foods) {
        this.name = name;
        this.total = total;
        this.table_no = table_no;
        this.foods = foods;
        this.status="0"; // 0-order placed,1-preparing order.2=Delivered
    }


}
