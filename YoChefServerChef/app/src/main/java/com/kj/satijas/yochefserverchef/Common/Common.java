package com.kj.satijas.yochefserverchef.Common;

import com.kj.satijas.yochefserverchef.Model.Request;
import com.kj.satijas.yochefserverchef.Model.User;

public class Common {
    public static User CurrentUser;
    public static Request currentRequest;


    public static final String UPDATE="Update";
    public static final String DELETE="Delete";

    public static final int PICK_IMAGE_REQUEST=71;

    public static String convertCodeToStatus(String code)
    {
        if(code.equals("0"))
            return "Placed";
        else if(code.equals("1"))
            return "Preparing Order";
        else
            return "Delivered";
    }
 }
