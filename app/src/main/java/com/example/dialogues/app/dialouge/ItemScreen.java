package com.example.dialogues.app.dialouge;

import com.example.dialogues.app.models.pojos.Item;
import com.example.dialogues.utils.BaseScreen;

/**
 * Created by vivek on 27/02/18.
 */

public interface ItemScreen extends BaseScreen{

    void showItem(Item item);

    void goToNext();
    void goToMain();

}
