package com.sc.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

/**
 * Created by Doerthous on 2017/7/2.
 */

public class FragmentBase extends Fragment implements ViewBase{
    protected int fragmentId;
    protected View view;
    protected ViewBase parent;
    public FragmentBase() {
        // Required empty public constructor
    }
    public void setParent(ViewBase parent){
        this.parent = parent;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(fragmentId, container, false);
        init();
        return view;
    }

    protected void init(){}

    protected void showFragment(FragmentBase fragment, int containerId, int enter, int exit){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(enter, exit);
        transaction.replace(containerId, fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        if(parent != null) {
            parent.onClick(view);
        }
    }
    @Override
    public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
        if(parent != null) {
            parent.onSelectedDayChange(calendarView, i, i1, i2);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
