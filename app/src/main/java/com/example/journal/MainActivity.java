package com.example.journal;
/**
 * <h1>Journal</h1>
 * The MainActivity for the Journal app.
 * The Journal app is an app in which a user can store journal entries, view stored entries and
 * delete unwanted entries.
 * In the MainActivity a list will be displayed of all the entries that are currently in the
 * database of the app. There is a floating action button in the bottom right of the screen which,
 * after being clicked on, will start the InputActivity where a new entry can be put into the
 * database. Clicking on an item in the list will start the DetailActivity in which the user can
 * view the details of a past entry.
 */

// List of imports.
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    // Initialize necessary variables.
    private EntryAdapter adapter;
    private EntryDatabase db;

    /*
     * The MainActivity for the app is created here, it makes use of a private EntryAdapter and
     * EntryDatabase variable. In the onCreate, the activity_main is set, a database is
     * assigned and an adapter is created and set to the listView of the activity_main.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(new OnItemClickListener());
        listView.setOnItemLongClickListener(new OnItemLongClickListener());

        db = EntryDatabase.getInstance(getApplicationContext());
        adapter = new EntryAdapter(this, R.layout.entry_row, db.selectAll());
        listView.setAdapter(adapter);
    }


    // Calls updateData if MainActivity is resumed.
    @Override
    protected void onResume() {
        super.onResume();
        updateData();
    }

    // Method that starts the InputActivity if the FloatingAction button has been clicked.
    public void newEntryClicked(View view) {
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    // Method to start the DetailActivity if a journal entry has been clicked on.
    private class OnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor cursor = db.selectEntry(id);

            /* check if a cursor is null and check for moveToFirst(), used info from:
            * https://android.wekeepcoding.com/article/21027603/android.database.
            * CursorIndexOutOfBoundsException%3A+Index+0+requested%2C+with+a+size+of+0 */
            if( cursor != null && cursor.moveToFirst() ){

                // Get all data from the cursor.
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String mood = cursor.getString(cursor.getColumnIndex("mood"));
                Long timestamp = cursor.getLong(cursor.getColumnIndex("timestamp"));
                cursor.close();

                // Create the clicked journal entry and, with the entry, start a new intent.
                JournalEntry clickedJournal = new JournalEntry(title, content, mood, timestamp);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("clicked_journal", clickedJournal);
                startActivity(intent);
            }
        }
    }

    // Method to delete an entry after a longClick has been implemented on an item in the list.
    private class OnItemLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            db.deleteEntry(id);
            updateData();
            return true;
        }
    }

    // Method that updates information on screen through the adapter.
    private void updateData() {
        adapter.swapCursor(db.selectAll());
    }

}
