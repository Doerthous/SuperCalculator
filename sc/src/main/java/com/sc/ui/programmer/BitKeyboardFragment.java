package com.sc.ui.programmer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sc.R;
import com.sc.calculate.programmer.ProgrammConstant;
import com.sc.calculate.programmer.Programmer;
import com.sc.utity.UIUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BitKeyboardFragment extends Fragment implements ProgrammConstant{
    private static BitKeyboardFragment instance;
    public static BitKeyboardFragment getInstance(View.OnClickListener parent) {
        //if (instance == null) {
            instance = new BitKeyboardFragment();
        //}
        instance.parent = parent;
        return instance;
    }
    private View.OnClickListener parent;
    private View view;
    private List<Button> byteKeyboard;
    private List<Button> wordKeyboard;
    private List<Button> dwordKeyboard;
    private List<Button> qwordKeyboard;

    private BitKeyboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bit_keyboard, container, false);
        initButton();
        return view;
    }

    private void initButton(){
        byteKeyboard = new ArrayList<>();
        wordKeyboard = new ArrayList<>();
        dwordKeyboard = new ArrayList<>();
        qwordKeyboard = new ArrayList<>();
        byteKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit0));
        byteKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit1));
        byteKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit2));
        byteKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit3));
        byteKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit4));
        byteKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit5));
        byteKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit6));
        byteKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit7));
        wordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit8));
        wordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit9));
        wordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit10));
        wordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit11));
        wordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit12));
        wordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit13));
        wordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit14));
        wordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit15));
        dwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit16));
        dwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit17));
        dwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit18));
        dwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit19));
        dwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit20));
        dwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit21));
        dwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit22));
        dwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit23));
        dwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit24));
        dwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit25));
        dwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit26));
        dwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit27));
        dwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit28));
        dwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit29));
        dwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit30));
        dwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit31));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit32));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit33));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit34));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit35));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit36));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit37));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit38));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit39));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit40));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit41));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit42));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit43));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit44));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit45));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit46));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit47));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit48));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit49));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit50));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit51));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit52));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit53));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit54));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit55));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit56));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit57));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit58));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit59));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit60));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit61));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit62));
        qwordKeyboard.add((Button) view.findViewById(R.id.pgmBtnBit63));
        int i = 0;
        for(Button b: byteKeyboard){
            b.setOnClickListener(parent);
            b.setTag("Bit"+i);
            i++;
        }
        for(Button b: wordKeyboard){
            b.setOnClickListener(parent);
            b.setTag("Bit"+i);
            i++;
        }
        for(Button b: dwordKeyboard){
            b.setOnClickListener(parent);
            b.setTag("Bit"+i);
            i++;
        }
        for(Button b: qwordKeyboard){
            b.setOnClickListener(parent);
            b.setTag("Bit"+i);
            i++;
        }
    }

    //
    /*public static final int BYTE = 8;
    public static final int WORD = 16;
    public static final int DWORD = 32;
    public static final int QWORD = 64;*/
    private int mode;
    public void setMode(int mode){
        this.mode = mode;
        if(isVisible){
            updateButtonTextColor();
        }
    }
    //
    private String button;
    public void setButton(String s){
        button = "";
        for(int i = s.length()-1; i >= 0; --i){ // 低位
            char ch = s.charAt(i);
            if(ch != ' '){
                button += s.charAt(i);
            }
        }
        while(button.length() < QWORD){ // 高位
            button += "0";
        }
        if(isVisible){
            updateButtonText();
        }
    }

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

    public void update(){
        updateButtonText();
        updateButtonTextColor();
    }
    private void updateButtonText(){
        int i = 0;
        for(int j = 0; i < BYTE; ++i, ++j){
            byteKeyboard.get(j).setText(String.valueOf(button.charAt(i)));
        }
        for(int j = 0; i < WORD; ++i, ++j){
            wordKeyboard.get(j).setText(String.valueOf(button.charAt(i)));
        }
        for(int j = 0; i < DWORD; ++i, ++j){
            dwordKeyboard.get(j).setText(String.valueOf(button.charAt(i)));
        }
        for(int j = 0; i < QWORD; ++i, ++j){
            qwordKeyboard.get(j).setText(String.valueOf(button.charAt(i)));
        }
    }
    private void updateButtonTextColor(){
        switch (mode){
            case BYTE:{
                for(Button b : wordKeyboard){
                    UIUtils.disableButton(b, R.color.colorGray);
                }
                for(Button b : dwordKeyboard){
                    UIUtils.disableButton(b, R.color.colorGray);
                }
                for(Button b : qwordKeyboard){
                    UIUtils.disableButton(b, R.color.colorGray);
                }
            } break;
            case WORD:{
                for(Button b : wordKeyboard){
                    UIUtils.enableButton(b, R.color.colorF8F8F8, parent);
                }
                for(Button b : dwordKeyboard){
                    UIUtils.disableButton(b, R.color.colorGray);
                }
                for(Button b : qwordKeyboard){
                    UIUtils.disableButton(b, R.color.colorGray);
                }
            } break;
            case DWORD:{
                for(Button b : wordKeyboard){
                    UIUtils.enableButton(b, R.color.colorF8F8F8, parent);
                }
                for(Button b : dwordKeyboard){
                    UIUtils.enableButton(b, R.color.colorF8F8F8, parent);
                }
                for(Button b : qwordKeyboard){
                    UIUtils.disableButton(b, R.color.colorGray);
                }
            } break;
            case QWORD:{
                for(Button b : wordKeyboard){
                    UIUtils.enableButton(b, R.color.colorF8F8F8, parent);
                }
                for(Button b : dwordKeyboard){
                    UIUtils.enableButton(b, R.color.colorF8F8F8, parent);
                }
                for(Button b : qwordKeyboard){
                    UIUtils.enableButton(b, R.color.colorF8F8F8, parent);
                }
            } break;
        }
    }
}
