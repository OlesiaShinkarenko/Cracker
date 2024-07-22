package com.example.burl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private SeekBar pin1, pin2,pin3;
    private TextView pin_value_1,pin_value_2,pin_value_3;
    private Button tool1,tool2,tool3, restart;
    private Integer pin_value1 = 0, pin_value2 = 0, pin_value3 = 0;
    private Integer [] right_combination= new Integer[]{6,6, 6};
    private Integer [][] tools_values = {{-3,2,2},{1,3,-1},{-2,-2,1}};
    private Integer [] pin_btn = new Integer[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pin1 = findViewById(R.id.pin1);
        pin2 = findViewById(R.id.pin2);
        pin3 = findViewById(R.id.pin3);
        pin3.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        tool1 = findViewById(R.id.tool1);
        tool2 = findViewById(R.id.tool2);
        tool3 = findViewById(R.id.tool3);

        pin_value_1 = findViewById(R.id.pin_value_1);
        pin_value_2 = findViewById(R.id.pin_value_2);
        pin_value_3 = findViewById(R.id.pin_value_3);

        restart = findViewById(R.id.restart);


        Random r = new Random();
     while (Check()){
         right_combination= new Integer[]{6,6, 6};
         pin_value1= right_combination[0];
         pin_value2 = right_combination[1];
         pin_value3 = right_combination[2];
             int j = 0;
            while (j<3){
                int i = r.nextInt(3);
                pin_btn[j] = i;
                pin_value1 -= tools_values[pin_btn[j]][0];
                pin_value2-=tools_values[pin_btn[j]][1];
                pin_value3-=tools_values[pin_btn[j]][2];
                j++;
            }
        }
        Initialization();
        Check_tool_1();
        Check_tool_2();
        Check_tool_3();
        tool1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    pin_value1 += tools_values[pin_btn[0]][0];
                    pin_value2 += tools_values[pin_btn[0]][1];
                    pin_value3 += tools_values[pin_btn[0]][2];
                    Initialization();
                }
        });
        tool2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    pin_value1 += tools_values[pin_btn[1]][0];
                    pin_value2 += tools_values[pin_btn[1]][1];
                    pin_value3 += tools_values[pin_btn[1]][2];
                    Initialization();
            }
        });

        tool3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    pin_value1 += tools_values[pin_btn[2]][0];
                    pin_value2 += tools_values[pin_btn[2]][1];
                    pin_value3 += tools_values[pin_btn[2]][2];
                    Initialization();
            }
        });
        pin_value_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Check1();
                Check_tool_1();
                Check_tool_2();
                Check_tool_3();
                Check2();
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                MainActivity.this.finish();
            }
        });
    }


    private void Initialization() {
        pin1.setProgress(pin_value1);
        pin2.setProgress(pin_value2);
        pin3.setProgress(pin_value3);
        pin_value_1.setText(pin_value1.toString());
        pin_value_2.setText(pin_value2.toString());
        pin_value_3.setText(pin_value3.toString());
    }

    private boolean Check(){
        if(pin_value1>0 && pin_value1<11 && pin_value2>0 && pin_value2<11&& pin_value3>0 && pin_value3<11){
            return false;
        }
        return true;
    }
    private void Check_tool_1 (){
        if (pin_value1+tools_values[pin_btn[0]][0] > 0 && pin_value1+tools_values[pin_btn[0]][0]<11
                && pin_value2+tools_values[pin_btn[0]][1] > 0 && pin_value2+tools_values[pin_btn[0]][1]<11&&
                pin_value3+tools_values[pin_btn[0]][2] > 0 && pin_value3+tools_values[pin_btn[0]][2]<11
        ){
            tool1.setEnabled(true);
        }else
        {
            tool1.setEnabled(false);
        }
    }
    private void Check_tool_2(){
        if (pin_value1+tools_values[pin_btn[1]][0] > 0 && pin_value1+tools_values[pin_btn[1]][0]<11
                && pin_value2+tools_values[pin_btn[1]][1] > 0 && pin_value2+tools_values[pin_btn[1]][1]<11&&
                pin_value3+tools_values[pin_btn[1]][2] > 0 && pin_value3+tools_values[pin_btn[1]][2]<11
        ){
            tool2.setEnabled(true);
        }else
        {
            tool2.setEnabled(false);
        }
    }
    private void Check_tool_3(){
        if (pin_value1+tools_values[pin_btn[2]][0] > 0 && pin_value1+tools_values[pin_btn[2]][0]<11
                && pin_value2+tools_values[pin_btn[2]][1] > 0 && pin_value2+tools_values[pin_btn[2]][1]<11&&
                pin_value3+tools_values[pin_btn[2]][2] > 0 && pin_value3+tools_values[pin_btn[2]][2]<11
        ){
            tool3.setEnabled(true);
        }else
        {
            tool3.setEnabled(false);
        }
    }
    private void Check2(){
        if(tool1.isEnabled()==false&&tool2.isEnabled()==false&&tool3.isEnabled()==false){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Увы вы не прошли игру.. Хотите попробовать ещё раз?");
            builder.setPositiveButton("Да",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                            MainActivity.this.finish();
                        }
                    });
            builder.setNegativeButton("Нет",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.this.finish();
                        }
                    });
            builder.setCancelable(false);
            builder.create();
            builder.show();
        }
    }
    private void Check1(){
        if (pin_value1 == right_combination[0]&&pin_value2 == right_combination[1]&& pin_value3 == right_combination[2]){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Вы прошли игру! Хотите пройти ее снова?");
            builder.setPositiveButton("Да",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                            MainActivity.this.finish();
                        }
                    });
            builder.setNegativeButton("Нет",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.this.finish();
                        }
                    });
            builder.setCancelable(false);
            builder.create();
            builder.show();
        }

    }

}