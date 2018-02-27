package com.example.dialogues.app.models;

import android.util.Log;

import com.example.dialogues.app.models.pojos.Item;
import com.example.dialogues.app.models.pojos.ItemResponse;
import com.example.dialogues.network.DialogueService;
import com.example.dialogues.network.NetworkResponse;
import com.example.dialogues.utils.log.ILogger;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by vivek on 26/02/18.
 */

public class ItemModel {
    private static final String TAG = ItemModel.class.getSimpleName();

    ILogger logger;
    ItemRepository itemRepository;

    public ItemModel(ILogger logger, ItemRepository itemRepository) {
        logger.d(TAG, "ItemModel() called with: logger = [" + logger + "], itemRepository = [" + itemRepository + "]");
        this.logger = logger;
        this.itemRepository = itemRepository;
    }

    public Observable<ItemResponse> getItems(){

        Observable<ItemResponse> listObservable = Observable.create(new ObservableOnSubscribe<ItemResponse>() {
            @Override
            public void subscribe(final ObservableEmitter<ItemResponse> emitter) throws Exception {

                itemRepository.getItems().subscribe(new Observer<ItemResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(ItemResponse itemResponse) {
                        emitter.onNext(itemResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        emitter.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        emitter.onComplete();
                    }

                });

            }
        });



        return listObservable;
    }

}
