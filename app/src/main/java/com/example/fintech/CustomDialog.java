package com.example.fintech;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CustomDialog extends Dialog {
    private Context customcontext ;
    EditText edittoid, editlimit, editfrompw ;
    Button okbtn, canclebtn ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);


        edittoid = (EditText)findViewById(R.id.edittoid);
        editlimit = (EditText)findViewById(R.id.editlimit);
        editfrompw = (EditText)findViewById(R.id.editfrompw);
        okbtn = (Button) findViewById(R.id.okButton);
        canclebtn = (Button)findViewById(R.id.cancelButton);

        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        canclebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public  CustomDialog(@NonNull Context context){
        super(context);
        customcontext = context;




    }


}
