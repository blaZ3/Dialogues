package com.example.dialogues.utils;

import android.support.v4.app.Fragment;

import com.example.dialogues.MainActivity;

/**
 * Created by vivek on 27/02/18.
 */

public class BaseFragment extends Fragment {


    protected MainActivity getMainActivity(){
        return (MainActivity) getActivity();
    }

}
