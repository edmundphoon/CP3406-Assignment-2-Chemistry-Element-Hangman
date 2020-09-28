package com.example.chemistryelementhangman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Button playGame, howToPlay, scoreboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setting portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // buttons
        playGame = (Button) findViewById(R.id.play_button);
        howToPlay = (Button) findViewById(R.id.instruction_button);
        scoreboard = (Button) findViewById(R.id.score_button);

        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent myIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(myIntent);
            }
        });

        howToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent (getApplicationContext(), HowTo.class);
                startActivity(intent);
                finish();
            }
        });


        scoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //SharedPreferences preferences = getSharedPreferences("PREPS", 0);
                //SharedPreferences.Editor editor = preferences.edit();
                //editor.putInt("lastScore", score);
                //editor.apply();

                Intent intent = new Intent (getApplicationContext(), Leaderboard.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
