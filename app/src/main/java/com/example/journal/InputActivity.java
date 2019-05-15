package com.example.journal;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class InputActivity extends AppCompatActivity {


    private ImageView clickedImage;
    private String mood = "mood";

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
                String.valueOf(content.getText()), mood, System.currentTimeMillis());
        db.insert(entry);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void moodClicked(View view) {
        ImageView image = (ImageView) view;
        image.setBackgroundColor(0xFF689F38);
        if (clickedImage != null) {
            clickedImage.setBackgroundColor(0x00000000);
        }
        mood = String.valueOf(image.getTag());
        clickedImage = image;
    }
}
