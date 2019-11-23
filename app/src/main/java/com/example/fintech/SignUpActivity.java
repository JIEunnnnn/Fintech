package com.example.fintech;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.fintech.MainActivity.JSON;

public class SignUpActivity extends AppCompatActivity {
    private final String url ="http://10.3.17.173:9436/user/signup";
    private String result;

    private String id;  //id = phone number
    EditText phone1;
    EditText phone2;
    EditText phone3;
    EditText userpw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        phone1 = findViewById(R.id.userPhone1);
        phone2 = findViewById(R.id.userPhone2);
        phone3 = findViewById(R.id.userPhone3);
        userpw = findViewById(R.id.userPw);

        Button btnSignUp=findViewById(R.id.btnSignup);
        Button btnAuthNum=findViewById(R.id.authNum);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = phone1.getText().toString()+phone2.getText().toString()+phone3.getText().toString();
                Toast.makeText(getApplicationContext(),"환영합니다. 다시 로그인 해주세요.", Toast.LENGTH_LONG).show();

                Intent homeIntent = new Intent(view.getContext(), MainActivity.class);
                startActivity(homeIntent);
            }
        });

        btnAuthNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUsingPhone authUsingPhone = new AuthUsingPhone();
                authUsingPhone.requestPost();
                try{
                    Thread.sleep(300);
                    try {
                        JSONObject resultObject = new JSONObject(result);
                        JSONObject dataHeader = resultObject.getJSONObject("dataHeader");
                        if (dataHeader.getString("successCode").equals("0")){
                            Toast.makeText(getApplicationContext(),"인증되었습니다.", Toast.LENGTH_LONG).show();

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }catch (Exception e){
                        e.printStackTrace();
                }
            }
        });

    }



    class PostSignupClnt {
        OkHttpClient passclnt = new OkHttpClient(); // OK객체 생성

        public void requestPassPost() {

            final RequestBody passbody;

            try {
                JSONObject js = new JSONObject();
                js.put("id", id);
                js.put("pw", userpw.getText().toString());

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
                                Log.d("Thread", "회원가입서버실패 ");
                            }

                            @Override
                            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                result = response.body().string();
                                Log.d("Thread", "회원가입서버성공 " + result);
                            }

                        });

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    class AuthUsingPhone {
        OkHttpClient passclnt = new OkHttpClient(); // OK객체 생성

        public void requestPost() {

            final RequestBody passbody;

            try {
                JSONObject dataHeaderObject = new JSONObject();

                JSONObject dataBodyObject = new JSONObject();
                dataBodyObject.put("vrfyNo","2");
                dataBodyObject.put("MsgCntt","123698");
                dataBodyObject.put("bizGbn","02");
                dataBodyObject.put("telcoTpCode","1");
                dataBodyObject.put("mobiNo1",phone1.getText().toString());
                dataBodyObject.put("mobiNo2",phone2.getText().toString());
                dataBodyObject.put("mobiNo3",phone3.getText().toString());
                dataBodyObject.put("encIdno","2866shinhanial%2FENC%2FjxhtM0akykzdn0%2FZl7n8Pg%3D%3D%0A");


                JSONObject js = new JSONObject();
                js.put("dataHeader", dataHeaderObject);
                js.put("dataBody", dataBodyObject);

                Log.d("JSON", "인증 json: "+js.toString());
                passbody = RequestBody.create(JSON, js.toString());

                final Request passrequest = new Request.Builder()
                        .url("http://10.3.17.61:8081/v1/phoneservices/requestandverifyauthorizationforfree")
                        .addHeader("Content-Type", "application/json;charset=UTF-8")
                        .post(passbody)
                        .build();

                passclnt.newCall(passrequest)
                        .enqueue(new Callback() {
                            @Override
                            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                Log.d("Thread", "휴대전화 인증 실패 ");
                            }

                            @Override
                            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                result = response.body().string();
                                Log.d("Thread", "휴대전화 인증 성공 " + result);
                            }

                        });

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


}
