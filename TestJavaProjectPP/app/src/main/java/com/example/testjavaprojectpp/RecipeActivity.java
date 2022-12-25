package com.example.testjavaprojectpp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipeActivity extends AppCompatActivity {

    private TextView mRecipeName;
    private TextView mRecipeIngredients;
    private TextView mRecipeMethodTitle;
    private TextView mRecipe;
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        mRecipeName = (TextView)findViewById(R.id.text_recipe);
        mRecipeIngredients = (TextView)findViewById(R.id.ingredients);
        mRecipeMethodTitle = (TextView)findViewById(R.id.method);
        mRecipe = (TextView)findViewById(R.id.recipe);
        mImage = (ImageView)findViewById(R.id.recipe_img_id);

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("RecipeName");
        String Ingredients = intent.getExtras().getString("RecipeIngredients");
        String MethodTitle = intent.getExtras().getString("RecipeMethodTitle");
        String Recipe = intent.getExtras().getString("Recipe");
        int RecipeImage = intent.getExtras().getInt("Thumbnail");

        mRecipeName.setText(Title);
        mRecipeIngredients.setText(Ingredients);
        mRecipeMethodTitle.setText(MethodTitle);
        mRecipe.setText(Recipe);
        mImage.setImageResource(RecipeImage);
    }
}