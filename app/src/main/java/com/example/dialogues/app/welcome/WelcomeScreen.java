package com.example.dialogues.app.welcome;

import com.example.dialogues.app.models.pojos.Item;
import com.example.dialogues.utils.BaseScreen;

import java.util.ArrayList;

/**
 * Created by vivek on 26/02/18.
 */

public interface WelcomeScreen extends BaseScreen {

    void gotItems(ArrayList<Item> items);



}
