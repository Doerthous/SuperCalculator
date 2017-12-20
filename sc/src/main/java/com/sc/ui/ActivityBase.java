package com.sc.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CalendarView;

import com.sc.R;

/**
 * Created by Doerthous on 2017/7/5.
 */

public class ActivityBase extends AppCompatActivity implements ViewBase{
    protected void init(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(activityId);
        init();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
