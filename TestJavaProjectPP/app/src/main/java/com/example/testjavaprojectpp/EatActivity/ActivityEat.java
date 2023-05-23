package com.example.testjavaprojectpp.EatActivity;

import static com.example.testjavaprojectpp.SecondActivity.nameCategory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testjavaprojectpp.spinners.NewRecipeActivity;
import com.example.testjavaprojectpp.R;
import com.example.testjavaprojectpp.sqlitedb.Recipes;
import com.example.testjavaprojectpp.RecyclerViewAdapter;
import com.example.testjavaprojectpp.sqlitedb.DatabaseHelper;
import com.example.testjavaprojectpp.sqlitedb.RecipeModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ActivityEat extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerViewAdapter myAdapter;

    List<Recipes> recipes1;

    SearchView sv;

    public String category = nameCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        LinearLayout constraintLayout = findViewById(R.id.activity_main_2);

        ImageButton back = (ImageButton)findViewById(R.id.backward_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

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
                String[] s = newText.split("[ ,;'.~:/*!?]");
                if (newText.contains(" и ")){
                    s = newText.split(" и ");
                    filterList(s,false);
                }
                else if(newText.contains(" или ")){
                    s = newText.split(" или ");
                    filterList(s,true);
                }
                else{
                    filterList(s,false);
                }

                return false;
            }
        });

        recipes1 = new ArrayList<>();
        System.out.println(category);
        updateRecipes(category);
        CustomComparatorLetters csl = new CustomComparatorLetters();
        Collections.sort(recipes1,csl);
        updateActivity(recipes1);
    }

    public void setFilteredList(List<Recipes> filteredList){
        this.recipes1 = filteredList;
        myAdapter.notifyDataSetChanged();
    }

    private void filterList(String[] s, boolean isOr){
        //s - список ингредиентов, isOr - является ли поиск по "или"
        List<Recipes> filteredList = new ArrayList<>();

        for (Recipes item : recipes1){
            int count = 0;
            for (String str : s){
                if (item.getRecipeIngredients().toLowerCase().contains(str.toLowerCase())){
                    count++;
                    if (count == s.length && !isOr){
                        if (!filteredList.contains(item)){
                            filteredList.add(item);
                        }
                    }
                    else if(isOr){
                        if (!filteredList.contains(item)){
                            filteredList.add(item);
                        }
                    }
                }
                else if (item.getRecipeName().toLowerCase().contains(s[0].toLowerCase())){
                    if (!filteredList.contains(item)){
                        filteredList.add(item);
                    }
                }
            }
        }
        if (filteredList.isEmpty() || s[0].matches("[-+]?\\d+")){
//            Toast.makeText(this, "Нет такого рецепта :(", Toast.LENGTH_SHORT).show();
            updateActivity(new ArrayList<Recipes>());
        }
        else{
            updateActivity(filteredList);
        }
    }

    void updateActivity(List<Recipes> list){
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView_id);
        myAdapter = new RecyclerViewAdapter(this,list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,RecyclerView.VERTICAL,false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(myAdapter);
    }

    void updateRecipes(String category){

        DatabaseHelper databaseHelper = new DatabaseHelper(ActivityEat.this);
        List<RecipeModel> everyone = databaseHelper.getAll();
        if (category != "everyone"){
            everyone = databaseHelper.getEveryone(category);
        }


        //Toast.makeText(SoupActivity.this,everyone.toString(), Toast.LENGTH_SHORT).show();

        for (int i = 0; i < everyone.size();i++){

            recipes1.add(new Recipes(everyone.get(i).getRecipeName(),
                    everyone.get(i).getRecipeIngredients().replace(";","\n"),
                    "Метод",
                    everyone.get(i).getRecipe().replace(";","\n"),everyone.get(i).getImageName()));
        }
    }

    public void clickAddRecipe(View view){
        Intent intent2 = new Intent(ActivityEat.this, NewRecipeActivity.class);
        startActivity(intent2);
    }

    private class CustomComparatorLetters implements Comparator<Recipes>{

        @Override
        public int compare(Recipes recipes, Recipes t1) {
            return recipes.getRecipeName().compareTo(t1.getRecipeName());
        }
    }
}