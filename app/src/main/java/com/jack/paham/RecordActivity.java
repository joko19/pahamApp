package com.jack.paham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.jack.paham.adapter.RecordAdapter;
import com.jack.paham.model.Record;
import com.jack.paham.model.RecordDB;
import com.jack.paham.model.Score;

import java.util.ArrayList;

public class RecordActivity extends AppCompatActivity {

    private RecordDB recordDB;
    private RecyclerView recyclerView;
    private ArrayList<Record> records, result;
    private Score dataConvert;
    private ArrayList<Score> convert, highest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        getDataRecord();
        convertScore();
        getHighest();
        convertResult();
        recyclerView = findViewById(R.id.rv_record);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        RecordAdapter recordAdapter = new RecordAdapter(getApplicationContext(), result);
        recyclerView.setAdapter(recordAdapter);
        getSupportActionBar().setTitle("Perolehan Skor Tertinggi");
    }

    private void convertResult() {
        result = new ArrayList<>();
        if (records.size() == 0){
            Record tResult = new Record();
            tResult.setName("Belum Ada Record");
            tResult.setAddress("Silahkan Bermain");
            result.add(tResult);
        }
        else if (records.size() > 10){
            for (int r = 0; r<10; r++){
                Record tResult = new Record();
                tResult.setName(convert.get(r).getName());
                tResult.setAddress(convert.get(r).getAddress());
                String sResult = Integer.toString(convert.get(r).getNilai());
                tResult.setScore(sResult);
                tResult.setNum(Integer.toString(r+1));
                result.add(tResult);
            }
        }
        else {
            for (int r = 0; r<records.size(); r++){
            Record tResult = new Record();
            tResult.setName(convert.get(r).getName());
            tResult.setAddress(convert.get(r).getAddress());
            String sResult = Integer.toString(convert.get(r).getNilai());
            tResult.setScore(sResult);
            tResult.setNum(Integer.toString(r+1));
            result.add(tResult);
        }

        }
    }

    private void convertScore() {
        //agar nilainya menjadi int
        convert = new ArrayList<>();
        for (int c = 0; c < records.size(); c++){
            dataConvert = new Score();
            dataConvert.setName(records.get(c).getName());
            dataConvert.setAddress(records.get(c).getAddress());
            int tNilai = Integer.parseInt(records.get(c).getScore());
            dataConvert.setNilai(tNilai);
            convert.add(dataConvert);
        }
    }

    private void getHighest() {
        //mengurutkan berdasarkan skor tertinggi
        highest = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            Score tHigh = new Score();
            for (int j = 0; j < records.size(); j++) {
                if (convert.get(j).getNilai() < convert.get(i).getNilai()) {
                    int tempVar = convert.get(j).getNilai();
                    int sTempVar = convert.get(i).getNilai();
                    convert.get(j).setNilai(sTempVar);
                    convert.get(i).setNilai(tempVar);

                    String tempName = convert.get(j).getName();
                    convert.get(j).setName(convert.get(i).getName());
                    convert.get(i).setName(tempName);

                    String tempAddr = convert.get(j).getAddress();
                    convert.get(j).setAddress(convert.get(i).getAddress());
                    convert.get(i).setAddress(tempAddr);
                }
            }
        }
    }

    private void getDataRecord() {
        recordDB = new RecordDB(getApplicationContext());
        SQLiteDatabase readData = recordDB.getReadableDatabase();
        Cursor cursor = readData.rawQuery("SELECT * FROM " + RecordDB.MyColums.tableName + " ORDER BY " + RecordDB.MyColums.score + " DESC", null);
        cursor.moveToFirst();
        records = new ArrayList<>();
        for (int i = 0; i<cursor.getCount(); i++){
            cursor.moveToPosition(i);
            Record data = new Record();
            data.setName(cursor.getString(1));
            data.setAddress(cursor.getString(2));
            data.setScore(cursor.getString(3));
            records.add(data);
        }
        readData.close();
    }

    @Override
    public void onBackPressed() {
        Intent move = new Intent(RecordActivity.this, MainActivity.class);
        startActivity(move);
//        super.onBackPressed();
    }
}
