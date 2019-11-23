package com.example.fintech;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fintech.DataObject.Card;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GivingPassViewAdapter extends RecyclerView.Adapter<GivingPassViewAdapter.CustomViewHolder> {
    private ArrayList<Card> mList;  //들어갈 data List

    //ViewHolder
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView textViewCardName;
        protected TextView textViewCardNo;

        public CustomViewHolder(View view) {
            super(view);
            this.textViewCardName = (TextView) view.findViewById(R.id.textcardName);
            this.textViewCardNo = (TextView) view.findViewById(R.id.textcardNo);

            Button btnGivingPass = (Button) view.findViewById(R.id.buttonGivingPass);
            final Intent intent = new Intent(view.getContext(), QRActivity.class);
            btnGivingPass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //카드 속 '이용권 주기' 버튼 누를 시 Activity


                    v.getContext().startActivity(intent);
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

}