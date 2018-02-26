package com.example.dialogues;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dialogues.databinding.ActivityMainBinding;
import com.example.dialogues.itemsList.ItemsListFragment;
import com.example.dialogues.welcome.WelcomeFragment;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        getSupportFragmentManager()
                .beginTransaction()
                .add(dataBinding.frameContainer.getId(),
                        ItemsListFragment.newInstance(), WelcomeFragment.TAG)
                .commit();
    }
}
