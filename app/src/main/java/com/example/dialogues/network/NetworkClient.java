package com.example.dialogues.network;

import android.util.Log;

import com.example.dialogues.AppConstants;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vivek on 03/12/17.
 */

public class NetworkClient {
    private static final String TAG = NetworkClient.class.getSimpleName();

    private static final int DOWNLOAD_BUFFER_SIZE=20*1024;//20 kB

    static DialogueService dialogueService;

    public static DialogueService getDialogueService(){
        if (dialogueService == null){
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            client.followRedirects(true);
            client.followSslRedirects(true);
            client.addNetworkInterceptor(interceptor);
            OkHttpClient httpClient = client.build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstants.ROOT)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            dialogueService = retrofit.create(DialogueService.class);
        }

        return dialogueService;
    }

    private static Interceptor interceptor = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Log.d(TAG, "intercept: " + request.url().toString());
            return chain.proceed(request);

        }
    };

}
