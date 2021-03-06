package com.example.journal;
/**
 * The EntryDatabase class for the app.
 * This is the class that handles a SQLite database to store or delete journal entries.
 */

// List of imports.
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EntryDatabase extends SQLiteOpenHelper {

    private static EntryDatabase instance;

    // Constructor of the class calls super.
    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory,
                         int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    // In the onCreate of the class, a database is created using SQL execs.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table journalEntries (_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", title TEXT, content TEXT, mood TEXT, timestamp INTEGER);");
        db.execSQL("INSERT INTO journalEntries (title, content, mood, timestamp) VALUES ('First entry!', " +
                "'This is your first entry!', 'happy', "+System.currentTimeMillis()+")");
    }

    // Method that drops the existing table and creates a new one by calling the onCreate.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "journalEntries");
        onCreate(db);
    }

    /*
    * Method that, with added context, returns the instance, if it exists, of the database, or
    * creates a new instance before returning if it did not exist yet.
    * Used the answer of https://stackoverflow.com/questions/24214148/java-getinstance-vs-static
    */
    public static synchronized EntryDatabase getInstance(Context context) {
        if (instance == null) {

            // Used https://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper
            instance = new EntryDatabase(context, "journalEntries", null, 1,
                    null);
        }

        return instance;
    }

    // Method that returns all entries in the database.
    public Cursor selectAll() {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM journalEntries", null);
    }

    // Method that returns a single entry from the database.
    public Cursor selectEntry(long id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM journalEntries WHERE _id IS " + id, null);
    }

    // Method that inserts a new entry into the database.
    public void insert(JournalEntry entry) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", entry.getTitle());
        values.put("content", entry.getContent());
        values.put("mood", entry.getMood());
        values.put("timestamp", entry.getTimestamp());
        db.insert("journalEntries", null, values);
    }

    // Method that deletes one entry from the database.
    public void deleteEntry (Long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM journalEntries WHERE _id = " + id);
    }
}
