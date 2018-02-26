package com.example.dialogues.welcome;

import android.util.Log;

import com.example.dialogues.utils.ILogger;

/**
 * Created by vivek on 26/02/18.
 */

public class WelcomePresenter {
    private static final String TAG = WelcomePresenter.class.getSimpleName();

    WelcomeScreen welcomeScreen;
    ILogger logger;

    public WelcomePresenter(WelcomeScreen welcomeScreen, ILogger logger){
        this.welcomeScreen = welcomeScreen;
        this.logger = logger;
    }

    /**
     * get items asynchronously
     */
    public void getItems(){

    }

}
