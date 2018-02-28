package com.example.dialogues;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.dialogues.app.dialouge.ItemFragment;
import com.example.dialogues.app.models.pojos.Item;
import com.example.dialogues.databinding.ActivityMainBinding;
import com.example.dialogues.app.itemsList.ItemsListFragment;
import com.example.dialogues.app.welcome.WelcomeFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private final String TAG_MAIN = "TAG_MAIN";
    private final String TAG_LIST = "TAG_LIST";
    private final String TAG_ITEM = "TAG_ITEM";

    ActivityMainBinding dataBinding;

    ArrayList<Item> currItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        getSupportFragmentManager()
                .beginTransaction()
                .add(dataBinding.frameContainer.getId(),
                        WelcomeFragment.newInstance(), WelcomeFragment.TAG)
                .addToBackStack(TAG_MAIN)
                .commit();
    }


    public void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public ArrayList<Item> getCurrItems() {
        return currItems;
    }

    public void setCurrItems(ArrayList<Item> currItems) {
        this.currItems = currItems;
    }

    public void navigateToListFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(dataBinding.frameContainer.getId(), ItemsListFragment.newInstance())
                .addToBackStack(TAG_LIST)
                .commit();
    }

    public void navigateToItemFragment(int position){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(dataBinding.frameContainer.getId(), ItemFragment.newInstance(position))
                .addToBackStack(TAG_ITEM)
                .commit();
    }


    public void navigateToMain(){
        //pop other fragments and go to main
        getSupportFragmentManager()
                .popBackStack(TAG_MAIN, 0);
    }



}
