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
//                Response<ResponseBody> response = NetworkClient.getDialogueService().downloadFileUrlSync("https://00e9e64bac8b4eab4e0e784d39e8217cad5d5ba5467a10a255-apidata.googleusercontent.com/download/storage/v1/b/assignment-perpule/o/2514.mp3?qk=AD5uMEsgaiSmp_AkwKgz8m4l4GpBAgEilzK5v4NkM69s39o5WLzfMUGdrgv5kWg0Mw22jPoPJi_KsnDwra8UObrU77sioaz5SqMQSM0BKh9ZkdyUC9LzyTsyS8tHpffIFL5yZf5U4TsHHJrJ6NoVhDYP19lzfLuwVyJ3SKeTxDp03SCAKvR6Ss6Alok87mQrKVSyBwlig2Vka9PgNnl696MC_PuPF0jbbqbm4wS56TP2pgsjoP3tnbeu-h-XR0rm8MgjnCRIiqmveLhqUky_N7afABfCGgxAxoRkiI0aCmEymU7N6JHDsX9lLcMsGnfujnByyhhwy7ABZ6WzlrTP2CUmz0et6f6VABbf3287L-zEbM3rAQl5iFmfbZNawm6K4XMJRTiC-1v46unJeEXqYMLSLyXZLU7cza7dIRzfcxBYnC5D71MM4i6N-IpQ4nphxxpzoScV0N7fFdQHMMMkQvm__JKHmBMdAlP3j_RRVwWvoGBRG0p9V3sSCUg8AOJSaDPKMiKRo38Hyul8Etj5DKnUPYqp_k7ArSUr_xd5eYA7ksE7MPw9p1Yi8HDnVsaKaBz6dLybLsS30MBwQjVyREShaXscG5X_EbZrCI89NRPkaXKUm91RRdnsxJw0suuBrsx_udb0XNSytWQgEbF9dLsU0gLY-Cs8L_mLsvir7WyLL9emuScGgSiYMtc9_x5rZI3sP3mmKkwr3VpkpPFQtZAlndoQQoTHcyaPJTY00NeSVJNOJkFSjlE").execute();

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
