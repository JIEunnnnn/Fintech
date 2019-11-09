package com.example.fintech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnQR = (Button) findViewById(R.id.buttonQR);
        final Intent intent = new Intent(this, QRActivity.class);

        btnQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //새로운 액티비시 생성하여 qr코드 인식하는 화면 및  인식된 qr코드를 읽는방법 ㅇㅇ



            }
        });
    }
}
