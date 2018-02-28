package com.example.dialogues.localPersistance;

import com.example.dialogues.app.models.pojos.ItemResponse;

/**
 * Created by vivek on 28/02/18.
 */

public class MockLocalStorage implements  LocalStorage {


    @Override
    public void put(String key, ItemResponse value) {

    }

    @Override
    public ItemResponse get(String key) {
        return null;
    }
}
