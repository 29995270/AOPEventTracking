package com.wq.freeze.aopeventtracking;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wq.freeze.aopeventtracking.lib.annotations.PageResume;

/**
 * Created by wangqi on 2015/12/28.
 */
public class DummyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = new View(getActivity());
        view.setBackgroundColor(Color.BLUE);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @PageResume(pageName = "DummyFragment")
    @Override
    public void onResume() {
        super.onResume();
    }

    @PageResume(pageName = "DummyFragment")
    @Override
    public void onPause() {
        super.onPause();
    }
}
