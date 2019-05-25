package com.example.journal;
/**
 * The DetailActivity activity for the app.
 * In this activity the user can view a journal entry that is saved in the database when the
 * corresponding item in the list in the activity_main has been clicked on. The title, content and
 * timestamp of the journal entry will be displayed.
 */

// List of imports.
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.DateFormat;

public class DetailActivity extends AppCompatActivity {

    /* In the onCreate, the clicked journal entry will be received from the intent and its
    * information set to corresponding textViews in the detail_activity.
    */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        Intent intent = getIntent();
        JournalEntry clickedJournal = (JournalEntry) intent.
                getSerializableExtra("clicked_journal");

        TextView title = findViewById(R.id.detailTitle);
        TextView content = findViewById(R.id.detailContent);
        TextView date = findViewById(R.id.detailDate);

        title.setText(clickedJournal.getTitle());
        content.setText(clickedJournal.getContent());

        String dateString = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
                DateFormat.SHORT).format(clickedJournal.getTimestamp());
        date.setText(dateString);
    }

}
