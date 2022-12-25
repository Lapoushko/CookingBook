package com.example.testjavaprojectpp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    Intent intent2 = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent2);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();;
    }
}