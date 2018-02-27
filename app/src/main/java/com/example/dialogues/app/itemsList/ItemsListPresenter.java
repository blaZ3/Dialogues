package com.example.dialogues.app.itemsList;

import com.example.dialogues.app.models.pojos.Item;
import com.example.dialogues.utils.log.ILogger;

import java.util.ArrayList;

/**
 * Created by vivek on 26/02/18.
 */

public class ItemsListPresenter {
    private static final String TAG = ItemsListPresenter.class.getSimpleName();

    ItemsListScreen screen;
    ArrayList<Item> items;
    ILogger logger;

    public ItemsListPresenter(ItemsListScreen screen, ArrayList<Item> items, ILogger logger){
        this.screen = screen;
        this.items = items;
        this.logger = logger;
    }


    public void loadItems(){
        if (items != null){
            screen.onItemsLoaded(items);
        }
    }


    public void downloadFirst(){

    }

    public void goToNext(){

    }

}
