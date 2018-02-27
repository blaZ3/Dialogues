package com.example.dialogues.app.welcome;

import com.example.dialogues.app.models.ItemModel;
import com.example.dialogues.app.models.ItemRepository;
import com.example.dialogues.app.models.pojos.ItemResponse;
import com.example.dialogues.network.NetworkClient;
import com.example.dialogues.utils.log.ILogger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by vivek on 26/02/18.
 */

public class WelcomePresenter {
    private static final String TAG = WelcomePresenter.class.getSimpleName();

    WelcomeScreen welcomeScreen;
    ILogger logger;

    ItemModel itemModel;

    public WelcomePresenter(WelcomeScreen welcomeScreen, ILogger logger){
        this.welcomeScreen = welcomeScreen;
        this.logger = logger;

        this.itemModel = new ItemModel(logger, new ItemRepository(NetworkClient.getDialogueService()));
    }

    /**
     * get items asynchronously
     */
    public void getItems(){
        welcomeScreen.showLoadingProgress();
        Observer<ItemResponse> itemResponseObserver = new Observer<ItemResponse>() {
            @Override
            public void onSubscribe(Disposable d) {}

            @Override
            public void onNext(ItemResponse itemResponse) {
                welcomeScreen.hideLoadingProgress();
                switch (itemResponse.getStatus()){
                    case SUCCESS:
                        welcomeScreen.gotItems(itemResponse.getData());
                        break;
                    case API_ERROR:
                        welcomeScreen.showApiError();
                        break;
                    case NETWORK_ERROR:
                        welcomeScreen.showNetworkError();
                        break;
                }
            }

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onComplete() {}
        };

        itemModel.getItems().subscribe(itemResponseObserver);
    }

}
