package com.example.dialogues.app.itemsList;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dialogues.R;
import com.example.dialogues.app.models.pojos.Item;
import com.example.dialogues.databinding.FragmentItemsListBinding;
import com.example.dialogues.network.download.ServiceSoundDownloader;
import com.example.dialogues.utils.BaseFragment;
import com.example.dialogues.utils.log.Logger;

import java.util.ArrayList;


public class ItemsListFragment extends BaseFragment implements ItemsListScreen{
    private static final String TAG = ItemsListFragment.class.getSimpleName();

    FragmentItemsListBinding dataBinding;

    private ItemsListPresenter itemsListPresenter;
    private ItemListAdapter adapter;

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
        itemsListPresenter = new ItemsListPresenter(this, getMainActivity().getCurrItems(),
                new Logger(), new ServiceSoundDownloader(getMainActivity()));
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

        doInit();

        return dataBinding.getRoot();
    }


    private void initToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(dataBinding.toolbarItemsList);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Dialogues");
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
        itemsListPresenter.loadItems();
    }

    ItemListAdapter.ItemListAdapterInterface itemListAdapterInterface = new ItemListAdapter.ItemListAdapterInterface() {
        @Override
        public void itemSelected(int position) {
            getMainActivity().navigateToItemFragment(position);
        }
    };

    @Override
    public void onItemsLoaded(ArrayList<Item> items) {
        adapter = new ItemListAdapter(getActivity(), items,
                itemListAdapterInterface);

        dataBinding.recyclerList.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        dataBinding.recyclerList.setAdapter(adapter);


        itemsListPresenter.downloadFirst();
    }

    @Override
    public void onItemsError() {
        getMainActivity().showToast(getString(R.string.error_generic));
    }

    @Override
    public void showLoadingProgress() {
        dataBinding.progressList.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingProgress() {
        dataBinding.progressList.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        getMainActivity().onBackPressed();
    }




}
