package com.example.journal;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class EntryAdapter extends ResourceCursorAdapter {

    private Cursor cursor;

    // constructor TODO: why is super deprecated
    public EntryAdapter(Context context, int layout, Cursor cursor) {
        super(context, layout, cursor);
        this.cursor = cursor;
        Log.d("length of cursor", String.valueOf(cursor.getCount()));
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Log.d("Entries binding", "yes");
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView titleView = view.findViewById(R.id.entryTitle);
        TextView dateView = view.findViewById(R.id.entryDate);

        String title = cursor.getString(cursor.getColumnIndex("title"));
        String date = cursor.getString(cursor.getColumnIndex("title"));

        titleView.setText(title);
        dateView.setText(date);
    }
}
