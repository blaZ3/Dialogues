package com.example.dialogues.app.welcome;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dialogues.R;
import com.example.dialogues.app.models.pojos.Item;
import com.example.dialogues.databinding.FragmentWelcomeBinding;
import com.example.dialogues.utils.BaseFragment;
import com.example.dialogues.utils.log.Logger;

import java.util.ArrayList;
import java.util.List;

public class WelcomeFragment extends BaseFragment implements WelcomeScreen{
    public static final String TAG = WelcomeFragment.class.getSimpleName();

    FragmentWelcomeBinding dataBinding;

    WelcomePresenter welcomePresenter;

    public WelcomeFragment() {
        // Required empty public constructor
    }

    public static WelcomeFragment newInstance() {
        WelcomeFragment fragment = new WelcomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        welcomePresenter = new WelcomePresenter(this, new Logger());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_welcome, container, false);

        dataBinding.btnWelcomeContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMainActivity().navigateToListFragment();
            }
        });

        return dataBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        doInit();
    }


    //view methods

    @Override
    public void doInit() {
        Log.d(TAG, "doInit() called");

        dataBinding.progressWelcome.setVisibility(View.GONE);
        dataBinding.btnWelcomeContinue.setVisibility(View.GONE);

        welcomePresenter.getItems();
    }

    @Override
    public void gotItems(ArrayList<Item> items) {
        getMainActivity().setCurrItems(items);
        dataBinding.btnWelcomeContinue.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNetworkError() {

    }

    @Override
    public void showApiError() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showLoadingProgress() {
        dataBinding.progressWelcome.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingProgress() {
        dataBinding.progressWelcome.setVisibility(View.GONE);
    }
}
