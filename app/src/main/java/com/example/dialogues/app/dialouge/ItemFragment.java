package com.example.dialogues.app.dialouge;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dialogues.DownloadService;
import com.example.dialogues.R;
import com.example.dialogues.app.models.pojos.Item;
import com.example.dialogues.databinding.FragmentItemBinding;
import com.example.dialogues.network.download.ServiceSoundDownloader;
import com.example.dialogues.utils.BaseFragment;
import com.example.dialogues.utils.MusicPlayer;
import com.example.dialogues.utils.log.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class ItemFragment extends BaseFragment implements ItemScreen{
    private static final String TAG = ItemFragment.class.getSimpleName();

    private static final String ARG_POSITION = "ARG_ITEM_ID";

    private ItemPresenter itemPresenter;
    private FragmentItemBinding dataBinding;

    public ItemFragment() {
        // Required empty public constructor
    }


    public static ItemFragment newInstance(int position) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            int currPosition = getArguments().getInt(ARG_POSITION);
            itemPresenter = new ItemPresenter(this,
                    getMainActivity().getCurrItems(),
                    currPosition,
                    new Logger(),
                    new ServiceSoundDownloader(getMainActivity()));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_item, container, false);

        dataBinding.btnItemContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNext();
            }
        });

        doInit();

        try{
            Log.d(TAG, "onCreateView: registering downloadBroadcastReceiver");
            LocalBroadcastManager.getInstance(getMainActivity().getApplicationContext())
                    .registerReceiver(downloadBroadcastReceiver,
                            new IntentFilter(DownloadService.DOWNLOAD_BROADCAST));
        }catch (Exception ex){
            ex.printStackTrace();
            getMainActivity().showToast(getMainActivity().getString(R.string.error_generic));
        }

        return dataBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        itemPresenter.stopPlayingSound();

        try{
            LocalBroadcastManager.getInstance(getMainActivity().getApplicationContext())
                    .unregisterReceiver(downloadBroadcastReceiver);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void doInit() {
        Log.d(TAG, "doInit() called");
        itemPresenter.showCurrItem();
    }

    @Override
    public void showItem(Item item) {
        dataBinding.setItem(item);

        itemPresenter.getSoundFile(item);

        itemPresenter.getNextSoundFile();
    }

    @Override
    public void playSound(File file) {
        itemPresenter.startPlayingSound(file.getAbsolutePath());
    }

    @Override
    public void goToNext() {
        itemPresenter.incrementPosition();
        itemPresenter.showCurrItem();
    }

    @Override
    public void goToMain() {
        itemPresenter.stopPlayingSound();

        getMainActivity().navigateToMain();
    }



    BroadcastReceiver downloadBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive() called with: context = [" + context + "], intent = [" + intent + "]");
            Bundle b = intent.getExtras();

            if (b.getString(DownloadService.TAG_STATUS).equals(DownloadService.STATUS_COMPLETE)){
                String filePath = b.getString(DownloadService.TAG_FILE_PATH);
                String itemLink = b.getString(DownloadService.TAG_ITEM_LINK);
                String itemId = b.getString(DownloadService.TAG_ITEM_ID);

                itemPresenter.gotDownload(filePath, itemLink, itemId);
            }else {
                showError(getMainActivity().getString(R.string.error_generic));
            }

        }
    };
}
