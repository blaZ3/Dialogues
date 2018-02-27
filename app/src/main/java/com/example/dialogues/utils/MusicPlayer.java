package com.example.dialogues.utils;

import android.media.MediaPlayer;
import android.util.Log;

/**
 * Created by vivek on 27/02/18.
 */

public class MusicPlayer {
    private static final String TAG = MusicPlayer.class.getSimpleName();

    private MediaPlayer mp;

    static MusicPlayer musicPlayer = null;

    public static MusicPlayer getInstance(){
        if (musicPlayer == null){
            musicPlayer = new MusicPlayer();
        }

        return musicPlayer;
    }

    private void MusicPlayer(){

    }

    public void play(String filePath){
        Log.d(TAG, "play() called with: filePath = [" + filePath + "]");
        //set up MediaPlayer
        mp = new MediaPlayer();

        try {
            mp.setDataSource(filePath);
            mp.prepare();
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        //set up MediaPlayer

        try {
            if (mp!=null && mp.isPlaying()){
                mp.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
