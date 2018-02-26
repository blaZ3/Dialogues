package com.example.dialogues.app.models;

/**
 * Created by vivek on 26/02/18.
 */

public class ItemModel {
    private static final String TAG = ItemModel.class.getSimpleName();

    static ItemModel instance = null;

    ItemRepository itemRepository;

    public static ItemModel getInstance(){
        if (instance == null){
            instance = new ItemModel();
        }
        return instance;
    }

    private ItemModel() {
        itemRepository = new ItemRepository();
    }


    public void getItems(){

    }

}
