package com.example.dialogues.app.itemsList;

import com.example.dialogues.app.models.pojos.Item;
import com.example.dialogues.utils.BaseScreen;

import java.util.ArrayList;

/**
 * Created by vivek on 26/02/18.
 */

public interface ItemsListScreen extends BaseScreen {

    void onItemsLoaded(ArrayList<Item> items);

    void onBackPressed();

}
