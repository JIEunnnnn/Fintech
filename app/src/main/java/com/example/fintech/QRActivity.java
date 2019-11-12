package com.example.fintech;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class QRActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        IntentIntegrator scan = new IntentIntegrator(this);
        scan.setBeepEnabled(true); // 비코드인식시 삐~~~~
        scan.setOrientationLocked(true);
        scan.initiateScan(); // 스캔하기


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        // 결과값을 받아오는 함수
        IntentResult res = IntentIntegrator.parseActivityResult(requestCode,resultCode, data);
        if(res != null){
            if(res.getContents() == null){
                Toast.makeText(this, "Cancelled",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this,"Scanned : "+res.getContents(), Toast.LENGTH_LONG).show();
                // 결과값 토스트메세지로 나타내기 => 결제창넘어가기로 변경해야함!
                // getContents() == 스캔하여 얻은 데이터 의미
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }


    }
}
