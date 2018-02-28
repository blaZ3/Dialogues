package com.example.dialogues.localPersistance;

import com.example.dialogues.app.models.pojos.ItemResponse;

/**
 * Created by vivek on 28/02/18.
 */

public interface LocalStorage {

    void put(String key, ItemResponse value);
    ItemResponse get(String key);

}
