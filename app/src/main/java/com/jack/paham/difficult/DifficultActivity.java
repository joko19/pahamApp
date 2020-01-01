package com.jack.paham.difficult;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.jack.paham.EasyActivity;
import com.jack.paham.R;
import com.jack.paham.adapter.DifficultAdapter;
import com.jack.paham.adapter.HardActivity;
import com.jack.paham.medium.MediumActivity;
import com.jack.paham.model.Difficult;

import java.util.ArrayList;

public class DifficultActivity extends AppCompatActivity {

    public static final String EXTRA_DIFFICULT = "extra_difficult";
    public static int passed_diff;
    private VideoView videoView;
    private MediaController mediaController;
    private Difficult dataQuiz;
    private Dialog myDialog, falseDialog, opening, red;
    private int score;
    private RadioGroup radioGroup;
    private RadioButton a,b,c,d;
    private Button btn_submit;
    private ArrayList<Difficult> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficult);
        inisialisasi();
        dataQuiz = getIntent().getParcelableExtra(EXTRA_DIFFICULT);

        getSupportActionBar().setTitle("Level " + dataQuiz.getNum());

        score = (dataQuiz.getNum() - 90) * 20 + 800;
        if (dataQuiz.getNum() == 91)
            popUpOpening();
        if (dataQuiz.getNum() == 94)
            popUpRed();
        setVideo();
        a.setText(dataQuiz.getAdiff());
        b.setText(dataQuiz.getBdiff());
        c.setText(dataQuiz.getCdiff());
        d.setText(dataQuiz.getDdiff());
        data = DifficultAdapter.data_difficult;
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected = radioGroup.getCheckedRadioButtonId();
                switch (selected){
                    case R.id.a_difficult:
                        if (dataQuiz.getAnswer() == 1){
                            popUpTrue();
                        }
                        else{
                            if (dataQuiz.getNum() < 94)
                                popUpFalse();
                            else
                                gameOver();
                        }
                        break;
                    case R.id.b_difficult:
                        if (dataQuiz.getAnswer() == 2){
                            popUpTrue();
                        }
                        else{
                            if (dataQuiz.getNum() < 94)
                                popUpFalse();
                            else
                                gameOver();
                        }
                        break;
                    case R.id.c_difficult:
                        if (dataQuiz.getAnswer() == 3){
                            popUpTrue();
                        }
                        else{
                            if (dataQuiz.getNum() < 94)
                                popUpFalse();
                            else
                                gameOver();
                        }
                        break;
                    case R.id.d_difficult:
                        if (dataQuiz.getAnswer() == 4){
                            popUpTrue();
                        }
                        else{
                            if (dataQuiz.getNum() < 94)
                                popUpFalse();
                            else
                                gameOver();
                        }
                        break;
                }
            }
        });
    }

    private void win() {

    }

    private void gameOver() {
        Intent lose = new Intent(DifficultActivity.this, DiffGameOver.class);
        lose.putExtra(EXTRA_DIFFICULT, data.get(dataQuiz.getNum() - 91));
        EasyActivity.score = 0;
        MediumActivity.passed = 0;
        HardActivity.passed_hard = 0;
        passed_diff = 0;
        startActivity(lose);
    }

    private void setVideo() {
        int source = dataQuiz.getQuestion();
        String tes = "android.resource://" + getPackageName() + "/" + source;
        videoView.setVideoURI(Uri.parse(tes));
        //digunakan untuk mengidentifikasi resource seperti lokasi video
        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        //menampilkan media controller video
        if (dataQuiz.getNum() > 91 && dataQuiz.getNum() < 94 || dataQuiz.getNum()  > 94){
            videoView.start();
        }
    }

    private void inisialisasi() {
        videoView = findViewById(R.id.videoView_question);
        radioGroup = findViewById(R.id.rg_difficult);
        a = findViewById(R.id.a_difficult);
        b = findViewById(R.id.b_difficult);
        c = findViewById(R.id.c_difficult);
        d = findViewById(R.id.d_difficult);
        btn_submit = findViewById(R.id.btn_difficult_submit);
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
        title.setText("Selamat Datang di Level Difficult");
        aman.setText("91 - 93");
        merah.setText("94 - 100");
        score.setText("+20");
        description.setText("Setiap level terdiri dari sebuah video dan 4 pilihan jawaban. video berisi teka teki yang harus dipecahkan. tetap fokus, teliti dan berhati hati dalam menjawab setiap soal");

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
                videoView.start();
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

        category.setText("Difficult");
        level.setText("94");
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
                videoView.start();
            }
        });
        red.show();
    }


    public void popUpTrue(){
        videoView.stopPlayback();
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.pop_up_hard_true);

        VideoView video_true = myDialog.findViewById(R.id.videoView_true);
        Button next = myDialog.findViewById(R.id.btn_next_diff);
        TextView close = myDialog.findViewById(R.id.tv_close_diff);
        TextView level = myDialog.findViewById(R.id.tv_level_true_diff);
        TextView tScore = myDialog.findViewById(R.id.tv_score_true_diff);

        int source = dataQuiz.getVidAnswer();
        String tes = "android.resource://" + getPackageName() + "/" + source;
        video_true.setVideoURI(Uri.parse(tes));
        //digunakan untuk mengidentifikasi resource seperti lokasi video
        mediaController = new MediaController(this);
        mediaController.setAnchorView(video_true);
        video_true.setMediaController(mediaController);
        //menampilkan media controller video
            video_true.start();

        String levelKe = Integer.toString(dataQuiz.getNum());
        int Skor =(dataQuiz.getNum() - 90) * 20 + 800  ;
        String skorAkhir = Integer.toString(Skor);

        level.setText(levelKe);
        tScore.setText(skorAkhir);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataQuiz.getNum() == 100){
                    Intent win = new Intent(DifficultActivity.this, WinnerActivity.class);
                    win.putExtra(EXTRA_DIFFICULT, data.get(dataQuiz.getNum() - 91));
                    EasyActivity.score = 0;
                    MediumActivity.passed = 0;
                    HardActivity.passed_hard = 0;
                    passed_diff = 0;
                    startActivity(win);
                }
                else{
                    Intent benar = new Intent(DifficultActivity.this, DifficultActivity.class);
                    benar.putExtra(EXTRA_DIFFICULT, data.get(dataQuiz.getNum() - 90));
                    passed_diff = dataQuiz.getNum()+2;
                    startActivity(benar);
                }
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

        MediaPlayer wrong = MediaPlayer.create(DifficultActivity.this, R.raw.wrong);
        wrong.start();

        videoView.stopPlayback();
        falseDialog = new Dialog(this);
        falseDialog.setContentView(R.layout.pop_up_false);

        TextView close = falseDialog.findViewById(R.id.tv_close_false);
        Button try_again = falseDialog.findViewById(R.id.btn_coba_lagi);
        TextView level = falseDialog.findViewById(R.id.tv_level_false);
        TextView mScore = falseDialog.findViewById(R.id.tv_score_false);
        TextView category = falseDialog.findViewById(R.id.tv_category_false);

        category.setText("Difficult");
        String tLevel = Integer.toString(dataQuiz.getNum());
        int tScore = (dataQuiz.getNum() - 90) * 20 + 800 - 20;
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
                Intent try_again = new Intent(DifficultActivity.this, DifficultActivity.class);
                try_again.putExtra(EXTRA_DIFFICULT, data.get(dataQuiz.getNum() - 91));
                startActivity(try_again);
            }
        });
        falseDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        falseDialog.show();
    }

}
