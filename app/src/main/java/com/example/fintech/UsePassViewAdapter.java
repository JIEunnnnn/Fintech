package com.example.fintech;

import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fintech.DataObject.Pass;

import org.jetbrains.annotations.NotNull;
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

public class UsePassViewAdapter extends RecyclerView.Adapter<UsePassViewAdapter.CustomViewHolder> {
    private ArrayList<Pass> mList;  //들어갈 data List
    private Pass chosenPass;
    Button btnQR;
    //ViewHolder
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView textViewFromID;
        protected TextView textViewTime;

        public CustomViewHolder(View view) {
            super(view);
            this.textViewFromID = (TextView) view.findViewById(R.id.from_idInpass);
            this.textViewTime = (TextView) view.findViewById(R.id.timeInpass);

            btnQR = (Button) view.findViewById(R.id.buttonQR);
            final Intent intent = new Intent(view.getContext(), QRActivity.class);
            btnQR.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //새로운 액티비시 생성하여 qr코드 인식하는 화면 및  인식된 qr코드를 읽는방법 ㅇㅇ
                    intent.putExtra("pass_id", mList.get(getLayoutPosition()).getPass_id());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    public UsePassViewAdapter(ArrayList<Pass> list) {
        this.mList = list;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.frag_pass, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

//        final Intent intent = new Intent(view.getContext(), QRActivity.class);
//        btnQR.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //새로운 액티비시 생성하여 qr코드 인식하는 화면 및  인식된 qr코드를 읽는방법 ㅇㅇ
//
//                v.getContext().startActivity(intent);
//            }
//        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {
//        viewholder.textViewFromID.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
//        viewholder.textViewTime.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
//
//        viewholder.textViewFromID.setGravity(Gravity.CENTER);
//        viewholder.textViewTime.setGravity(Gravity.CENTER);

        viewholder.textViewFromID.setText(mList.get(position).getFrom_id());
        viewholder.textViewTime.setText(mList.get(position).getLimit_price());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
    
}
