package com.example.testjavaprojectpp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.util.LruCache;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class Cache {

    private static Cache instance;
    private LruCache<Object, Object> lru;

    public Cache() {
        lru = new LruCache<Object, Object>(1024);

    }

    public static Cache getInstance() {

        if (instance == null) {
            instance = new Cache();
        }
        return instance;

    }

    public LruCache<Object, Object> getLru() {
        return lru;
    }


    public Bitmap retrieveBitmapFromCache(String key) {

        try {
            Bitmap bitmap = (Bitmap) Cache.getInstance().getLru().get(key);
            return bitmap;
        } catch (Exception e) {
        }
        return null;
    }

    public Bitmap loadImageBitmap(Context context, String imageName) {
        Bitmap bitmap = null;
        FileInputStream fiStream;
        try {
            fiStream    = context.openFileInput(imageName);
            bitmap      = BitmapFactory.decodeStream(fiStream);
            fiStream.close();
        } catch (Exception e) {
            Log.d("saveImage", "Exception 3, Something went wrong!");
            e.printStackTrace();
        }
        return bitmap;
    }


    public void saveImage(Context context, Bitmap b, String imageName)
    {
        FileOutputStream foStream;
        try
        {
            foStream = context.openFileOutput(imageName, Context.MODE_PRIVATE);
            b.compress(Bitmap.CompressFormat.PNG, 100, foStream);
            foStream.close();
        }
        catch (Exception e)
        {
            Log.d("saveImage", "Exception 2, Something went wrong!");
            e.printStackTrace();
        }
    }
}