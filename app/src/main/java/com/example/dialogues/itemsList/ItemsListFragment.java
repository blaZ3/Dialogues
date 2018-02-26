package com.example.dialogues.itemsList;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dialogues.R;
import com.example.dialogues.databinding.FragmentItemsListBinding;
import com.example.dialogues.utils.Logger;


public class ItemsListFragment extends Fragment implements ItemsListScreen{
    private static final String TAG = ItemsListFragment.class.getSimpleName();

    FragmentItemsListBinding dataBinding;

    ItemsListPresenter itemsListPresenter;

    public ItemsListFragment() {
        // Required empty public constructor
    }


    public static ItemsListFragment newInstance() {
        Log.d(TAG, "newInstance() called");
        ItemsListFragment fragment = new ItemsListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        itemsListPresenter = new ItemsListPresenter(this, new Logger());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_items_list, container, false);

        initToolbar();

        return dataBinding.getRoot();
    }


    private void initToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(dataBinding.toolbarItemsList);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("");
        dataBinding.toolbarItemsList.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back_arrow_white));
        dataBinding.toolbarItemsList.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * view methods
     */

    @Override
    public void doInit() {

    }

    @Override
    public void onItemsLoaded() {

    }

    @Override
    public void onContinue() {

    }

    @Override
    public void goToNext() {

    }

    @Override
    public void goToMain() {

    }

    @Override
    public void onBackPressed() {

    }
}
