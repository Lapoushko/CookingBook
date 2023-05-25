package com.example.testjavaprojectpp.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    private static String DB_PATH = "";
    private final Context mContext;


    public DatabaseHelper(@Nullable Context context) {
        super(context, "RecipeDB.db", null, 5);
        if (android.os.Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;
        copyDataBase();
        this.getReadableDatabase();
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
//                    imageName = cursor.getString(5).substring(0, cursor.getString(5).indexOf('.'));
                    imageName = cursor.getString(5);
                } else {
                    imageName = cursor.getString(5);
                }
                RecipeModel recipeModel = new RecipeModel(recipeID, recipeName, recipeIngredients, recipe, category, imageName);
                if (recipeModel.getCategory().equals(categoryRecipe)){
                    returnList.add(recipeModel);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<RecipeModel> getAll() {
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
                    imageName = cursor.getString(5);
//                    imageName = cursor.getString(5).substring(0, cursor.getString(5).indexOf('.'));
                } else {
                    imageName = cursor.getString(5);
                }

                RecipeModel recipeModel = new RecipeModel(recipeID, recipeName, recipeIngredients, recipe, category, imageName);
                returnList.add(recipeModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }

    private void copyDBFile() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }
    private void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }
    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    public int getLastIdFromMyTable()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM " + RECIPES_TABLE, null);
        int id = 0;
        if(cursor.moveToLast()){
            //name = cursor.getString(column_index);//to get other values
           id = cursor.getInt(0);//to get id, 0 is the column index
        }
        return id;
    }
}
