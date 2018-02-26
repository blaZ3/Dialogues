package com.example.dialogues.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vivek on 03/12/17.
 */

public class MockNetworkClient {

    static DialogueService service;

    public static DialogueService getDialogueService(String root){
        if (service == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(root)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(DialogueService.class);
        }

        return service;
    }

}
