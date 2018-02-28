package com.example.dialogues;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.example.dialogues.network.NetworkClient;
import com.example.dialogues.utils.FileHelper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by vivek on 27/02/18.
 */

public class DownloadService extends IntentService {
    private static final String TAG = DownloadService.class.getSimpleName();

    public static final String DOWNLOAD_BROADCAST = "DOWNLOAD_BROADCAST";

    public static final String STATUS_COMPLETE = "STATUS_COMPLETE";
    public static final String STATUS_FAIL = "STATUS_FAIL";

    public static final String TAG_ITEM_ID = "TAG_ITEM_ID";
    public static final String TAG_ITEM_LINK = "TAG_ITEM_LINK";
    public static final String TAG_FILE_PATH = "TAG_FILE_PATH";
    public static final String TAG_STATUS = "TAG_STATUS";

    private static ArrayList<String> downloadsQueued = new ArrayList<>();

    public DownloadService(){
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent() called with: intent = [" + intent + "]");
        Bundle b = intent.getExtras();

        String id = b.getString(TAG_ITEM_ID);
        String url = b.getString(TAG_ITEM_LINK);

        String filePath = FileHelper.getFilePathForItemId(id);

        Intent downloadBroadcastIntent = new Intent(DOWNLOAD_BROADCAST);
        if (filePath != null){

            Log.d(TAG, "onHandleIntent: filePath:"+filePath);
            Log.d(TAG, "onHandleIntent: url:"+url);

            //add to current downloads
            downloadsQueued.add(id);

            try {
                Response<ResponseBody> response = NetworkClient.getDialogueService().downloadFileUrlSync(url).execute();

                FileHelper.writeResponseBodyToDisk(response.body(), filePath);

                //remove form current downloads
                downloadsQueued.remove(id);

                downloadBroadcastIntent.putExtra(TAG_STATUS, STATUS_COMPLETE);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }else {
            downloadBroadcastIntent.putExtra(TAG_STATUS, STATUS_FAIL);
        }

        try{
            //raise broadcast
            downloadBroadcastIntent.putExtra(TAG_ITEM_ID, id);
            downloadBroadcastIntent.putExtra(TAG_FILE_PATH, filePath);
            downloadBroadcastIntent.putExtra(TAG_ITEM_LINK, url);

            LocalBroadcastManager.getInstance(getApplicationContext())
                    .sendBroadcast(downloadBroadcastIntent);

            Log.d(TAG, "onHandleIntent: raising DOWNLOAD_BROADCAST");
        }catch (Exception ex){
            ex.printStackTrace();
            Toast.makeText(this, getString(R.string.error_generic), Toast.LENGTH_SHORT).show();
        }


    }

    public static boolean shouldAddToDownload(String id){
        Log.d(TAG, "shouldAddToDownload() called with: id = [" + id + "]");
        if (downloadsQueued.contains(id)){
            return false;
        }

        return true;
    }
}
