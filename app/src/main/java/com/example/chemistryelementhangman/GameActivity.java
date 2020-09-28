package com.example.chemistryelementhangman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.widget.EditText;
import android.widget.ImageView;
import android.view.Menu;
import android.view.KeyEvent;
import android.widget.Toast;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private ImageView imageView;

    private ImageView question_image;

    private TextView tv_score;

    private List<ElementItem> list;

    Random r;

    int turn = 1;
    int score = 0;
    int wrong_choice = 0;

    // image sequence for hangman
    int[] img_seq = {R.drawable.hangman_1, R.drawable.hangman_2, R.drawable.hangman_3,
            R.drawable.hangman_4, R.drawable.hangman_5, R.drawable.hangman_6,
            R.drawable.hangman_7, R.drawable.hangman_8, R.drawable.hangman_9,
            R.drawable.hangman_10, R.drawable.hangman_11, R.drawable.hangman_12};

    int question_rnd;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        r = new Random();


        question_image = (ImageView) findViewById(R.id.question_image);

        imageView = (ImageView) findViewById(R.id.hangman_image);

        tv_score = (TextView) findViewById(R.id.tv_score);
        tv_score.setText("SCORE: " + score);

        final MediaPlayer correctSignal = MediaPlayer.create(this, R.raw.bike_horn);
        final MediaPlayer wrongSignal = MediaPlayer.create(this, R.raw.slap);
        final MediaPlayer dieSignal = MediaPlayer.create(this, R.raw.game_over);


        list = new ArrayList<>();

        // adding all celebrities and their names to the list
        for (int i = 0; i < new Database().answers.length; i++) {
            list.add(new ElementItem(new Database().answers[i], new Database().elements[i]));
        }

        newQuestion();



        question_rnd = r.nextInt(19);
        question_image.setImageResource(new Database().elements[question_rnd]);
        final EditText answer = (EditText) findViewById(R.id.answer);
        final boolean right_wrong = false;

        answer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String answer_r = answer.getText().toString();
                if (!answer_r.equals("")) {
                    if (answer_r.charAt(answer_r.length() - 1) == '\n') {
                        if (answer_r.trim().equalsIgnoreCase(new Database().answers[question_rnd])) {
                            Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                            answer.getText().clear();
                            answer.setText("");
                            correctSignal.start();
                            score++;
                            tv_score.setText("SCORE: " + score);
                            SharedPreferences preferences = getSharedPreferences("PREPS", 0);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("lastScore", "" + score);
                            editor.apply();
                            question_rnd = r.nextInt(18);
                            newQuestion();
                        }
                        else {
                            // press wrong letter, and hangman forms
                            Toast.makeText(GameActivity.this, "WRONG!", Toast.LENGTH_SHORT).show();
                            wrong_choice++;
                            answer.setText("");
                            wrongSignal.start();
                            imageView.setImageResource(img_seq[wrong_choice -1]);

                            // once hangman is in 12th form, game over
                            if (wrong_choice == 12) {
                                imageView.setImageResource(R.drawable.hangman_game_over);
                                answer.setText("BETTER LUCK NEXT TIME! PRESS BACK TO RETURN TO MENU");
                                dieSignal.start();
                                Toast.makeText(GameActivity.this, "GAME OVER", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {
            }
        });
    }


    private void newQuestion() {
        // set element image to the screen
        question_image.setImageResource(new Database().elements[question_rnd]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game, menu);
        return true;

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent (getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

}
