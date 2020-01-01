package com.jack.paham.adapter;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jack.paham.EasyActivity;
import com.jack.paham.R;
import com.jack.paham.difficult.DifficultLevel;
import com.jack.paham.hard.HardGameOver;
import com.jack.paham.medium.MediumActivity;
import com.jack.paham.model.Hard;

import java.util.ArrayList;

public class HardActivity extends AppCompatActivity {

    public static final String EXTRA_HARD = "extra_hard";
    public static int passed_hard;
    private ImageView question;
    private RadioGroup radioGroup;
    private RadioButton a,b,c,d;
    private Button btnSubmit;
    private int score;
    private Dialog opening, red, myDialog, falseDialog;
    private Hard dataQuiz;
    private ArrayList<Hard> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard);
        inisialisasi();
        dataQuiz = getIntent().getParcelableExtra(EXTRA_HARD);
        getSupportActionBar().setTitle("Level " + dataQuiz.getNum());

        question.setImageResource(dataQuiz.getQuestion());
        score = (dataQuiz.getNum() - 70) * 15 + 500;
        if (dataQuiz.getNum() == 71)
            popUpOpening();
        if (dataQuiz.getNum() == 76)
            popUpRed();
        a.setText(dataQuiz.getaPilih());
        b.setText(dataQuiz.getbPilih());
        c.setText(dataQuiz.getcPilih());
        d.setText(dataQuiz.getdPilih());
        data = HardAdapter.dataHard;

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected = radioGroup.getCheckedRadioButtonId();
                switch (selected){
                    case R.id.a_hard:
                        if (dataQuiz.getAnswer() == 1){
                            popUpTrue();
                        }
                        else{
                            if (dataQuiz.getNum() < 76)
                                popUpFalse();
                            else
                                gameOver();
                        }
                        break;
                    case R.id.b_hard:
                        if (dataQuiz.getAnswer() == 2)
                            popUpTrue();
                        else{
                            if (dataQuiz.getNum() < 76)
                                popUpFalse();
                            else
                                gameOver();
                        }
                        break;
                    case R.id.c_hard:
                        if (dataQuiz.getAnswer() == 3){
                            if (dataQuiz.getNum() == 90)
                                hardSuccess();
                            else
                                popUpTrue();
                        }
                        else{
                            if (dataQuiz.getNum() < 76)
                                popUpFalse();
                            else
                                gameOver();
                        }
                        break;
                    case R.id.d_hard:
                        if (dataQuiz.getAnswer() == 4)
                            popUpTrue();
                        else{
                            if (dataQuiz.getNum() < 76)
                                popUpFalse();
                            else
                                gameOver();
                        }
                        break;

                }
            }
        });
    }

    private void inisialisasi() {
        question = findViewById(R.id.hard_question);
        radioGroup = findViewById(R.id.hard_rg);
        a = findViewById(R.id.a_hard);
        b = findViewById(R.id.b_hard);
        c = findViewById(R.id.c_hard);
        d = findViewById(R.id.d_hard);
        btnSubmit = findViewById(R.id.hard_submit);
    }


    private void popUpOpening() {
        opening = new Dialog(this);
        opening.setContentView(R.layout.pop_up_easy_opening);

        TextView close, title, aman, merah, score, description;
        ImageView img;
        close = opening.findViewById(R.id.tv_close_easy_open);
        Button start = opening.findViewById(R.id.btn_easy_start);
        title = opening.findViewById(R.id.opening_title);
        aman = opening.findViewById(R.id.opening_aman);
        merah = opening.findViewById(R.id.opening_merah);
        score = opening.findViewById(R.id.opening_score);
        description = opening.findViewById(R.id.opening_description);
        img = opening.findViewById(R.id.img_pop_up_opening);

        img.setImageResource(R.drawable.pol3);
        title.setText("Selamat Datang di Level Hard");
        aman.setText("71 - 75");
        merah.setText("76 - 90");
        score.setText("+15");
        description.setText("Setiap soal terdapat sebuah gambar dan satu kata tanya, anda harus bisa memahami pertanyaan yang dimaksud hanya dengan bermodal satu kata tersebut. sehingga anda dapat menemukan jawaban yang tepat");

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



    private void popUpRed() {
        red = new Dialog(this);
        red.setContentView(R.layout.pop_up_level_merah);

        TextView close, category, level, nilai;
        close = red.findViewById(R.id.tv_close_easy_red);
        Button next = red.findViewById(R.id.btn_easy_red);
        category = red.findViewById(R.id.red_category);
        level = red.findViewById(R.id.red_level);
        nilai = red.findViewById(R.id.red_score);

        category.setText("Hard");
        level.setText("76");
        String tScore = Integer.toString(score);
        nilai.setText(tScore);

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


    public void popUpTrue(){

        MediaPlayer correct= MediaPlayer.create(HardActivity.this,R.raw.correct);
        correct.start();
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.pop_up_true);

        Button next = myDialog.findViewById(R.id.btn_next);
        TextView close = myDialog.findViewById(R.id.tv_close);
        TextView level = myDialog.findViewById(R.id.tv_level_true);
        TextView tScore = myDialog.findViewById(R.id.tv_score_true);
        TextView category = myDialog.findViewById(R.id.pop_up_true_category);

        category.setText("Hard");
        String levelKe = Integer.toString(dataQuiz.getNum());
        int Skor = (dataQuiz.getNum() - 70) * 15 + 500 ;
        String skorAkhir = Integer.toString(Skor);

        level.setText(levelKe);
        tScore.setText(skorAkhir);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent benar = new Intent(HardActivity.this, HardActivity.class);
                benar.putExtra(EXTRA_HARD, data.get(dataQuiz.getNum() - 70));
                passed_hard = dataQuiz.getNum()+2;
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

        MediaPlayer wrong = MediaPlayer.create(HardActivity.this, R.raw.wrong);
        wrong.start();


        falseDialog = new Dialog(this);
        falseDialog.setContentView(R.layout.pop_up_false);

        TextView close = falseDialog.findViewById(R.id.tv_close_false);
        Button try_again = falseDialog.findViewById(R.id.btn_coba_lagi);
        TextView level = falseDialog.findViewById(R.id.tv_level_false);
        TextView mScore = falseDialog.findViewById(R.id.tv_score_false);
        TextView category = falseDialog.findViewById(R.id.tv_category_false);

        category.setText("Hard");
        String tLevel = Integer.toString(dataQuiz.getNum());
        int tScore = (dataQuiz.getNum() - 70) * 15 + 500 - 15;
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
                Intent try_again = new Intent(HardActivity.this, HardActivity.class);
                try_again.putExtra(EXTRA_HARD, data.get(dataQuiz.getNum() - 71));
                startActivity(try_again);
            }
        });
        falseDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        falseDialog.show();
    }

    private void gameOver() {
        Intent lose = new Intent(HardActivity.this, HardGameOver.class);
        lose.putExtra(EXTRA_HARD, data.get(dataQuiz.getNum() - 71));
        EasyActivity.score = 0;
        MediumActivity.passed = 0;
        passed_hard = 0;
        startActivity(lose);
    }

    private void hardSuccess() {
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.pop_up_true);
        passed_hard = dataQuiz.getNum() + 2;
        final Button next = myDialog.findViewById(R.id.btn_next);
        TextView close = myDialog.findViewById(R.id.tv_close);
        TextView level = myDialog.findViewById(R.id.tv_level_true);
        TextView tScore = myDialog.findViewById(R.id.tv_score_true);
        TextView title = myDialog.findViewById(R.id.title_true);
        Button nextlevel = myDialog.findViewById(R.id.btn_next);
        TextView category = myDialog.findViewById(R.id.pop_up_true_category);

        category.setText("Hard");
        title.setText("Anda sudah berhasil menyelesaikan Level Hard");
        nextlevel.setText("Lanjut ke Level Difficult");
        String levelKe = Integer.toString(dataQuiz.getNum());
        int Skor = (dataQuiz.getNum() - 40) * 10 + 200 ;
        String skorAkhir = Integer.toString(Skor);

        level.setText(levelKe);
        tScore.setText(skorAkhir);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMedium = new Intent(HardActivity.this, DifficultLevel.class);
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

}
