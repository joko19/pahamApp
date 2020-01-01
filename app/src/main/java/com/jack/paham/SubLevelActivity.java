package com.jack.paham;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jack.paham.adapter.CategoryAdapter;
import com.jack.paham.model.Category;

import java.util.ArrayList;

public class SubLevelActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private ArrayList<Category> categoryArrayList;
    private TypedArray dataCategory, dataQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_level);
        getSupportActionBar().setTitle("Easy Level");
        recyclerView = findViewById(R.id.rv_level);

        loadData();
        categoryAdapter = new CategoryAdapter(getApplicationContext(), categoryArrayList);

        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));
        recyclerView.setAdapter(categoryAdapter);

//        if (categoryArrayList.)
    }


    private void loadData() {
        categoryArrayList = new ArrayList<>();
        dataCategory = getResources().obtainTypedArray(R.array.data_level_easy);
        dataQuestion = getResources().obtainTypedArray(R.array.data_question_easy);
        int[] num = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40};
        int[] answer = {1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1};
        for (int i = 0; i < dataCategory.length(); i++){
            Category jCategory = new Category();
            jCategory.setImg(dataCategory.getResourceId(i, -1));
            jCategory.setNum(num[i]);
            jCategory.setAnswer(answer[i]);
            jCategory.setQuestion(dataQuestion.getResourceId(i, -1));
            categoryArrayList.add(jCategory);
        }
    }

}