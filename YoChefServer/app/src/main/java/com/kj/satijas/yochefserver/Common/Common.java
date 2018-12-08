package com.kj.satijas.yochefserver.Common;

import com.kj.satijas.yochefserver.Model.Request;
import com.kj.satijas.yochefserver.Model.User;

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
