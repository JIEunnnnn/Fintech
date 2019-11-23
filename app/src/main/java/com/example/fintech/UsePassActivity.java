package com.example.fintech;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fintech.DataObject.Pass;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.fintech.MainActivity.JSON;

public class UsePassActivity  extends AppCompatActivity {
    public static final String url = "http://10.3.17.173:9436/pass/info";
    public static String result;

    private ArrayList<Pass> mList;
    private UsePassViewAdapter mAdapter;
    private int count = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.use_pass);

        mList = new ArrayList<>();
        final UsePassClnt passclnt = new UsePassClnt();

        passclnt.requestPassPost(url, Integer.parseInt(MenuActivity.id));
        try{
            Thread.sleep(500);
            try{
                Log.d("JSON",  result);
                JSONArray jsonPassArray = new JSONArray(result);
                for(int i=0;i<jsonPassArray.length();i++)
                {
                    JSONObject pass = (JSONObject) jsonPassArray.get(i);
                    Log.d("JSON",  pass.toString());
                    String cardname = pass.getString("from_id");
//                    String cardnumb = pass.getString("card_number");
                    Log.d("JSON",  i+"번째 Pass "+ pass.getString("from_id")+" to "+pass.getString("to_id"));
                    mList.add(new Pass(pass.getString("from_id"), pass.getString("limit_price")));
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        //About RecycleView
        RecyclerView pRecyclerView = findViewById(R.id.recycle_pass);
        LinearLayoutManager pLinearLayoutManager = new LinearLayoutManager(this);
        pRecyclerView.setLayoutManager(pLinearLayoutManager);

        mList.add(new Pass("hyovin", "2019-11-29"));
        mList.add(new Pass("hyovin", "2019-11-30"));
        mAdapter = new UsePassViewAdapter(mList);
        pRecyclerView.setAdapter(mAdapter);
    }

    class UsePassClnt {
        OkHttpClient passclnt = new OkHttpClient(); // OK객체 생성

        public void requestPassPost(String url, Integer id) {

            final RequestBody passbody;

            try {
                JSONObject js = new JSONObject();
                js.put("id", id);
                js.put("pw", MenuActivity.pw);

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
                                Log.d("Thread", "이용권받기서버실패 ");

                            }

                            @Override
                            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                result = response.body().string();
                                Log.d("Thread", "이용권받기서버성공 " + result);

                            }

                        });

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}