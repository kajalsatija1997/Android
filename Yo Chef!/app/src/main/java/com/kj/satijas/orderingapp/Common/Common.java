package com.kj.satijas.orderingapp.Common;

import com.kj.satijas.orderingapp.Model.CustomerClass;

public class Common {
    public static CustomerClass CurrentCustomer;

    public static String convertCodeToStatus(String status) {
        if(status.equals("0"))
            return "Placed";
        else if(status.equals("1"))
            return "Preparing Order";
        else
            return "Delivered";
    }
}
