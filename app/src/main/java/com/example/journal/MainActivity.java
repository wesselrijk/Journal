package com.example.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private EntryAdapter adapter;
    private EntryDatabase db;

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

    @Override
    protected void onResume() {
        super.onResume();
        updateData();
    }

    public void newEntryClicked(View view) {
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    private class OnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor cursor = db.selectEntry(id);

            /* check if a cursor is null and check for moveToFirst()
            https://android.wekeepcoding.com/article/21027603/android.database.
            CursorIndexOutOfBoundsException%3A+Index+0+requested%2C+with+a+size+of+0 */
            if( cursor != null && cursor.moveToFirst() ){

                // get all data from the cursor
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String mood = cursor.getString(cursor.getColumnIndex("mood"));
                Long timestamp = cursor.getLong(cursor.getColumnIndex("timestamp"));
                cursor.close();

                // create the clicked journal entry and create a new intent with it
                JournalEntry clickedJournal = new JournalEntry(title, content, mood, timestamp);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("clicked_journal", clickedJournal);
                startActivity(intent);
            }
        }
    }

    private class OnItemLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            db.deleteEntry(id);
            updateData();
            return true;
        }
    }

    private void updateData() {
        adapter.swapCursor(db.selectAll());
    }

}
