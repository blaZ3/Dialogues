package com.example.dialogues.app.dialouge;

import com.example.dialogues.app.models.pojos.Item;
import com.example.dialogues.utils.log.ILogger;

import java.util.ArrayList;

/**
 * Created by vivek on 27/02/18.
 */

public class ItemPresenter  {
    private static final String TAG = ItemPresenter.class.getSimpleName();


    private ItemScreen screen;
    ArrayList<Item> items;
    private ILogger logger;

    public ItemPresenter(ItemScreen screen, ArrayList<Item> items, ILogger logger){
        this.screen = screen;
        this.items = items;
        this.logger = logger;
    }


    public void showCurrItem(int position){
        if (position < items.size()){
            screen.showItem(items.get(position));
        }else {
            screen.goToMain();
        }
    }

}
