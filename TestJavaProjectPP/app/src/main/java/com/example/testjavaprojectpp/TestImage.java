package com.example.testjavaprojectpp;

import android.app.Application;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;


public class TestImage extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_image);
        ShapeableImageView imageView = findViewById(R.id.image_view_test);


        Picasso.get()
                .load("https://storage.yandexcloud.net/nouti/инф%20про%205.jpg")
                .fit()
                .into(imageView);
        Picasso.get().invalidate("https://storage.yandexcloud.net/nouti/инф%20про%205.jpg");
//        Picasso.get().load("https://media.geeksforgeeks.org/wp-content/cdn-uploads/logo-new-2.svg").placeholder(R.drawable.borsch).error(R.drawable.borsch).into(imageView);
    }
}

