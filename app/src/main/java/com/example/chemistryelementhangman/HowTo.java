package com.example.chemistryelementhangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;

public class HowTo extends AppCompatActivity {

    private TextView instructions;
    private ImageView instruction_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how);

        instructions = (TextView) findViewById(R.id.instructions);
        instruction_image = (ImageView) findViewById(R.id.instruction_image);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent (getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
