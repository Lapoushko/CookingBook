package com.example.testjavaprojectpp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.testjavaprojectpp.EatActivity.ActivityEat;

public class SecondActivity extends AppCompatActivity {

    public static String nameCategory ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        Button btn_add = findViewById(R.id.btnTestBd);
//        Button btn_add_read = findViewById(R.id.btnTestRead);
//
//        btn_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                RecipeModel recipeModel = new RecipeModel(-1,"Куринный суп","Куринная грудка","Пожарь","Суп","banan_oladii.jpg");
//                Toast.makeText(SecondActivity.this, recipeModel.toString(), Toast.LENGTH_SHORT).show();
//
//                DatabaseHelper databaseHelper = new DatabaseHelper(SecondActivity.this);
//
//                boolean success = databaseHelper.addOne(recipeModel);
//
//                Toast.makeText(SecondActivity.this,"Success= " + success, Toast.LENGTH_SHORT).show();
//            }
//        });
//        btn_add_read.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatabaseHelper databaseHelper = new DatabaseHelper(SecondActivity.this);
//                List<RecipeModel> everyone = databaseHelper.getEveryone("Суп");
//
//                Toast.makeText(SecondActivity.this,everyone.get(0).getRecipeName(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public void clickGarnish(View view){
        Intent intent2 = new Intent(SecondActivity.this, ActivityEat.class);
        nameCategory = "Горячее";
        startActivity(intent2);
    }

    public void clickSoups(View view){
        Intent intent2 = new Intent(SecondActivity.this, ActivityEat.class);
        nameCategory = "Суп";
        startActivity(intent2);
    }
    public void clickSalads(View view){
        Intent intent2 = new Intent(SecondActivity.this, ActivityEat.class);
        nameCategory = "Салаты";
        startActivity(intent2);
    }
    public void clickSnacks(View view){
        Intent intent2 = new Intent(SecondActivity.this, ActivityEat.class);
        nameCategory = "Закуски";
        startActivity(intent2);
    }
    public void clickSauce(View view){
        Intent intent2 = new Intent(SecondActivity.this, ActivityEat.class);
        nameCategory = "Соус";
        startActivity(intent2);
    }
    public void clickPizza(View view){
        Intent intent2 = new Intent(SecondActivity.this, ActivityEat.class);
        nameCategory = "Пицца";
        startActivity(intent2);
    }
    public void clickDrinks(View view){
        Intent intent2 = new Intent(SecondActivity.this, ActivityEat.class);
        nameCategory = "Напитки";
        startActivity(intent2);
    }
    public void clickDesert(View view){
        Intent intent2 = new Intent(SecondActivity.this, ActivityEat.class);
        nameCategory = "Десерт";
        startActivity(intent2);
    }
    public void clickBreakfast(View view){
        Intent intent2 = new Intent(SecondActivity.this, ActivityEat.class);
        nameCategory = "Завтрак";
        startActivity(intent2);
    }
    @Override
    public void onBackPressed() {
    }
}