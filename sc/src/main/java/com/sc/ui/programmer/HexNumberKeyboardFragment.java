package com.sc.ui.programmer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sc.R;
import com.sc.calculate.programmer.ProgrammConstant;
import com.sc.ui.KeyboardFactory;
import com.sc.utity.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HexNumberKeyboardFragment extends Fragment implements ProgrammConstant {
    private static HexNumberKeyboardFragment instance;
    public static HexNumberKeyboardFragment getInstance(View.OnClickListener parent) {
        //if (instance == null) {
            instance = new HexNumberKeyboardFragment();
        //}
        instance.parent = parent;
        return instance;
    }

    View view;
    Fragment hexSubKb1;
    Fragment hexSubKb2;
    List<Button> binKeyboard;
    List<Button> octKeyboard;
    List<Button> decKeyboard;
    List<Button> hexKeyboard;
    List<Button> otherKeyboard;
    View.OnClickListener parent;

    private HexNumberKeyboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_hexnumber_keyboard, container, false);
        initFragment();
        initButton();
        return view;
    }

    private void initFragment(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        hexSubKb1 = KeyboardFactory.getKeyboard(KeyboardFactory.HEX_SUB_KEYBOARD1, parent);
        hexSubKb2 = KeyboardFactory.getKeyboard(KeyboardFactory.HEX_SUB_KEYBOARD2, parent);
        transaction.replace(R.id.pgmFlHexFrame, hexSubKb1);
        transaction.commit();

    }
    private void showFragment(Fragment fragment, int enter, int exit){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(enter, exit);
        transaction.replace(R.id.pgmFlHexFrame, fragment);
        transaction.commit();
    }
    public void initButton() {
        otherKeyboard = new ArrayList<>();
        binKeyboard = new ArrayList<>();
        octKeyboard = new ArrayList<>();
        decKeyboard = new ArrayList<>();
        hexKeyboard = new ArrayList<>();
        otherKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex0));
        otherKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex1));
        binKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex2));
        otherKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex3));
        otherKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex4));
        otherKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex5));
        otherKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex6));
        octKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex7));
        octKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex8));
        binKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex9));
        hexKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex10));
        hexKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex11));
        otherKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex12));
        octKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex13));
        octKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex14));
        octKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex15));
        hexKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex16));
        hexKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex17));
        otherKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex18));
        decKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex19));
        decKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex20));
        octKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex21));
        hexKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex22));
        hexKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex23));
        otherKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex24));
        otherKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex25));
        otherKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex26));
        otherKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex27));
        otherKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex28));
        otherKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex29));
        otherKeyboard.add((Button) view.findViewById(R.id.pgmBtnHex30));
        for(Button b : binKeyboard){
            b.setOnClickListener(parent);
        }
        for (Button b : otherKeyboard) {
            b.setOnClickListener(parent);
        }
        onResume();
    }

    /*public static final int BIN = 2;
    public static final int OCT = 8;
    public static final int DEC = 10;
    public static final int HEX = 16;*/
    private int mode = DEC;
    public void setMode(int mode){
        this.mode = mode;
        if(isVisible){
            onResume();
        }
    }
    public void prevKeyboard(){
        showFragment(hexSubKb1, R.anim.slide_left_in, R.anim.slide_right_out);
    }
    public void nextKeyboard(){
        showFragment(hexSubKb2, R.anim.slide_right_in, R.anim.slide_left_out);
    }
    //
    private boolean isVisible = false;
    @Override
    public void onResume(){
        super.onResume();
        isVisible = true;
        update();
    }
    @Override
    public void onPause(){
        super.onPause();
        isVisible = false;
    }
    //
    public void update(){
        switch (mode){
            case BIN:
                for(Button b : octKeyboard){
                    UIUtils.disableButton(b, R.color.colorGray);
                }
                for(Button b : decKeyboard){
                    UIUtils.disableButton(b, R.color.colorGray);
                }
                for(Button b : hexKeyboard){
                    UIUtils.disableButton(b, R.color.colorGray);
                }
                break;
            case OCT:
                for(Button b : octKeyboard){
                    b.setOnClickListener(parent);
                    UIUtils.enableButton(b, R.color.colorF8F8F8, parent);
                }
                for(Button b : decKeyboard){
                    UIUtils.disableButton(b, R.color.colorGray);
                }
                for(Button b : hexKeyboard){
                    UIUtils.disableButton(b, R.color.colorGray);
                }
                break;
            case DEC:
                for(Button b : octKeyboard){
                    UIUtils.enableButton(b, R.color.colorF8F8F8, parent);
                }
                for(Button b : decKeyboard){
                    UIUtils.enableButton(b, R.color.colorF8F8F8, parent);
                }
                for(Button b : hexKeyboard){
                    UIUtils.disableButton(b, R.color.colorGray);
                }
                break;
            case HEX:
                for(Button b : octKeyboard){
                    UIUtils.enableButton(b, R.color.colorF8F8F8, parent);
                }
                for(Button b : decKeyboard){
                    UIUtils.enableButton(b, R.color.colorF8F8F8, parent);
                }
                for(Button b : hexKeyboard){
                    UIUtils.enableButton(b, R.color.colorF8F8F8, parent);
                }
                break;
        }
    }

}

