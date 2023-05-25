package com.example.lab4

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.lab4.model.SomethingData

class DBManager(context: Context) {
    val DBHelper = DBHelper(context);

    var db: SQLiteDatabase? = null;

    fun openDB(){
        db = DBHelper.writableDatabase;
    }

    fun insertToDB(title: String, desc: String){
        val values = ContentValues().apply {
            put(BD().COLUMN_NAME_TITLE, title)
            put(BD().COLUMN_NAME_DESC, desc)
        }

        db?.insert(BD().TABLE_NAME, null, values);
    }

    fun readDbData(): List<SomethingData>{
        var dataList: List<SomethingData> = listOf();

        val cursor = db?.query(BD().TABLE_NAME, null, null, null, null, null, null);

        while (cursor?.moveToNext()!!){
            val id = cursor.getString(cursor.getColumnIndexOrThrow("_id"))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(BD().COLUMN_NAME_TITLE)).toString()
            val desc = cursor.getString(cursor.getColumnIndexOrThrow(BD().COLUMN_NAME_DESC)).toString()
            dataList = dataList + SomethingData(id.toInt(), title, desc)
        }
        cursor.close();
        return dataList;
    }

    fun deleteInBD(title: String){
        db?.delete(BD().TABLE_NAME,"_id = $title", null)
        db?.close();
    }

    fun closeDB(){
        DBHelper.close();
    }
}