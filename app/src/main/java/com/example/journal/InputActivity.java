package com.example.journal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_activity);
    }

    public void submitEntryClicked(View view) {
        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
        EditText title = findViewById(R.id.editTitle);
        EditText content = findViewById(R.id.editContent);

        JournalEntry entry = new JournalEntry(String.valueOf(title.getText()),
                String.valueOf(content.getText()), "happy");
        db.insert(entry);

        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
