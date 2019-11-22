package com.example.fintech;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GivingPassActivity extends AppCompatActivity {

    ImageButton cardPlus ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.give_pass);

        cardPlus = (ImageButton)findViewById(R.id.cardplus);
        Intent intet = new Intent(this, FragCardActivity.class  );

        cardPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });



    }
}
