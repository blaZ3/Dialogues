package com.example.dialogues.app.itemsList;

import com.example.dialogues.app.models.pojos.Item;
import com.example.dialogues.network.download.SoundDownloader;
import com.example.dialogues.utils.FileHelper;
import com.example.dialogues.utils.log.ILogger;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by vivek on 26/02/18.
 */

public class ItemsListPresenter {
    private static final String TAG = ItemsListPresenter.class.getSimpleName();

    ItemsListScreen screen;
    ArrayList<Item> items;
    ILogger logger;
    SoundDownloader soundDownloader;

    public ItemsListPresenter(ItemsListScreen screen, ArrayList<Item> items, ILogger logger,
                              SoundDownloader soundDownloader){
        this.screen = screen;
        this.items = items;
        this.logger = logger;
        this.soundDownloader = soundDownloader;
    }


    public void loadItems(){
        if (items != null){
            screen.onItemsLoaded(items);
        }
    }


    public void downloadFirst(){
        if (items!=null && items.get(0)!=null){
            File file = FileHelper.getFile(items.get(0));
            if (file == null){
                soundDownloader.download(items.get(0));
            }
        }
    }

}
