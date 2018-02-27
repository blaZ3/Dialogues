package com.example.dialogues.utils;

import android.support.v4.app.Fragment;

import com.example.dialogues.MainActivity;
import com.example.dialogues.R;

/**
 * Created by vivek on 27/02/18.
 */

public class BaseFragment extends Fragment implements BaseScreen{

    protected MainActivity getMainActivity(){
        return (MainActivity) getActivity();
    }

    @Override
    public void doInit() {

    }

    @Override
    public void showLoadingProgress() {}

    @Override
    public void hideLoadingProgress() {}

    @Override
    public void showNetworkError() {
        getMainActivity().showToast(getMainActivity().getString(R.string.error_network));
    }

    @Override
    public void showApiError() {
        getMainActivity().showToast(getMainActivity().getString(R.string.error_api));
    }

    @Override
    public void showError(String msg) {
        getMainActivity().showToast(msg);
    }
}
