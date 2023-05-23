package com.example.testjavaprojectpp.spinners;
import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.testjavaprojectpp.R;
import com.example.testjavaprojectpp.sqlitedb.DatabaseHelper;
import com.example.testjavaprojectpp.sqlitedb.RecipeModel;
import com.google.android.material.button.MaterialButton;

import java.util.concurrent.TimeUnit;
public class NewRecipeActivity extends AppCompatActivity {

    Spinner spinner;
    String[] spinnerValue = {
            "Горячее",
            "Суп",
            "Соус",
            "Салаты",
            "Десерт",
            "Напитки",
            "Завтрак",
            "Закуски",
            "Напитки"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        ImageButton back = (ImageButton)findViewById(R.id.backward_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        spinner = (Spinner)findViewById(R.id.spinner);
        SpinnerAdapter adapter = new SpinnerAdapter(NewRecipeActivity.this, android.R.layout.simple_list_item_1);
        adapter.addAll(spinnerValue);
        adapter.add("Выберите категорию блюда");
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getCount());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stubФ
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        MaterialButton saveBtn = (MaterialButton)findViewById(R.id.save_recipe_button);
        EditText nameRecipe = (EditText)findViewById(R.id.plaintext_name_recipe);
        EditText listIngr = (EditText)findViewById(R.id.plaintext_listingr);
        EditText method = (EditText)findViewById(R.id.plaintext_method);
        DatabaseHelper db = new DatabaseHelper(NewRecipeActivity.this);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String listMessage = listIngr.getText().toString();
                Toast.makeText(NewRecipeActivity.this, listMessage, Toast.LENGTH_SHORT).show();
                String methodMessage = method.getText().toString();
                Toast.makeText(NewRecipeActivity.this, methodMessage, Toast.LENGTH_SHORT).show();
                Toast.makeText(NewRecipeActivity.this, spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                RecipeModel recipeModel = new RecipeModel(db.getLastIdFromMyTable()+1,nameRecipe.toString(),
                        listIngr.toString(),
                        method.toString(),
                        spinner.getSelectedItem().toString(),
                        getResources().getDrawable(R.drawable.back_without_int).toString());
                db.addOne(recipeModel);
            }
        });


    }
}