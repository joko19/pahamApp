package com.jack.paham.difficult;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jack.paham.R;
import com.jack.paham.adapter.DifficultAdapter;
import com.jack.paham.model.Difficult;

import java.util.ArrayList;

public class DifficultLevel extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DifficultAdapter difficultAdapter;
    private ArrayList<Difficult> dataDifficult;
    private TypedArray dataNo, dataQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_level);
        getSupportActionBar().setTitle("Difficult Level");
        recyclerView = findViewById(R.id.rv_level);

        loadData();
        difficultAdapter = new DifficultAdapter(getApplicationContext(), dataDifficult);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        recyclerView.setAdapter(difficultAdapter);
    }

    private void loadData() {
        dataDifficult = new ArrayList<>();
        dataNo = getResources().obtainTypedArray(R.array.no_diff);
        String[] adiff = getResources().getStringArray(R.array.aDiff);
        String[] bdiff = getResources().getStringArray(R.array.bDiff);
        String[] cdiff = getResources().getStringArray(R.array.cDiff);
        String[] ddiff = getResources().getStringArray(R.array.dDiff);
        int[] num = {91, 92, 93, 94, 95, 96, 97, 98, 99, 100};
        int[] answer = {4, 2, 3, 3, 4, 3, 2, 3, 4, 4};
        for (int i=0; i<num.length; i++){
            Difficult jDifficult = new Difficult();
            jDifficult.setNum(num[i]);
            jDifficult.setAnswer(answer[i]);
            jDifficult.setAdiff(adiff[i]);
            jDifficult.setBdiff(bdiff[i]);
            jDifficult.setCdiff(cdiff[i]);
            jDifficult.setDdiff(ddiff[i]);
            jDifficult.setImg(dataNo.getResourceId(i, -1));
            if (i == 0){
                jDifficult.setQuestion(R.raw.soal1);
                jDifficult.setVidAnswer(R.raw.jawaban1);
            }if (i == 1){
                jDifficult.setQuestion(R.raw.soal2);
                jDifficult.setVidAnswer(R.raw.jawaban2);
            }if (i == 2){
                jDifficult.setQuestion(R.raw.soal3);
                jDifficult.setVidAnswer(R.raw.jawaban3);
            }if (i == 3){
                jDifficult.setQuestion(R.raw.soal4);
                jDifficult.setVidAnswer(R.raw.jawaban4);
            }if (i == 4){
                jDifficult.setQuestion(R.raw.soal5);
                jDifficult.setVidAnswer(R.raw.jawaban5);
            }if (i == 5){
                jDifficult.setQuestion(R.raw.soal6);
                jDifficult.setVidAnswer(R.raw.jawaban6);
            }if (i == 6){
                jDifficult.setQuestion(R.raw.soal7);
                jDifficult.setVidAnswer(R.raw.jawaban7);
            }if (i == 7){
                jDifficult.setQuestion(R.raw.soal8);
                jDifficult.setVidAnswer(R.raw.jawaban8);
            }if (i == 8){
                jDifficult.setQuestion(R.raw.soal9);
                jDifficult.setVidAnswer(R.raw.jawaban9);
            }if (i == 9){
                jDifficult.setQuestion(R.raw.soal10);
                jDifficult.setVidAnswer(R.raw.jawaban10);
            }
            dataDifficult.add(jDifficult);
        }
    }

}
