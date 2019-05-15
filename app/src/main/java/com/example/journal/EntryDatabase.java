package com.example.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EntryDatabase extends SQLiteOpenHelper {

    private static EntryDatabase instance;

    // constructor of the class
    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory,
                         int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    // the oncreate of the class, a database is created using SQL execs
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table journalEntries (_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", title TEXT, content TEXT, mood TEXT, timestamp INTEGER);");
        db.execSQL("INSERT INTO journalEntries (title, content, mood, timestamp) VALUES ('First entry!', " +
                "'This is your first entry!', 'happy', "+System.currentTimeMillis()+")");
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

    public Cursor selectAll() {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM journalEntries", null);
    }

    public Cursor selectEntry(long id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM journalEntries WHERE _id IS " + id, null);
    }

    public void insert(JournalEntry entry) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", entry.getTitle());
        values.put("content", entry.getContent());
        values.put("mood", entry.getMood());
        values.put("timestamp", entry.getTimestamp());
        Long hoi = db.insert("journalEntries", null, values);
    }

    public void deleteEntry (Long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM journalEntries WHERE _id = " + id);
    }
}
