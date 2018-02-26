package com.example.dialogues.utils;

import android.util.Log;

/**
 * Created by vivek on 05/11/17.
 */

public class Logger implements ILogger {

    @Override
    public void d(String tag, String msg) {
        Log.d(tag, msg);
    }

    @Override
    public void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    @Override
    public void v(String tag, String msg) {
        Log.v(tag, msg);
    }

    @Override
    public void w(String tag, String msg) {
        Log.w(tag, msg);
    }
}
