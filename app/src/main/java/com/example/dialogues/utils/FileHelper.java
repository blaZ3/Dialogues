package com.example.dialogues.utils;

import android.os.Environment;
import android.util.Log;

import com.example.dialogues.app.models.pojos.Item;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

/**
 * Created by vivek on 27/02/18.
 */

public class FileHelper {
    private static final String TAG = FileHelper.class.getSimpleName();

    public static String root_directory = Environment.getExternalStorageDirectory().getPath() + "/Dialogues";

    public static String getFilePathForItemId(String id){
        return getFilePathForName(id + ".mp3");
    }

    public static String getFilePathForName(String name){
        String filePath = root_directory + "/" + name;
        try{
            final File rootDir = new File(root_directory);
            if(!rootDir.exists())
                rootDir.mkdirs();

            return filePath;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }


    public static File getFile(Item item){
        String filePath = getFilePathForItemId(item.getItemId());
        File file = new File(filePath);

        if (file.exists()){
            return file;
        }else {
            return null;
        }
    }


    public static boolean writeResponseBodyToDisk(ResponseBody body, String filePath) {
        try {
            // todo change the file location/name according to your needs
            File outputFile = new File(filePath);

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(outputFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

}
