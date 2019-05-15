package com.example.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import java.text.DateFormat;

public class EntryAdapter extends ResourceCursorAdapter {

    // constructor
    public EntryAdapter(Context context, int layout, Cursor cursor) {
        super(context, layout, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ImageView imageView = view.findViewById(R.id.entryMood);
        TextView titleView = view.findViewById(R.id.entryTitle);
        TextView dateView = view.findViewById(R.id.entryDate);

        String title = cursor.getString(cursor.getColumnIndex("title"));
        Long timestamp = cursor.getLong(cursor.getColumnIndex("timestamp"));
        String mood = cursor.getString(cursor.getColumnIndex("mood"));

        /* convert date https://stackoverflow.com/questions/454315/
        how-do-you-format-date-and-time-in-android */
        String date = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
                DateFormat.SHORT).format(timestamp);

        // switch over the mood to display the corresponding image
        switch(mood){
            case "mood":
                imageView.setImageResource(R.drawable.
                        iconfinder_android_robot_mobile_mood_emoji_1172122);
                break;
            case "angry":
                imageView.setImageResource(R.drawable.
                        iconfinder_android_robot_mobile_mood_emoji_angry_upset_1172121);
                break;
            case "dead":
                imageView.setImageResource(R.drawable.
                        iconfinder_android_robot_mobile_mood_emoji_crash_bug_dead_1172120);
                break;
            case "tounge":
                imageView.setImageResource(R.drawable.
                        iconfinder_android_robot_mobile_mood_emoji_happy_joke_tounge_1172119);
                break;
            case "smile":
                imageView.setImageResource(R.drawable.
                        iconfinder_android_robot_mobile_mood_emoji_happy_smile_1172131);
                break;
            case "successful":
                imageView.setImageResource(R.drawable.
                        iconfinder_android_robot_mobile_mood_emoji_happy_smile_successful_1172130);
                break;
            case "lovely":
                imageView.setImageResource(R.drawable.
                        iconfinder_android_robot_mobile_mood_emoji_love_lovely_1172127);
                break;
            case "sad":
                imageView.setImageResource(R.drawable.
                        iconfinder_android_robot_mobile_mood_emoji_sad_1172128);
                break;
            case "crying":
                imageView.setImageResource(R.drawable.
                        iconfinder_android_robot_mobile_mood_emoji_sad_crying_1172126);
                break;
            case "tear":
                imageView.setImageResource(R.drawable.
                        iconfinder_android_robot_mobile_mood_emoji_sad_tear_1172125);
                break;
            case "ill":
                imageView.setImageResource(R.drawable.
                        iconfinder_android_robot_mobile_mood_emoji_sick_ill_trouble_1172124);
                break;
            case "happy":
                imageView.setImageResource(R.drawable.
                        iconfinder_android_robot_mobile_mood_emoji_smile_happy_successful_1172129);
                break;
            case "tired":
                imageView.setImageResource(R.drawable.
                        iconfinder_android_robot_mobile_sleeping_tired_1172123);
                break;
        }

        titleView.setText(title);
        dateView.setText(date);
    }
}
