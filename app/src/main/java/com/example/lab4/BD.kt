package com.example.lab4

import android.provider.BaseColumns
import com.example.lab4.model.SomethingData

class BD : BaseColumns {

    val TABLE_NAME = "tasks";
    val COLUMN_NAME_TITLE = "title";
    val COLUMN_NAME_DESC = "description";

    val DATABASE_VERSION = 1;
    val DATABASE_NAME = "TaskManager.db";

    val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, $COLUMN_NAME_TITLE TEXT, $COLUMN_NAME_DESC TEXT)";

    val DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME";
}