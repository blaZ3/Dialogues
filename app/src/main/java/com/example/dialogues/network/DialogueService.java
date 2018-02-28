package com.example.dialogues.network;

import com.example.dialogues.app.models.pojos.ItemResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by vivek on 03/12/17.
 */

public interface DialogueService {

    @GET("mxcsl")
    Call<ItemResponse> getDialogues();

    @GET
    Call<ResponseBody> downloadFileUrlSync(@Url String fileUrl);

}
