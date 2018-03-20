package com.example.tt.showcase.ui.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.tt.showcase.data.network.ApiService;
import com.example.tt.showcase.utils.LogUtils;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by TT on 20.3.2018..
 * Showcase
 */

public class Test2Fragment extends Fragment {

    @Inject ApiService apiService;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.log(apiService == null);
    }
}
