package com.jack.paham.medium;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jack.paham.R;
import com.jack.paham.adapter.MediumAdapter;
import com.jack.paham.model.Medium;

import java.util.ArrayList;

public class MediumLevel extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MediumAdapter mediumAdapter;
    private ArrayList<Medium> dataMedium;
    private TypedArray dataNum, dataQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_level);
        getSupportActionBar().setTitle("Medium Level");
        recyclerView = findViewById(R.id.rv_level);

        loadData();
        mediumAdapter = new MediumAdapter(getApplicationContext(), dataMedium);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        recyclerView.setAdapter(mediumAdapter);
    }

    private void loadData() {
        dataMedium = new ArrayList<>();
        dataNum = getResources().obtainTypedArray(R.array.no_medium);
        dataQuestion = getResources().obtainTypedArray(R.array.soal_medium);
        int[] num = {41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70};
        int[] answer = {1, 3, 3, 2, 1, 2, 2, 1, 3, 1, 2, 3, 1, 2, 3, 2, 2, 1, 2, 3, 1, 3, 3, 1, 2, 2, 3, 1, 3, 3};
        String[] anwerA = getResources().getStringArray(R.array.mediumA);
        String[] anwerB = getResources().getStringArray(R.array.mediumB);
        String[] anwerC = getResources().getStringArray(R.array.mediumC);
        for (int i=0; i<dataNum.length(); i++){
            Medium jMedium = new Medium();
            jMedium.setNum(num[i]);
            jMedium.setImg(dataNum.getResourceId(i, -1));
            jMedium.setQuestion(dataQuestion.getResourceId(i, -1));
            jMedium.setAnswer(answer[i]);
            jMedium.setaChoise(anwerA[i]);
            jMedium.setbChoise(anwerB[i]);
            jMedium.setcChoise(anwerC[i]);
            dataMedium.add(jMedium);
        }
    }
}
