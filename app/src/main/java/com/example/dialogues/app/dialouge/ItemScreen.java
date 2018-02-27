package com.example.dialogues.app.dialouge;

import com.example.dialogues.app.models.pojos.Item;
import com.example.dialogues.utils.BaseScreen;

import java.io.File;

/**
 * Created by vivek on 27/02/18.
 */

public interface ItemScreen extends BaseScreen{

    void showItem(Item item);
    void playSound(File file);

    void goToNext();
    void goToMain();

}
