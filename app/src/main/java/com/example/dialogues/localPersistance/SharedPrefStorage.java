package com.example.dialogues.localPersistance;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.dialogues.app.models.pojos.ItemResponse;
import com.google.gson.Gson;

/**
 * Created by vivek on 28/02/18.
 */

public class SharedPrefStorage implements LocalStorage {
    private static final String TAG = SharedPrefStorage.class.getSimpleName();

    private static SharedPrefStorage instance = null;

    private SharedPreferences sharedPreferences;

    public static SharedPrefStorage getInstance(Context context){
        if (instance == null){
            instance = new SharedPrefStorage(context);
        }
        return instance;
    }

    private SharedPrefStorage(Context context){
        sharedPreferences = context.getSharedPreferences("dialogue_store", Context.MODE_PRIVATE);
    }

    @Override
    public void put(String key, ItemResponse value) {
        Gson gson = new Gson();
        sharedPreferences.edit().putString(key, gson.toJson(value)).commit();
    }

    @Override
    public ItemResponse get(String key) {
        Gson gson = new Gson();
        String str = sharedPreferences.getString(key, "");

        try{
            ItemResponse response = gson.fromJson(str, ItemResponse.class);
            return response;
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }
}
