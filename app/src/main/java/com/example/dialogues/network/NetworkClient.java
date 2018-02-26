package com.example.dialogues.network;

import com.example.dialogues.AppConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vivek on 03/12/17.
 */

public class NetworkClient {

    static DialogueService service;

    public static DialogueService getDialogueService(){
        if (service == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstants.ROOT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(DialogueService.class);
        }

        return service;
    }

}
