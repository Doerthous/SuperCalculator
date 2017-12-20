package com.sc.ui.time;


import android.widget.CalendarView;
import com.sc.R;
import com.sc.ui.FragmentBase;

public class DatePickerFragment extends FragmentBase {

    public DatePickerFragment() {
        fragmentId = R.layout.fragment_date_picker;
    }

    @Override
    protected void init(){
        ((CalendarView)view.findViewById(R.id.calendarView)).setOnDateChangeListener(parent);
    }
}
