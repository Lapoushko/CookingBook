package com.example.testjavaprojectpp.EatActivity;

import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import com.example.testjavaprojectpp.MainActivity;
import com.example.testjavaprojectpp.R;
import com.example.testjavaprojectpp.Recipes;
import com.example.testjavaprojectpp.RecyclerViewAdapter;
import com.example.testjavaprojectpp.SecondActivity;
import com.example.testjavaprojectpp.sqlitedb.DatabaseHelper;
import com.example.testjavaprojectpp.sqlitedb.RecipeModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SoupActivity extends AppCompatActivity {

    RecyclerView myrecyclerView;
    RecyclerViewAdapter myAdapter;

    List<Recipes> recipes1;

    SearchView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //ПОИСК
        sv = findViewById(R.id.search);
        sv.clearFocus();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });

        recipes1 = new ArrayList<>();
        updateRecipes("Суп");
        CustomComparatorLetters csl = new CustomComparatorLetters();
        Collections.sort(recipes1,csl);
        updateActivity(recipes1);
    }

    public void setFilteredList(List<Recipes> filteredList){
        this.recipes1 = filteredList;
        myAdapter.notifyDataSetChanged();
    }

    private void filterList(String s){
        List<Recipes> filteredList = new ArrayList<>();
        for (Recipes item : recipes1){
            if (item.getRecipeIngredients().toLowerCase().contains(s.toLowerCase())){
                filteredList.add(item);
            }
            else if (item.getRecipeName().toLowerCase().contains(s.toLowerCase())){
                filteredList.add(item);
            }
        }
        if (filteredList.isEmpty() || s.matches("[-+]?\\d+")){
            Toast.makeText(this, "Нет такого рецепта :(", Toast.LENGTH_SHORT).show();
            updateActivity(new ArrayList<Recipes>());
        }
        else{
            updateActivity(filteredList);
        }


    }

    void updateActivity(List<Recipes> list){
        myrecyclerView = (RecyclerView)findViewById(R.id.recyclerView_id);

        myAdapter = new RecyclerViewAdapter(this,list);

        myrecyclerView.setLayoutManager(new GridLayoutManager(this,1));

        myrecyclerView.setAdapter(myAdapter);
    }

    void updateRecipes(String category){

        DatabaseHelper databaseHelper = new DatabaseHelper(SoupActivity.this);
        List<RecipeModel> everyone = databaseHelper.getEveryone(category);

        //Toast.makeText(SoupActivity.this,everyone.toString(), Toast.LENGTH_SHORT).show();

        for (int i = 0; i < everyone.size();i++){

            String name= everyone.get(i).getImageName();
            int id = getResources().getIdentifier(name, "drawable", getPackageName());

            recipes1.add(new Recipes(everyone.get(i).getRecipeName(),
                    everyone.get(i).getRecipeIngredients().replace(";","\n"),
                    "Метод",
                    everyone.get(i).getRecipe(),id));
        }
    }

    private class CustomComparatorLetters implements Comparator<Recipes>{

        @Override
        public int compare(Recipes recipes, Recipes t1) {
            return recipes.getRecipeName().compareTo(t1.getRecipeName());
        }
    }
}