package com.example.dialogues.app.models;

import com.example.dialogues.network.DialogueService;
import com.example.dialogues.network.NetworkClient;
import com.example.dialogues.app.models.pojos.ItemResponse;
import com.example.dialogues.network.NetworkResponse;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vivek on 26/02/18.
 */

public class ItemRepository {
    private static final String TAG = ItemRepository.class.getSimpleName();

    DialogueService dialogueService;

    public ItemRepository(DialogueService dialogueService) {
        this.dialogueService = dialogueService;
    }

    public Observable<ItemResponse> getItems(){
        return getItemsFromNetwork();
    }


    private Observable<ItemResponse> getItemsFromNetwork(){
        Observable<ItemResponse> responseObservable = Observable.create(new ObservableOnSubscribe<ItemResponse>() {
            @Override
            public void subscribe(final ObservableEmitter<ItemResponse> emitter) throws Exception {
                dialogueService.getDialogues().enqueue(new Callback<ItemResponse>() {
                    @Override
                    public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                        ItemResponse itemResponse = null;
                        if (response.isSuccessful() && response.errorBody() == null){
                            itemResponse = response.body();
                            itemResponse.setStatus(NetworkResponse.Status.SUCCESS);
                            emitter.onNext(itemResponse);
                        }else{
                            itemResponse = new ItemResponse();
                            itemResponse.setStatus(NetworkResponse.Status.API_ERROR);
                            emitter.onNext(itemResponse);
                        }
                    }

                    @Override
                    public void onFailure(Call<ItemResponse> call, Throwable t) {
                        ItemResponse itemResponse = new ItemResponse();
                        itemResponse.setStatus(NetworkResponse.Status.NETWORK_ERROR);
                        emitter.onNext(itemResponse);
                    }
                });
            }
        });

        return responseObservable;
    }


    private void getItemsFromStorage(){

    }

}
