package com.example.journal;
/**
 * The InputActivity activity for the app.
 * In this activity the user can create a journal entry. The activity takes a few input sources in
 * the form of EditText's from the user, which creates a JournalEntry object after the user has
 * submitted the entry. This object will be inserted into the database.
 */

// List of imports.
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class InputActivity extends AppCompatActivity {

    // Initialize necessary variables.
    private ImageView clickedImage;
    private String mood = "mood"; // Default value for the mood, if the user does not specify one.

    // onCreate sets the input_activity layout.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_activity);
    }

    // Method that submits an entry to the database, if the submitEntry button has been clicked.
    public void submitEntryClicked(View view) {
        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
        EditText title = findViewById(R.id.editTitle);
        EditText content = findViewById(R.id.editContent);

        JournalEntry entry = new JournalEntry(String.valueOf(title.getText()),
                String.valueOf(content.getText()), mood, System.currentTimeMillis());
        db.insert(entry);
        finish();
    }

    // Method that shuts down the activity if user goes to another activity.
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /* Method that sets the mood for the journal entry when the user clicks on a mood icon. Also
    * sets the visual response for clicking on a mood icon. */
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
