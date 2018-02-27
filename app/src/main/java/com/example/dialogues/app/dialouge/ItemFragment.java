package com.example.dialogues.app.dialouge;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dialogues.R;
import com.example.dialogues.app.models.pojos.Item;
import com.example.dialogues.databinding.FragmentItemBinding;
import com.example.dialogues.utils.BaseFragment;
import com.example.dialogues.utils.log.Logger;


public class ItemFragment extends BaseFragment implements ItemScreen{
    private static final String TAG = ItemFragment.class.getSimpleName();

    private static final String ARG_POSITION = "ARG_ITEM_ID";
    private int currPosition;

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
    public void onAttach(Context context) {
        super.onAttach(context);
        itemPresenter = new ItemPresenter(this, getMainActivity().getCurrItems(),
                new Logger());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currPosition = getArguments().getInt(ARG_POSITION);
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

        return dataBinding.getRoot();
    }


    @Override
    public void doInit() {
        Log.d(TAG, "doInit() called");
        itemPresenter.showCurrItem(currPosition);
    }

    @Override
    public void showItem(Item item) {
        dataBinding.setItem(item);
    }

    @Override
    public void goToNext() {
        currPosition++;
        itemPresenter.showCurrItem(currPosition);
    }

    @Override
    public void goToMain() {
        //pop other fragment and go to main
        getMainActivity().navigateToMain();
    }
}
