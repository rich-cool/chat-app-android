package com.example.chatapp.ui;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chatapp.R;
import com.example.chatapp.util.Constants;
import com.example.chatapp.util.Logger;

public abstract class BaseFragment extends Fragment {

    private static final String TAG = Constants.LOGIN_FEATURE;

    public BaseFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(TAG, "onCreate()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.d(TAG, "onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.d(TAG, "onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.d(TAG, "onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.d(TAG, "onStop()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.d(TAG, "onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.d(TAG, "onDetach()");
    }
}
