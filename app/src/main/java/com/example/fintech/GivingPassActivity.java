package com.example.fintech;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.fintech.DataObject.Card;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.OkHttpClient;

public class GivingPassActivity extends AppCompatActivity {


    private ArrayList<Card> mArrayList;
    private GivingPassViewAdapter mAdapter;
    private int count = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.give_pass);

        RecyclerView pRecyclerView = findViewById(R.id.recycle_pass);
        LinearLayoutManager pLinearLayoutManager = new LinearLayoutManager(this);
        pRecyclerView.setLayoutManager(pLinearLayoutManager);

        mArrayList = new ArrayList<>();

        // 서버에 전송받은 데이터 ㅇㅇ 받기
        mArrayList.add(new Card("Deep Dream(체크)[딥 드림] 신한은행", "3569-****-****-2745"));
        mArrayList.add(new Card("Simple Platinum", "23779-****-****-001"));
        mAdapter = new GivingPassViewAdapter(mArrayList);
        pRecyclerView.setAdapter(mAdapter);


    }



    class CardPlus {

        OkHttpClient cardclnt = new OkHttpClient();


    }
}
