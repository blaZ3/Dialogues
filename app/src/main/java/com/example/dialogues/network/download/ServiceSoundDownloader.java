package com.example.dialogues.network.download;

import android.content.Context;
import android.content.Intent;

import com.example.dialogues.DownloadService;
import com.example.dialogues.app.models.pojos.Item;

/**
 * Created by vivek on 27/02/18.
 */

public class ServiceSoundDownloader implements SoundDownloader {

    Context context;

    public ServiceSoundDownloader(Context context){
        this.context = context;
    }

    @Override
    public void download(Item item) {
        if (DownloadService.shouldAddToDownload(item.getItemId())){ //if download is already queued
            Intent downloadServiceIntent = new Intent(context, DownloadService.class);
            downloadServiceIntent.putExtra(DownloadService.TAG_ITEM_ID, item.getItemId());
            downloadServiceIntent.putExtra(DownloadService.TAG_ITEM_LINK, item.getAudio());
            context.startService(downloadServiceIntent);
        }
    }
}
