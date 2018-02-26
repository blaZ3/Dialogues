package com.example.dialogues.welcome;

import com.example.dialogues.utils.BaseScreen;

/**
 * Created by vivek on 26/02/18.
 */

public interface WelcomeScreen extends BaseScreen {
    void goToList();

    void showNetworkError();
    void showApiError();

}
