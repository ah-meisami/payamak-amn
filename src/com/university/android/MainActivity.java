package com.university.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        Button layout_main_btn1 = (Button) findViewById(R.id.layout_main_btn1);
        Button layout_main_btn2 = (Button) findViewById(R.id.layout_main_btn2);
        Button layout_main_btn3 = (Button) findViewById(R.id.layout_main_btn3);
        Button layout_main_btn4 = (Button) findViewById(R.id.layout_main_btn4);
        Button layout_main_btn5 = (Button) findViewById(R.id.layout_main_btn5);

        layout_main_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intencion = new Intent(view.getContext(), PublicKeyActivity.class);
                startActivity(intencion);
            }
        });

        layout_main_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intencion = new Intent(view.getContext(), SendActivity.class);
                startActivity(intencion);
            }
        });

        layout_main_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intencion = new Intent(view.getContext(), InboxActivity.class);
                startActivity(intencion);
            }
        });

        layout_main_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intencion = new Intent(view.getContext(), SentActivity.class);
                startActivity(intencion);
            }
        });

        layout_main_btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intencion = new Intent(view.getContext(), HelpActivity.class);
                startActivity(intencion);
            }
        });
    }
}



