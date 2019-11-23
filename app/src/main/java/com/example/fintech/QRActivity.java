package com.example.fintech;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.fintech.MainActivity.JSON;

public class QRActivity extends AppCompatActivity {

    public static final String url = "http://10.3.17.173:9436/pass/use";
    public String qrContents;
    public static String result;
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
                qrContents = res.getContents();

                Toast.makeText(this,"Scanned : "+res.getContents(), Toast.LENGTH_LONG).show();
                // 결과값 토스트메세지로 나타내기 => 결제창넘어가기로 변경해야함!
                // getContents() == 스캔하여 얻은 데이터 의미
                Intent intent =new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);

            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }


    }

    class postQRcode {
        OkHttpClient passclnt = new OkHttpClient(); // OK객체 생성

        public void requestPassPost(String url, Integer id) {

            final RequestBody passbody;

            try {
                JSONObject js = new JSONObject();
//                js.put("pass", );

                passbody = RequestBody.create(JSON, js.toString());

                final Request passrequest = new Request.Builder()
                        .url(url)
                        .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                        .post(passbody)
                        .build();

                passclnt.newCall(passrequest)
                        .enqueue(new Callback() {
                            @Override
                            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                Log.d("Thread", "이용권주기서버실패 ");

                            }

                            @Override
                            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                result = response.body().string();
                                Log.d("Thread", "이용권주기서버성공 " + result);

                                // MAP => frag_pass로 전달해야지

                            }

                        });

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
