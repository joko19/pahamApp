package com.jack.paham;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jack.paham.adapter.CategoryAdapter;
import com.jack.paham.medium.MediumLevel;
import com.jack.paham.model.Category;

import java.util.ArrayList;

public class EasyActivity extends AppCompatActivity {
    ImageView question, btnTrue, btnFalse;
    public static final String EXTRA_QUIZ = "extra_quiz;";
    Category dataQuiz;
    public static int score;
    Dialog myDialog, falseDialog, opening, red;
    ArrayList<Category> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_easy);
        inisialisasi();

        dataQuiz = getIntent().getParcelableExtra(EXTRA_QUIZ);
        String judul = Integer.toString(dataQuiz.getNum());
        getSupportActionBar().setTitle("Level " + judul);
        question.setImageResource(dataQuiz.getQuestion());
        if (dataQuiz.getNum() == 1){
            popUpOpening();
        }
        if (dataQuiz.getNum() == 11)
            popUpRed();
        data = CategoryAdapter.categoryArrayList;
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (dataQuiz.getAnswer() == 1){
                if (dataQuiz.getNum() == 40)
                    easySuccess();
                else
                    popUpTrue();
            }
            else {
                if (dataQuiz.getNum() < 11)
                popUpFalse();
                else{
                    gameOver();
                }
            }
            }
        });

        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataQuiz.getAnswer() == 0){
                    if (dataQuiz.getNum() == 40)
                        easySuccess();
                    else
                        popUpTrue();
                }
                else{
                    if (dataQuiz.getNum() < 11)
                        popUpFalse();
                    else{
                        gameOver();
                    }
                }

            }
        });
    }

    private void easySuccess() {
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.pop_up_true);

        final Button next = myDialog.findViewById(R.id.btn_next);
        TextView close = myDialog.findViewById(R.id.tv_close);
        TextView level = myDialog.findViewById(R.id.tv_level_true);
        TextView tScore = myDialog.findViewById(R.id.tv_score_true);
        TextView title = myDialog.findViewById(R.id.title_true);
        Button nextlevel = myDialog.findViewById(R.id.btn_next);

        title.setText("Anda sudah berhasil menyelesaikan Level Easy");
        nextlevel.setText("Lanjut ke Level Medium");
        String levelKe = Integer.toString(dataQuiz.getNum());
        int Skor = dataQuiz.getNum() * 5 ;
        String skorAkhir = Integer.toString(Skor);

        level.setText(levelKe);
        tScore.setText(skorAkhir);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent toMedium = new Intent(EasyActivity.this, MediumLevel.class);
                    score = dataQuiz.getNum()+2;
                    startActivity(toMedium);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

    }

    private void popUpRed() {
        red = new Dialog(this);
        red.setContentView(R.layout.pop_up_level_merah);
        TextView close = red.findViewById(R.id.tv_close_easy_red);
        Button next = red.findViewById(R.id.btn_easy_red);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                red.dismiss();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                red.dismiss();
            }
        });
        red.show();
    }

    private void popUpOpening() {
        opening = new Dialog(this);
        opening.setContentView(R.layout.pop_up_easy_opening);
        TextView close = opening.findViewById(R.id.tv_close_easy_open);
        Button start = opening.findViewById(R.id.btn_easy_start);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             opening.dismiss();
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opening.dismiss();
            }
        });
        opening.show();

    }

    private void gameOver() {

        Intent lose = new Intent(EasyActivity.this, GameOverActivity.class);
        lose.putExtra(EasyActivity.EXTRA_QUIZ, data.get(dataQuiz.getNum() - 1));
        score = 0;
        startActivity(lose);
    }

    private void inisialisasi() {
        question = findViewById(R.id.img_question_easy);
        btnTrue = findViewById(R.id.btn_benar);
        btnFalse = findViewById(R.id.btn_salah);
    }


    public void popUpTrue(){

        MediaPlayer music= MediaPlayer.create(EasyActivity.this,R.raw.correct);
        music.start();

        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.pop_up_true);

        final Button next = myDialog.findViewById(R.id.btn_next);
        TextView close = myDialog.findViewById(R.id.tv_close);
        TextView level = myDialog.findViewById(R.id.tv_level_true);
        TextView tScore = myDialog.findViewById(R.id.tv_score_true);

        String levelKe = Integer.toString(dataQuiz.getNum());
        int Skor = dataQuiz.getNum() * 5 ;
        String skorAkhir = Integer.toString(Skor);

        level.setText(levelKe);
        tScore.setText(skorAkhir);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent benar = new Intent(EasyActivity.this, EasyActivity.class);
                    benar.putExtra(EasyActivity.EXTRA_QUIZ, data.get(dataQuiz.getNum()));
                    score = dataQuiz.getNum()+2;
                    startActivity(benar);
                }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public void popUpFalse(){

        MediaPlayer wrong= MediaPlayer.create(EasyActivity.this,R.raw.wrong);
        wrong.start();

        falseDialog = new Dialog(this);
        falseDialog.setContentView(R.layout.pop_up_false);

        TextView close = falseDialog.findViewById(R.id.tv_close_false);
        Button try_again = falseDialog.findViewById(R.id.btn_coba_lagi);
        TextView level = falseDialog.findViewById(R.id.tv_level_false);
        TextView mScore = falseDialog.findViewById(R.id.tv_score_false);

        String tLevel = Integer.toString(dataQuiz.getNum());
        int tScore = dataQuiz.getNum() * 5 - 5;
        String nScore = Integer.toString(tScore);

        level.setText(tLevel);
        mScore.setText(nScore);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                falseDialog.dismiss();
            }
        });
        try_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent try_again = new Intent(EasyActivity.this, EasyActivity.class);
                try_again.putExtra(EasyActivity.EXTRA_QUIZ, data.get(dataQuiz.getNum() - 1));
                startActivity(try_again);
            }
        });
        falseDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        falseDialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
