package com.example.journal;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EntryDatabase extends SQLiteOpenHelper {

    static EntryDatabase instance;

    // constructor of the class
    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory,
                         int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    // the oncreate of the class, a database is created using SQL execs
    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryInitial = "create table journalEntries (_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", title TEXT, content TEXT, mood TEXT, timestamp INTEGER);";
        db.execSQL(queryInitial);
        db.execSQL("INSERT INTO journalEntries (title, mood, timestamp) VALUES ('First entry!', " +
                "'This is your first entry!', 'happy', System.currentTimeMillis())");
    }

    // this method drops the existing table and creates a new one by calling the oncreate
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "journalEntries");
        onCreate(db);
    }

    // used the answer of https://stackoverflow.com/questions/24214148/java-getinstance-vs-static
    public static synchronized EntryDatabase getInstance(Context context) {
        if (instance == null) {
            // used https://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper
            instance = new EntryDatabase(context, "journalEntries", null, 1,
                    null);
        }
        return instance;
    }
}
