package com.example.dialogues.utils;

/**
 * Created by vivek on 26/02/18.
 */

public interface BaseScreen {

    void doInit();

    void showLoadingProgress();
    void hideLoadingProgress();

    void showNetworkError();
    void showApiError();
    void showError(String msg);

}
