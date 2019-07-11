package com.example.db_task;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/*
Author : mohamed khidr abbas
 */

public class MainActivity extends AppCompatActivity {
    private Button insertButton;
    private Button showButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insertButton = (Button) findViewById(R.id.btnInsert);
        showButton = (Button) findViewById(R.id.btnShow);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInsertActivity();
            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShowDataActivity();
            }
        });

    }

    public void openInsertActivity() {
        Intent intent = new Intent(MainActivity.this, InsertActivity.class);
        startActivity(intent);
    }

    public void openShowDataActivity() {
        Intent intent = new Intent(MainActivity.this, ShowDataActivity.class);
        startActivity(intent);
    }


}
