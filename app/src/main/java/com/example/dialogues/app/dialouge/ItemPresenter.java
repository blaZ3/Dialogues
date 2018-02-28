package com.example.dialogues.app.dialouge;

import com.example.dialogues.app.models.pojos.Item;
import com.example.dialogues.network.download.SoundDownloader;
import com.example.dialogues.utils.FileHelper;
import com.example.dialogues.utils.MusicPlayer;
import com.example.dialogues.utils.log.ILogger;

import java.io.File;
import java.io.PushbackInputStream;
import java.util.ArrayList;

/**
 * Created by vivek on 27/02/18.
 */

public class ItemPresenter  {
    private static final String TAG = ItemPresenter.class.getSimpleName();

    private int currPosition;
    private ItemScreen screen;
    private ArrayList<Item> items;
    private ILogger logger;
    private SoundDownloader soundDownloader;

    public ItemPresenter(ItemScreen screen, ArrayList<Item> items, int currPosition, ILogger logger,
                         SoundDownloader soundDownloader){
        this.screen = screen;
        this.items = items;
        this.currPosition = currPosition;
        this.logger = logger;
        this.soundDownloader = soundDownloader;
    }

    public void incrementPosition(){
        currPosition++;
    }

    public void showCurrItem(){
        if (currPosition < items.size()){
            screen.showItem(items.get(currPosition));
        }else {
            screen.goToMain();
        }
    }


    public void getSoundFile(Item item){
        File file = FileHelper.getFile(item);
        if (file != null){
            screen.playSound(file);
        }else {
            soundDownloader.download(item);
        }
    }

    public void getNextSoundFile(){
        int nextPosition = currPosition + 1;
        if (nextPosition < items.size()){
            Item nextItem = items.get(nextPosition);
            File file = FileHelper.getFile(nextItem);
            if (file != null){
                //do nothing
            }else {
                soundDownloader.download(nextItem);
            }
        }
    }


    public void gotDownload(String filePath, String url, String id){
        if (id.equals(items.get(currPosition).getItemId())){
            screen.playSound(new File(filePath));
        }
    }


    public void startPlayingSound(String absPath){
        MusicPlayer.getInstance().stop();
        MusicPlayer.getInstance().play(absPath);
    }

    public void stopPlayingSound(){
        MusicPlayer.getInstance().stop();
    }

    public int getCurrPosition() {
        return currPosition;
    }
}
