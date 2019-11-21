package com.example.fintech;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String url = "http://localhost:8080/user";
    // 서버 주소 url

    private static final String TAG = "OKHTTP 테스트";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    EditText tel ;
    EditText pw ;
    Button btnLogin ;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.loginBtn);
        tel = (EditText)findViewById(R.id.login_id);
        pw = (EditText)findViewById(R.id.login_pw);

        final HttpConnection connectServ = new HttpConnection();

        final Intent intent = new Intent(this, MenuActivity.class);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Integer inTel = Integer.parseInt(""+tel.getText());
                final Integer inPw = Integer.parseInt(""+pw.getText());

                connectServ.requestPost(url, inTel, inPw);

                 // tartActivity(intent);
            }
        });

    }

    class HttpConnection {

        OkHttpClient clnt  = new OkHttpClient(); // OK객체 생성

        public void requestPost(String url, Integer id, Integer pw ){
            final RequestBody body ;
            try{
                JSONObject js = new JSONObject();
                js.put("id", id);
                js.put("pw", pw);

                body = RequestBody.create(JSON, js.toString());

                Log.d("JSON", "제이쓴변환"+js.toString());

                final Request request = new Request.Builder()
                        .url(url)
                        .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                        .post(body)
                        .build();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Response res =  clnt.newCall(request).execute();
                        }catch (IOException e){
                            e.printStackTrace();
                        }

                    }
                }).start();


            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }
}
