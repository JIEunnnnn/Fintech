package com.example.fintech;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);


        Button btnGivingPass = (Button) findViewById(R.id.givingPassBtn);
        Button btnUsePass = (Button) findViewById(R.id.usePassBtn);



        btnGivingPass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), GivingPassActivity.class);
                startActivity(intent);
            }
        });
        btnUsePass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), UsePassActivity.class);
                startActivity(intent);
            }
        });

    }

}
