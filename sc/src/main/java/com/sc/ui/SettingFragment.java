package com.sc.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sc.R;
import com.sc.ui.FragmentBase;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends FragmentBase {

    public SettingFragment() {
        // Required empty public constructor
        fragmentId = R.layout.fragment_setting;
    }

    @Override
    public void init() {
        view.findViewById(R.id.mainBtnSetting).setOnClickListener(parent);
    }
}
