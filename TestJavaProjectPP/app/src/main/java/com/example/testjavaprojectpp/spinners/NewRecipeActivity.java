package com.example.testjavaprojectpp.spinners;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.LruCache;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testjavaprojectpp.R;
import com.example.testjavaprojectpp.Cache;
import com.example.testjavaprojectpp.sqlitedb.DatabaseHelper;
import com.example.testjavaprojectpp.sqlitedb.RecipeModel;
import com.google.android.material.button.MaterialButton;

public class NewRecipeActivity extends AppCompatActivity {
    ImageButton imageButton;
    Spinner spinner;
    private LruCache<String, Bitmap> mMemoryCache;
    Uri uri;
    boolean isChangedImage = false;

    String[] spinnerValue = {
            "Горячее",
            "Суп",
            "Соус",
            "Салаты",
            "Десерт",
            "Напитки",
            "Завтрак",
            "Закуски",
            "Пицца"
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
        imageButton = (ImageButton)findViewById(R.id.recipe_image_add_button);
        Cache saveInCache = new Cache();
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nameRecipe.getText().toString() != "" && listIngr.getText().toString() != "" && method.getText().toString() != "" && spinner.getSelectedItem().toString() != "" && isChangedImage){
                    int id = db.getLastIdFromMyTable()+1;
                    String nameRecipeMessage = nameRecipe.getText().toString();
                    String ingrMessage = listIngr.getText().toString();
                    String methodMessage = method.getText().toString();
                    String category = spinner.getSelectedItem().toString();
                    System.out.println(db.getLastIdFromMyTable());

                        RecipeModel recipeModel = new RecipeModel(id,
                                nameRecipeMessage,
                                ingrMessage,
                                methodMessage,
                                category,
                                uri.toString());
                        db.addOne(recipeModel);

                        Bitmap bitmap = ((BitmapDrawable)imageButton.getDrawable()).getBitmap();
        //                Cache.getInstance().getLru().put(nameRecipeMessage, bitmap);
                        Cache.getInstance().saveImage(getApplicationContext(),bitmap,nameRecipeMessage+".jpeg");

                }else{
                    onBackPressed();
                }
            }
        });
    }
    void imageChooser() {

        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), 200);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == 200) {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    isChangedImage = true;
                    imageButton.setImageURI(selectedImageUri);
                    uri = selectedImageUri;
                }
            }
        }
    }


}