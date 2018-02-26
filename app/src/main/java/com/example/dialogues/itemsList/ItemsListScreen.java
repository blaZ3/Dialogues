package com.example.dialogues.itemsList;

import com.example.dialogues.utils.BaseScreen;

/**
 * Created by vivek on 26/02/18.
 */

public interface ItemsListScreen extends BaseScreen {

    void onItemsLoaded();
    void onContinue();

    void goToNext();
    void goToMain();

    void onBackPressed();

}
