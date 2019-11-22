package com.example.fintech;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.http.StatusLine;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String url = "http://10.3.17.173:9436/user";
    // 서버 주소 url

    private static final String TAG = "OKHTTP 테스트";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");





    public static String result  ;
    String MSG = "YES" ;
    // result를 전역변수로 설정해서 서버에서 요청받은 메세지에 ㄸ라 intent 수행하게끔 설정하기

    EditText tel ;
    EditText pw ;
    Button btnLogin ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final HttpConnection2 connectServ = new HttpConnection2();
        final Intent intent = new Intent(this, MenuActivity.class);


        btnLogin = (Button) findViewById(R.id.loginBtn);
        tel = (EditText)findViewById(R.id.login_id);
        pw = (EditText)findViewById(R.id.login_pw);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Integer inTel = Integer.parseInt(""+tel.getText());
                final Integer inPw = Integer.parseInt(""+pw.getText());



                connectServ.requestPost(url, inTel, inPw);
                //  connectServ.stop();


                do {
                    System.out.println("쓰레드메세지dowhile"+result);

                }while(result == null);



                System.out.println("쓰레드메세지조건문"+result);

                if(result.equals(MSG)){
                    startActivity(intent);  // 서버에서 response 응답받으면 다음 화면으로 넘어가도록 처리!

                }else {
                    Toast.makeText(getApplicationContext(), "일치하는 회원정보가 없습니다.", Toast.LENGTH_SHORT).show();


                }








            }
        });

    }

    class HttpConnection2
    {

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
                                Log.d("Thread", "매세지!"+res);

                                result = res.body().string();
                                Log.d("Thread", "메세지222222! "+result);









                            }catch (IOException e){
                                Log.d("Thread","동기식 thread 실패...");
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
