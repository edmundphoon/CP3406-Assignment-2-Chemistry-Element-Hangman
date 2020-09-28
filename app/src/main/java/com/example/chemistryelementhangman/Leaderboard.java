package com.example.chemistryelementhangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.SharedPreferences;

public class Leaderboard extends AppCompatActivity {

    TextView tv_score;

    int lastScore;
    int best1, best2, best3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        tv_score = (TextView) findViewById(R.id.tv_score);

        SharedPreferences preferences = getSharedPreferences("PREPS", 0);
        lastScore = Integer.parseInt(preferences.getString("lastScore", "0"));
        best1 = preferences.getInt("best1", 0);
        best2 = preferences.getInt("best2", 0);
        best3 = preferences.getInt("best3", 0);

        if(lastScore > best3) {
            best3 = lastScore;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best3", best3);
            editor.apply();
        }

        else if(lastScore > best2) {
            int temp = best2;
            best2 = lastScore;
            best3 = temp;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best3", best3);
            editor.putInt("best2", best2);
            editor.apply();
        }

        else if(lastScore > best1) {
            int temp = best1;
            best1 = lastScore;
            best2 = temp;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best2", best2);
            editor.putInt("best1", best1);
            editor.apply();
        }

        tv_score.setText(("LAST SCORE: " + lastScore + "\n" + "BEST 1: " + best1 + "\n" + "BEST 2: " + best2 + "\n" + "BEST 3: " + best3));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent (getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
