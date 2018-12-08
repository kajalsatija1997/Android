package com.kj.satijas.orderingapp.db

import android.database.sqlite.SQLiteDatabase
import com.kj.satijas.orderingapp.custData
import android.content.ContentValues

object CustomerTable{
    val TABLE_NAME="CustomerTable"
    object columns{
        val ID="id"
        val NAME="name"
        val MOBILE_NO="mobile_no"
    }
    val CMD_CREATE_TABLE="""
        CREATE TABLE IF NOT EXISTS $TABLE_NAME
        (
        ${columns.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
        ${columns.NAME} TEXT,
        ${columns.MOBILE_NO} INT
        );
        """.trimIndent()

    fun insertData(db:SQLiteDatabase,data:custData)
    {
     val row=ContentValues()
        row.put(columns.NAME,data.Name)
        row.put(columns.MOBILE_NO,data.Mobile)

        db.insert(TABLE_NAME,null,row)
    }

    fun getAllTodos(db:SQLiteDatabase):ArrayList<custData> //read frm db
    {
        val custs=ArrayList<custData>()
        var c=db.query(TABLE_NAME,
                arrayOf(columns.ID,columns.NAME,columns.MOBILE_NO),
                null,
                null,
                null,
                null,
                null)

        while (c.moveToNext()){
            val todo=custData(c.getString(1),c.getLong(2))
            custs.add(todo)
        }
        return  custs
    }
}