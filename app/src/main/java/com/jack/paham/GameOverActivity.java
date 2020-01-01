package com.jack.paham;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jack.paham.model.Category;
import com.jack.paham.model.RecordDB;

public class GameOverActivity extends AppCompatActivity {
    private Category data;
    private EditText edtName, edtFrom;
    private Button record;
    private TextView score, level;
    private RecordDB recordDB;
    String name, from, gScore, gLevel;
    Integer eScore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
        inisalisasi();

        MediaPlayer wrong = MediaPlayer.create(GameOverActivity.this, R.raw.wrong);
        wrong.start();

        recordDB = new RecordDB(getBaseContext());

        data = getIntent().getParcelableExtra(EasyActivity.EXTRA_QUIZ);
        gLevel = Integer.toString(data.getNum());

        eScore = data.getNum() * 5 - 5;
        gScore = Integer.toString(eScore);
        score.setText(gScore);
        level.setText(gLevel);

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = edtName.getText().toString();
                from = edtFrom.getText().toString();
                if (name.equals("") || from.equals("")) {
                    Toast.makeText(GameOverActivity.this, "Silahkan isi data terlebih dahulu", Toast.LENGTH_SHORT).show();
                } else {
                    saveData();
                    Intent move = new Intent(GameOverActivity.this, RecordActivity.class);
                    startActivity(move);
                }
            }
        });
    }

    private void saveData() {
        SQLiteDatabase db = recordDB.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RecordDB.MyColums.name, name);
        values.put(RecordDB.MyColums.address, from);
        values.put(RecordDB.MyColums.score, gScore);
        db.insert(RecordDB.MyColums.tableName, null, values);
        db.close();
    }

    private void inisalisasi() {
        edtName = findViewById(R.id.edt_name);
        edtFrom = findViewById(R.id.edt_from);
        record = findViewById(R.id.btn_record);
        score = findViewById(R.id.tv_score_game_over);
        level = findViewById(R.id.tv_level_game_over);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
