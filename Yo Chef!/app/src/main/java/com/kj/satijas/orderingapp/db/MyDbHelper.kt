package com.kj.satijas.orderingapp.db

import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.database.sqlite.SQLiteDatabase


class MyDbHelper(context:Context) :SQLiteOpenHelper(context,"chef.db",null,1)
{
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(CustomerTable.CMD_CREATE_TABLE) //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}