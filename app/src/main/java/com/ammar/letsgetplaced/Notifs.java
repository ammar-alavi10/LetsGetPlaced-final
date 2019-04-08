package com.ammar.letsgetplaced;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Notifs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifs);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        String title = pref.getString("Title",null);
        String message = pref.getString("Body",null);

        TextView tvtitle = findViewById(R.id.title);
        TextView tvmsg = findViewById(R.id.msg);

        tvtitle.setText(title);
        tvmsg.setText(message);
    }
}
