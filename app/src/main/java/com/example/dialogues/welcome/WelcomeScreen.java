package com.example.dialogues.welcome;

/**
 * Created by vivek on 26/02/18.
 */

public interface WelcomeScreen {

    void doInit();
    void goToList();

    void showNetworkError();
    void showApiError();

}
