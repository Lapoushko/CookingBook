package com.example.testjavaprojectpp;

import android.app.Application;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

//    final int MENU_ANIM = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_one);

        Animation anim = AnimationUtils.loadAnimation(this,R.anim.anim);
        ImageView btn = (ImageView) findViewById(R.id.image_view);
        btn.startAnimation(anim);

        ConstraintLayout constraintLayout = findViewById(R.id.main_layout);

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

    public void clickPrivacyPolicy(View view){
        Intent openURL = new Intent(android.content.Intent.ACTION_VIEW);
        openURL.setData(Uri.parse("https://github.com/Lapoushko/CookingBook/blob/main/TestJavaProjectPP/Privacy%20policy.txt"));
        startActivity(openURL);
    }
}