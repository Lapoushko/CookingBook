package com.example.testjavaprojectpp.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String RECIPE = "RECIPE";
    public static final String RECIPES_TABLE = "RECIPE_TABLE";
    public static final String ID = "ID";
    public static final String RECIPE_NAME = "RECIPE_NAME";
    public static final String RECIPE_INGREDIENTS = "RECIPE_INGREDIENTS";
    public static final String CATEGORY = "CATEGORY";
    public static final String IMAGE_NAME = "IMAGE_NAME";
    private static String DB_NAME = "RecipeDB.db";



    public DatabaseHelper(@Nullable Context context) {
        super(context, "RecipeDB.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "CREATE TABLE " + RECIPES_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + RECIPE_NAME + " TEXT, " + RECIPE_INGREDIENTS + " TEXT, " +
                RECIPE + " TEXT, " + CATEGORY + " TEXT, " + IMAGE_NAME + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(RecipeModel recipeModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(RECIPE_NAME,recipeModel.getRecipeName());
        cv.put(RECIPE_INGREDIENTS,recipeModel.getRecipeIngredients());
        cv.put(RECIPE,recipeModel.getRecipe());
        cv.put(CATEGORY,recipeModel.getCategory());
        cv.put(IMAGE_NAME,recipeModel.getImageName());

        long insert = db.insert(RECIPES_TABLE, null, cv);
        if (insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public List<RecipeModel> getEveryone(String categoryRecipe) {
        List<RecipeModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + RECIPES_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int recipeID = cursor.getInt(0);
                String recipeName = cursor.getString(1);
                String recipeIngredients = cursor.getString(2);
                String recipe = cursor.getString(3);
                String category = cursor.getString(4);
                String imageName = "";
                if (cursor.getString(5).contains(".")) {
                    imageName = cursor.getString(5).substring(0, cursor.getString(5).indexOf('.'));
                } else {
                    imageName = cursor.getString(5);
                }

                RecipeModel recipeModel = new RecipeModel(recipeID, recipeName, recipeIngredients, recipe, category, imageName);
                if (recipeModel.getCategory().equals(categoryRecipe)) {
                    returnList.add(recipeModel);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }
}
