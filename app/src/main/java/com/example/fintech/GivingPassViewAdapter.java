package com.example.fintech;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.LayoutInflater;

import com.example.fintech.DataObject.Card;
import com.example.fintech.DataObject.Pass;

import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.fintech.MainActivity.JSON;

public class GivingPassViewAdapter extends RecyclerView.Adapter<GivingPassViewAdapter.CustomViewHolder> {
     private String url = "http://10.3.17.173:9436/pass/give";
     private String result;
     private ArrayList<Card> mList;  //들어갈 data List
     private CustomDialog dialog ;
     private Pass chosenPass;

    //ViewHolder
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView textViewCardName;
        protected TextView textViewCardNo;

        public CustomViewHolder(View view) {
            super(view);
            chosenPass = new Pass();
            this.textViewCardName = (TextView) view.findViewById(R.id.textcardName);
            this.textViewCardNo = (TextView) view.findViewById(R.id.textcardNo);

            Button btnGivingPass = (Button) view.findViewById(R.id.buttonGivingPass);

            btnGivingPass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //카드 속 '이용권 주기' 버튼 누를 시 Activity
                    chosenPass.setCard_num(mList.get(getLayoutPosition()).getCardNo());
                    Log.d("item", "chosenPass. card_numb: "+chosenPass.getCard_num());


                }
            });

        }
    }

    public GivingPassViewAdapter( ArrayList<Card> list){ this.mList=list; }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.frag_card, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GivingPassViewAdapter.CustomViewHolder viewholder, int position) {
        viewholder.textViewCardName.setText(mList.get(position).getCardName());
        viewholder.textViewCardNo.setText(mList.get(position).getCardNo());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }


    public void showDialog(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        View view2 = LayoutInflater.from(view.getContext()).inflate(R.layout.custom_dialog, null);
        builder.setView(view2);

        EditText edittoid = (EditText)view2.findViewById(R.id.edittoid);
        EditText editlimit = (EditText)view2.findViewById(R.id.editlimit);
        EditText editfrompw = (EditText)view2.findViewById(R.id.editfrompw);
        Button okbtn = (Button) view2.findViewById(R.id.okButton);
        Button canclebtn = (Button) view2.findViewById(R.id.cancelButton);

        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        canclebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    class postGivingPassClnt {
        OkHttpClient passclnt = new OkHttpClient(); // OK객체 생성

        public void requestPassPost(String to_id, String limit_price, String pw) {

            final RequestBody passbody;

            try {
                JSONObject js = new JSONObject();
                js.put("to_id", to_id);
                js.put("limit_price", limit_price);
                js.put("pw", pw);
                js.put("card_numb", chosenPass.getCard_num());

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