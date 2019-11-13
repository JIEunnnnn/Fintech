package com.example.fintech;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fintech.Pass.Pass;

import java.util.ArrayList;

public class UsePassActivity  extends AppCompatActivity {

    private ArrayList<Pass> mArrayList;
    private UsePassViewAdapter mAdapter;
    private int count = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.use_pass);

        RecyclerView pRecyclerView = findViewById(R.id.recycle_pass);
        LinearLayoutManager pLinearLayoutManager = new LinearLayoutManager(this);
        pRecyclerView.setLayoutManager(pLinearLayoutManager);

        mArrayList = new ArrayList<>();

        mArrayList.add(new Pass("hyovin", "2019-11-29"));
        mArrayList.add(new Pass("hyovin", "2019-11-30"));
        mAdapter = new UsePassViewAdapter(mArrayList);
        pRecyclerView.setAdapter(mAdapter);
    }
}