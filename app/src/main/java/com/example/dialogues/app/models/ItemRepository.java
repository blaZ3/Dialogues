package com.example.dialogues.app.models;

import com.example.dialogues.localPersistance.LocalStorage;
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

    private static final String ITEMS_KEY = "ITEMS_KEY";

    private DialogueService dialogueService;
    private LocalStorage localStorage;

    public ItemRepository(DialogueService dialogueService, LocalStorage localStorage) {
        this.dialogueService = dialogueService;
        this.localStorage = localStorage;
    }

    public Observable<ItemResponse> getItems(){

        if (localStorage.get(ITEMS_KEY) != null){
            return getItemsFromLocalStorage();
        }

        return getItemsFromNetwork();
    }


    private Observable<ItemResponse> getItemsFromLocalStorage(){
        Observable<ItemResponse> responseObservable = Observable.create(new ObservableOnSubscribe<ItemResponse>() {
            @Override
            public void subscribe(ObservableEmitter<ItemResponse> emitter) throws Exception {
                try{
                    emitter.onNext(localStorage.get(ITEMS_KEY));
                }catch (Exception ex){
                    ex.printStackTrace();
                    emitter.onError(ex);
                }
            }
        });

        return responseObservable;
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

                            if (localStorage!=null){
                                localStorage.put(ITEMS_KEY, itemResponse);
                            }

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
