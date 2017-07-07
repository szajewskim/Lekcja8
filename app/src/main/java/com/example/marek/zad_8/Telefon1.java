package com.example.marek.zad_8;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Telefon1 extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telefon1);
        Button button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TextView textView = (TextView) findViewById(R.id.textView5);
                Context context;
                context = getApplicationContext();
                Intent intent = new Intent(context,MainActivity.class);
                startActivity(intent);

            }
        });



    }
}

