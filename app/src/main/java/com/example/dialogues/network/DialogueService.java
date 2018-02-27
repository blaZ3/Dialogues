package com.example.dialogues.network;

import com.example.dialogues.app.models.pojos.ItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by vivek on 03/12/17.
 */

public interface DialogueService {

    @GET("gigal")
    Call<ItemResponse> getDialogues();

}
