package com.example.journal;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.DateFormat;

public class DetailActivity extends AppCompatActivity {

    EntryDatabase db;

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
