package com.example.fintech;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.example.fintech.DataObject.Card;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.fintech.MainActivity.JSON;

public class GivingPassActivity extends AppCompatActivity {

    public static final String url = "http://10.3.17.173:9436/card/info";
    public static String result;
    private ArrayList<Card> mList;  //들어갈 data List
    private GivingPassViewAdapter mAdapter;
    private int count = -1;
    CardData carddata ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.give_pass);

        String id = MenuActivity.id;
        mList = new ArrayList<>();

        final GivingPassClnt passclnt = new GivingPassClnt();

        passclnt.requestPassPost(url, Integer.parseInt(id));

        try{
            Thread.sleep(300);
            try{
                JSONArray jsonCardArray = new JSONArray(result);
                Log.d("JSON",  result);
                for(int i=0;i<jsonCardArray.length();i++)
                {
                    JSONObject card = (JSONObject) jsonCardArray.get(i);
                    Log.d("JSON",  card.toString());
                    String cardname = card.getString("card_name");
                    String cardnumb = card.getString("card_number");
                    Log.d("JSON",  cardname + cardnumb);
                    mList.add(new Card(cardname, cardnumb));
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

        mAdapter = new GivingPassViewAdapter(mList);
        pRecyclerView.setAdapter(mAdapter);
    }


    class GivingPassClnt {
        OkHttpClient passclnt = new OkHttpClient(); // OK객체 생성

        public void requestPassPost(String url, Integer id) {

            final RequestBody passbody;

            try {
                JSONObject js = new JSONObject();
                js.put("id", id);

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

    public class CardData{

        String card_name ;
        String card_number ;


        public String  getname() {
            return card_name ;
        }

        public void setname(String card_name) {
            this.card_name = card_name;
        }

        public String getNNum() {
            return card_number;
        }

        public void setNum(String card_number) {
            this.card_number= card_number;
        }


    }






}
