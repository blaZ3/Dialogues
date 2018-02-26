package com.example.dialogues.utils.log;

/**
 * Created by vivek on 05/11/17.
 */

public class UnitTestLogger implements ILogger {

    @Override
    public void d(String tag, String msg) {
//        System.out.println("D " + tag + ": " + msg);
    }

    @Override
    public void e(String tag, String msg) {
//        System.out.println("E " + tag + ": " + msg);
    }

    @Override
    public void v(String tag, String msg) {
//        System.out.println("V " + tag + ": " + msg);
    }

    @Override
    public void w(String tag, String msg) {
//        System.out.println("W " + tag + ": " + msg);
    }
}
