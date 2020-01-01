package com.jack.paham.hard;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jack.paham.R;
import com.jack.paham.adapter.HardAdapter;
import com.jack.paham.model.Hard;

import java.util.ArrayList;

public class HardLevel extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HardAdapter hardAdapter;
    private ArrayList<Hard> dataHard;
    private TypedArray dataNum, dataQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_level);
        getSupportActionBar().setTitle("Hard Level");
        recyclerView = findViewById(R.id.rv_level);

        loadData();
        hardAdapter = new HardAdapter(this, dataHard);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(hardAdapter);
    }

    private void loadData() {
        dataHard = new ArrayList<>();
        int[] numHard = {71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90};
        int[] answetHard = {4, 4, 3, 2, 1, 2, 4, 3, 1, 2, 4, 2, 4, 2, 2, 4, 4, 2, 3, 3};
        dataNum = getResources().obtainTypedArray(R.array.no_hard);
        dataQuestion = getResources().obtainTypedArray(R.array.soal_hard);
        String[] aPilih = getResources().getStringArray(R.array.apilih);
        String[] bPilih = getResources().getStringArray(R.array.bpilih);
        String[] cPilih = getResources().getStringArray(R.array.cpilih);
        String[] dPilih = getResources().getStringArray(R.array.dpilih);
        for (int i=0; i<numHard.length; i++){
            Hard jHard = new Hard();
            jHard.setNum(numHard[i]);
            jHard.setAnswer(answetHard[i]);
            jHard.setaPilih(aPilih[i]);
            jHard.setbPilih(bPilih[i]);
            jHard.setcPilih(cPilih[i]);
            jHard.setdPilih(dPilih[i]);
            jHard.setQuestion(dataQuestion.getResourceId(i, -1));
            jHard.setImg(dataNum.getResourceId(i, -1));
            dataHard.add(jHard);
        }
    }
}

