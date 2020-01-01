package com.jack.paham;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.jack.paham.difficult.DifficultLevel;
import com.jack.paham.hard.HardLevel;
import com.jack.paham.medium.MediumLevel;

public class LevelActivity extends AppCompatActivity {
    ImageView begin, medium, hard, difficult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        begin = findViewById(R.id.begin);
        medium = findViewById(R.id.btn_medium);
        hard = findViewById(R.id.btn_hard);
        difficult = findViewById(R.id.btn_difficult);

        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent move = new Intent(LevelActivity.this, SubLevelActivity.class);
                startActivity(move);
            }
        });
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent move = new Intent(LevelActivity.this, MediumLevel.class);
                startActivity(move);
            }
        });
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveHard = new Intent(LevelActivity.this, HardLevel.class);
                startActivity(moveHard);
            }
        });
        difficult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveDiff = new Intent(LevelActivity.this, DifficultLevel.class);
                startActivity(moveDiff);
            }
        });
    }
}
