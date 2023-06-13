package com.example.testjavaprojectpp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
        String RecipeImage = intent.getExtras().getString("Thumbnail");

        mRecipeName.setText(Title);
        mRecipeIngredients.setText(Ingredients);
        mRecipeMethodTitle.setText(MethodTitle);

        final SpannableStringBuilder sb = new SpannableStringBuilder(Recipe);

        String rec = Recipe.split("\n")[0];

        final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD); // Span to make text bold
        final StyleSpan iss = new StyleSpan(Typeface.NORMAL); //Span to make text italic
        sb.setSpan(bss, 0, rec.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make first 4 characters Bold
        sb.setSpan(iss, rec.length(), Recipe.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make last 2 characters Italic

        mRecipe.setText(sb);

        Cache saveInCache = new Cache();
        Bitmap bitmap = (Bitmap)Cache.getInstance().retrieveBitmapFromCache(Title);
        if (!RecipeImage.contains("https")){
//            mImage.setImageURI(Uri.parse(RecipeImage));
//            mImage.setImageBitmap(bitmap);
            mImage.setImageBitmap(saveInCache.loadImageBitmap(getApplicationContext(),Title+".jpeg"));
        }
        else {
            Picasso.get()
                    .load(RecipeImage)
                    .error(R.drawable.back_without_int)
                    .into(mImage);
        }
        ImageButton back = (ImageButton)findViewById(R.id.backward_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}