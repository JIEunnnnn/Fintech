package com.example.fintech;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.http.StatusLine;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String url = "http://10.3.17.173:9436/user";
    // 서버 주소 url

    private static final String TAG = "OKHTTP 테스트";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static String result;
    String MSG = "YES" ;
    // result를 전역변수로 설정해서 서버에서 요청받은 메세지에 ㄸ라 intent 수행하게끔 설정하기

    EditText tel ;
    EditText pw ;
    Button btnLogin ;
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final HttpConnection2 connectServ = new HttpConnection2();
        final Intent intent = new Intent(this, MenuActivity.class);

        btnSignup = findViewById(R.id.btnsignUp);
        btnLogin = (Button) findViewById(R.id.loginBtn);
        tel = (EditText)findViewById(R.id.login_id);
        pw = (EditText)findViewById(R.id.login_pw);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String inTel = tel.getText().toString();
                final String inPw = pw.getText().toString();

                try{
                 connectServ.requestPost(url, inTel, inPw);

                 Thread.sleep(100);

                    if(result.equals(MSG)){
                        intent.putExtra("id",tel.getText().toString());
                        intent.putExtra("pw", tel.getText().toString());
                        startActivity(intent);  // 서버에서 response 응답받으면 다음 화면으로 넘어가도록 처리!
                        finish();

                    }else {
                        Toast.makeText(getApplicationContext(), "일치하는 회원정보가 없습니다.", Toast.LENGTH_SHORT).show();

                    }

                }
                catch (Exception e){
                    e.printStackTrace();
                    Log.d("Thread","온클릭메세지실패");

                }


            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(view.getContext(), SignUpActivity.class);
                startActivity(intent2);
            }
        });
    }


    class HttpConnection2
    {

        OkHttpClient clnt  = new OkHttpClient(); // OK객체 생성


        public void requestPost(String url, String id, String pw ){

            final RequestBody body ;
            try{
                JSONObject js = new JSONObject();
                js.put("id", id);
                js.put("pw", pw);

                body = RequestBody.create(JSON, js.toString());

                Log.d("JSON", "제이쓴변환메세지"+js.toString());

                final Request request = new Request.Builder()
                        .url(url)
                        .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                        .post(body)
                        .build();


                 clnt.newCall(request)
                         .enqueue(new Callback() {
                             @Override
                             public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                 Log.d("Thread", "콜백메세지222222실패!!!!!!!!!! ");
                             }

                             @Override
                             public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                 result = response.body().string();
                                 Log.d("Thread", "메세지222222! "+result);
                             }
                         });

                 Log.d("Thread", "콜백매세지!");


            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
