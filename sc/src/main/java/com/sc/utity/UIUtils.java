package com.sc.utity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sc.R;


/**
 * Created by Doerthous on 2017/6/29.
 */

public class UIUtils {
    public static void setTextViewTextColor(View view, int colorId){
        ((TextView)view).setTextColor(ContextCompat.getColor(view.getContext(), colorId));
    }
    public static void setBackgroundColor(View view, int colorId){
        view.setBackgroundResource(colorId);
    }
    public static void setImageButtonDrawable(View view, int drawableId){
        ((ImageButton)view).setImageDrawable(ContextCompat.getDrawable(view.getContext(), drawableId));
    }
    public static void disableButton(View view, int colorId){
        ((Button)view).setTextColor(ContextCompat.getColor(view.getContext(), colorId));
        view.setOnClickListener(null);
    }
    public static void disableButton(Button view, int colorId){
        disableButton((View)view, colorId);
    }
    public static void enableButton(Button view, int colorId, View.OnClickListener listener){
        ((Button)view).setTextColor(ContextCompat.getColor(view.getContext(), colorId));
        view.setOnClickListener(listener);
    }
    public static void autoScrollWithData(HorizontalScrollView scrollView, int direction){
        new AutoScrollWithData(scrollView, direction);
    }
    public static void disableVirtualKeyboard(EditText et){
        et.setInputType(InputType.TYPE_NULL);
    }
    public static void showFragment(FragmentManager fragmentManager, Fragment fragment,
                                    int containerId, int enter, int exit){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(enter, exit);
        transaction.replace(containerId, fragment);
        transaction.commit();
    }
}
class AutoScrollWithData implements ViewTreeObserver.OnGlobalLayoutListener {
    HorizontalScrollView scrollView;
    int direction;
    public AutoScrollWithData(HorizontalScrollView scrollView, int direction){
        this.scrollView = scrollView;
        this.direction = direction;
        scrollView.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }
    @Override
    public void onGlobalLayout() {
        scrollView.post(new Runnable() {
            public void run() {
                scrollView.fullScroll(direction);
            }
        });
    }
}
