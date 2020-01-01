package com.jack.paham.model;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RecordDB extends SQLiteOpenHelper {

    public static final String DatabaseName = "record.db";
    private static final int DatabaseVersion = 6;

    public static abstract class MyColums implements BaseColumns{
        public static final String tableName = "record";
        public static final String name = "name";
        public static final String address = "address";
        public static final String score = "score";
    }

    private static final String SQL_CREATE_TABLE_RECORD = String.format(
      "CREATE TABLE %s" +
              "(%s INTEGER PRIMARY KEY AUTOINCREMENT," +
              " %s TEXT NOT NULL," +
              " %s TEXT NOT NULL," +
              " %s TEXT NOT NULL)",
            MyColums.tableName,
            MyColums._ID,
            MyColums.name,
            MyColums.address,
            MyColums.score
    );

    public RecordDB(Context context){
        super(context, DatabaseName, null, DatabaseVersion);
    }

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + MyColums.tableName;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_RECORD);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS" + MyColums.tableName);
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
