package com.example.dialogues.app.welcome;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dialogues.R;
import com.example.dialogues.utils.log.Logger;

public class WelcomeFragment extends Fragment implements WelcomeScreen{
    public static final String TAG = WelcomeFragment.class.getSimpleName();

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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);

        doInit();

        return rootView;
    }



    //view methods

    @Override
    public void doInit() {
        welcomePresenter.getItems();
    }

    @Override
    public void goToList() {

    }

    @Override
    public void showNetworkError() {

    }

    @Override
    public void showApiError() {

    }
}
