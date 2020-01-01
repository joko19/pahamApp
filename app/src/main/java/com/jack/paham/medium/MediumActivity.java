package com.jack.paham.medium;

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
import com.jack.paham.adapter.MediumAdapter;
import com.jack.paham.hard.HardLevel;
import com.jack.paham.model.Medium;

import java.util.ArrayList;

public class MediumActivity extends AppCompatActivity {
    RadioGroup rg_anwer;
    RadioButton a,b,c;
    Button submit;
    public static final String EXTRA_MEDIUM = "extra_medium";
    Medium dataQuiz;
    Dialog myDialog, falseDialog, opening, red;
    ArrayList<Medium> data;
    int score;
    private ImageView question;
    public static int passed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium);
        dataQuiz = getIntent().getParcelableExtra(EXTRA_MEDIUM);
        insialisasi();
        question.setImageResource(dataQuiz.getQuestion());
        score = (dataQuiz.getNum() - 40) * 10 + 200;
        getSupportActionBar().setTitle("Level " + dataQuiz.getNum());
        if (dataQuiz.getNum() == 41)
            popUpOpening();
        if (dataQuiz.getNum() == 46)
            popUpRed();
        a.setText(dataQuiz.getaChoise());
        b.setText(dataQuiz.getbChoise());
        c.setText(dataQuiz.getcChoise());
        data = MediumAdapter.data_Medium;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected = rg_anwer.getCheckedRadioButtonId();
                switch (selected){
                    case R.id.rb_a:
//                        Toast.makeText(MediumActivity.this, "hello world", Toast.LENGTH_SHORT).show();
                        if (dataQuiz.getAnswer() == 1){
                            popUpTrue();
                        }
                        else
                            if (dataQuiz.getNum() < 46)
                                popUpFalse();
                            else
                                gameOver();
                        break;
                    case R.id.rb_b:
                        if (dataQuiz.getAnswer() == 2)
                            popUpTrue();
                        else
                            if (dataQuiz.getNum() < 46)
                            popUpFalse();
                            else
                                gameOver();
                        break;
                    case R.id.rb_c:
                        if (dataQuiz.getAnswer() == 3)
                            if (dataQuiz.getNum() == 70)
                                mediumSuccess();
                            else
                            popUpTrue();
                        else
                            if (dataQuiz.getNum() < 46)
                                popUpFalse();
                            else
                                gameOver();
                        break;

                }
            }
        });
    }

    private void insialisasi() {
        question = findViewById(R.id.question_medium);
        rg_anwer = findViewById(R.id.rg_medium_answer);
        a = findViewById(R.id.rb_a);
        b = findViewById(R.id.rb_b);
        c = findViewById(R.id.rb_c);
        submit = findViewById(R.id.btn_medium_submit);
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

        img.setImageResource(R.drawable.pol2);
        title.setText("Selamat Datang di Level Medium");
        aman.setText("41 - 45");
        merah.setText("46 - 70");
        score.setText("+10");
        description.setText("Baca setiap pertanyaan dengan teliti. Setiap soal memiliki 3 pilihan jawaban. Pilih 1 jawaban yang benar. Fokus dan selamat mengerjakan");

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

        category.setText("Medium");
        level.setText("46");
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

        MediaPlayer correct= MediaPlayer.create(MediumActivity.this,R.raw.correct);
        correct.start();

        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.pop_up_true);

        Button next = myDialog.findViewById(R.id.btn_next);
        TextView close = myDialog.findViewById(R.id.tv_close);
        TextView level = myDialog.findViewById(R.id.tv_level_true);
        TextView tScore = myDialog.findViewById(R.id.tv_score_true);
        TextView category = myDialog.findViewById(R.id.pop_up_true_category);

        category.setText("Medium");
        String levelKe = Integer.toString(dataQuiz.getNum());
        int Skor = (dataQuiz.getNum() - 40) * 10 + 200 ;
        String skorAkhir = Integer.toString(Skor);

        level.setText(levelKe);
        tScore.setText(skorAkhir);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent benar = new Intent(MediumActivity.this, MediumActivity.class);
                benar.putExtra(EXTRA_MEDIUM, MediumAdapter.data_Medium.get(dataQuiz.getNum() - 40));
                passed = dataQuiz.getNum()+2;
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

        MediaPlayer wrong = MediaPlayer.create(MediumActivity.this, R.raw.wrong);
        wrong.start();


        falseDialog = new Dialog(this);
        falseDialog.setContentView(R.layout.pop_up_false);

        TextView close = falseDialog.findViewById(R.id.tv_close_false);
        Button try_again = falseDialog.findViewById(R.id.btn_coba_lagi);
        TextView level = falseDialog.findViewById(R.id.tv_level_false);
        TextView mScore = falseDialog.findViewById(R.id.tv_score_false);
        TextView category = falseDialog.findViewById(R.id.tv_category_false);

        category.setText("Medium");
        String tLevel = Integer.toString(dataQuiz.getNum());
        int tScore = (dataQuiz.getNum() - 40) * 10 + 200 - 10;
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
                Intent try_again = new Intent(MediumActivity.this, MediumActivity.class);
                try_again.putExtra(EXTRA_MEDIUM, data.get(dataQuiz.getNum() - 41));
                startActivity(try_again);
            }
        });
        falseDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        falseDialog.show();
    }

    private void gameOver() {
        Intent lose = new Intent(MediumActivity.this, GameOverMedium.class);
        lose.putExtra(EXTRA_MEDIUM, data.get(dataQuiz.getNum() - 41));
        EasyActivity.score = 0;
        passed = 0;
        startActivity(lose);
    }

    private void mediumSuccess() {
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.pop_up_true);
        passed = dataQuiz.getNum() + 2;
        final Button next = myDialog.findViewById(R.id.btn_next);
        TextView close = myDialog.findViewById(R.id.tv_close);
        TextView level = myDialog.findViewById(R.id.tv_level_true);
        TextView tScore = myDialog.findViewById(R.id.tv_score_true);
        TextView title = myDialog.findViewById(R.id.title_true);
        Button nextlevel = myDialog.findViewById(R.id.btn_next);
        TextView category = myDialog.findViewById(R.id.pop_up_true_category);

        category.setText("Medium");
        title.setText("Anda sudah berhasil menyelesaikan Level Medium");
        nextlevel.setText("Lanjut ke Level Hard");
        String levelKe = Integer.toString(dataQuiz.getNum());
        int Skor = (dataQuiz.getNum() - 40) * 10 + 200 ;
        String skorAkhir = Integer.toString(Skor);

        level.setText(levelKe);
        tScore.setText(skorAkhir);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMedium = new Intent(MediumActivity.this, HardLevel.class);
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
