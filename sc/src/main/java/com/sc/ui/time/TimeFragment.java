package com.sc.ui.time;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.sc.R;
import com.sc.calculate.time.Time;
import com.sc.datastructrue.records.TimeCalResult;
import com.sc.ui.FragmentBase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimeFragment extends FragmentBase {

    Button btnDatepicker;
    TextView bigTv;
    TextView smallTv;
    FragmentBase keyboard;
    FragmentBase datepicker;
    //
    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    Time time = new Time(df);

    public TimeFragment() {
        fragmentId = R.layout.fragment_time;
    }

    @Override
    protected void init(){
        // initFragment();
        keyboard = new NumberKeyboardFragment();
        keyboard.setParent(this);
        datepicker = new DatePickerFragment();
        datepicker.setParent(this);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.timeFlFrame, keyboard);
        transaction.commit();
        // initButton()
        btnDatepicker = view.findViewById(R.id.button2);
        btnDatepicker.setOnClickListener(this);
        //
        bigTv = view.findViewById(R.id.textView);
        smallTv = view.findViewById(R.id.textView2);
        //view.setTag("Time");
    }

    public void update(){
        TimeCalResult result = time.output();
        smallTv.setText(result.getExpr());
        bigTv.setText(result.getResult());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button2:
                showFragment(datepicker, R.id.timeFlFrame, R.anim.slide_right_in, R.anim.slide_left_out);
                break;
            default:
                String tag = String.valueOf(view.getTag());
                if ("Clear".equals(tag)){
                    time.input("Clear");
                }else{
                    time.input(((Button) view).getText().toString());
                }
        }
        update();
    }
    @Override
    public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, i);
        c.set(Calendar.MONTH, i1);
        c.set(Calendar.DATE, i2);
        time.input(df.format(c.getTime()));
        showFragment(keyboard, R.id.timeFlFrame, R.anim.slide_right_in, R.anim.slide_left_out);
        update();
    }
}
