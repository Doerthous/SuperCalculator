package com.sc.ui.programmer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sc.R;
import com.sc.calculate.programmer.ProgrammConstant;
import com.sc.calculate.programmer.Programmer;
import com.sc.ui.KeyboardFactory;
import com.sc.utity.Keyboard;
import com.sc.utity.UIUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProgrammerFragment extends Fragment implements View.OnClickListener, ProgrammConstant{
    View view;
    LinearLayout bin;
    LinearLayout oct;
    LinearLayout dec;
    LinearLayout hex;
    Button btnType;

    Button pgmBtnNumKey;
    Button pgmBtnBitKey;

    TextView tvSmall;
    TextView tvBig;
    TextView tvBin;
    TextView tvOct;
    TextView tvDec;
    TextView tvHex;
    TextView tvAcsii;
    HexNumberKeyboardFragment hexKeyboard;
    BitKeyboardFragment bitKeyboard;
    Programmer programmer = new Programmer();

    public ProgrammerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_programmer, container, false);
        initFragment();
        initButton();
        initTextView();
        initScrollView();
        select(dec);
        update();
        return view;
    }
    private void initFragment(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        hexKeyboard = (HexNumberKeyboardFragment) KeyboardFactory.getKeyboard(KeyboardFactory.HEX_KEYBOARD, this);
        bitKeyboard = (BitKeyboardFragment) KeyboardFactory.getKeyboard(KeyboardFactory.BIT_KEYBOARD, this);
        transaction.replace(R.id.pgmFlKeyboard, hexKeyboard);
        transaction.commit();

    }
    private void initButton(){
        view.findViewById(R.id.pgmBtnNumKey).setOnClickListener(this);
        view.findViewById(R.id.pgmBtnBitKey).setOnClickListener(this);
        bin = view.findViewById(R.id.pgmBtnBIN);
        bin.setOnClickListener(this);
        oct = view.findViewById(R.id.pgmBtnOCT);
        oct.setOnClickListener(this);
        dec = view.findViewById(R.id.pgmBtnDEC);
        dec.setOnClickListener(this);
        hex = view.findViewById(R.id.pgmBtnHEX);
        hex.setOnClickListener(this);
        btnType = view.findViewById(R.id.pgmBtnType);
        btnType.setOnClickListener(this);

        pgmBtnNumKey = view.findViewById(R.id.pgmBtnNumKey);
        pgmBtnBitKey = view.findViewById(R.id.pgmBtnBitKey);
        UIUtils.setBackgroundColor(pgmBtnBitKey,R.drawable.mybutton2);
        UIUtils.setBackgroundColor(pgmBtnNumKey,R.color.colorBlue);
    }
    private void initTextView(){
        tvSmall = view.findViewById(R.id.pgmTvSmall);
        tvBig = view.findViewById(R.id.pgmTvBig);
        tvBin = view.findViewById(R.id.pgmTvBin);
        tvOct = view.findViewById(R.id.pgmTvOct);
        tvDec = view.findViewById(R.id.pgmTvDec);
        tvHex = view.findViewById(R.id.pgmTvHex);
        tvAcsii = view.findViewById(R.id.pgmTvAscii);
    }
    private void initScrollView(){
        UIUtils.autoScrollWithData((HorizontalScrollView) view.findViewById(R.id.pgmScrvSmall), View.FOCUS_RIGHT);
        UIUtils.autoScrollWithData((HorizontalScrollView) view.findViewById(R.id.pgmScrvBig), View.FOCUS_RIGHT);
    }
    private void showFragment(Fragment fragment, int enter, int exit){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(enter, exit);
        transaction.replace(R.id.pgmFlKeyboard, fragment);
        transaction.commit();
    }
    private void update(){
        String[] out = programmer.output();
        tvSmall.setText(out[0]);
        tvBig.setText(out[1]);
        tvBin.setText(out[2]);
        tvOct.setText(out[3]);
        tvDec.setText(out[4]);
        tvHex.setText(out[5]);
        tvAcsii.setText(out[6]);
        bitKeyboard.setMode(programmer.getType());
        bitKeyboard.setButton(out[2]);
        hexKeyboard.setMode(programmer.getFormat());
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pgmBtnNumKey:
                showFragment(hexKeyboard, R.anim.slide_left_in, R.anim.slide_right_out);
                UIUtils.setBackgroundColor(pgmBtnBitKey,R.drawable.mybutton2);
                UIUtils.setBackgroundColor(pgmBtnNumKey,R.color.colorBlue);
                break;
            case R.id.pgmBtnBitKey:
                showFragment(bitKeyboard, R.anim.slide_right_in, R.anim.slide_left_out);
                UIUtils.setBackgroundColor(pgmBtnNumKey,R.drawable.mybutton2);
                UIUtils.setBackgroundColor(pgmBtnBitKey,R.color.colorBlue);
                break;
            case R.id.pgmBtnBIN:
                setFormat(Programmer.BIN);
                break;
            case R.id.pgmBtnOCT:
                setFormat(Programmer.OCT);
                break;
            case R.id.pgmBtnDEC:
                setFormat(Programmer.DEC);
                break;
            case R.id.pgmBtnHEX:
                setFormat(Programmer.HEX);
                break;
            case R.id.pgmBtnType:
                changeType();
                break;
            default: // 输入
                String tag = (String) view.getTag();
                if(tag != null && tag.length() > 0){
                    if(Keyboard.CLR.equals(tag)){
                        programmer.input(Keyboard.CLR);
                    } else if(tag.contains("Bit")){
                        setNumber(view);
                    }
                } else{
                    String text = (((Button)view).getText().toString());
                    switch (text){
                        case Keyboard.LEFT:
                            hexKeyboard.prevKeyboard();
                            break;
                        case Keyboard.RIGHT:
                            hexKeyboard.nextKeyboard();
                            break;
                        default:
                            programmer.input(text);
                    }
                }
        }
        update();
    }
    //
    private void setFormat(int format){
        int currFormat = programmer.getFormat();
        if(currFormat == format){
            return;
        }
        programmer.setFormat(format);
        unSelectAll();
        switch (programmer.getFormat()){
            case BIN:
                select(bin);
                break;
            case OCT:
                select(oct);
                break;
            case DEC:
                select(dec);
                break;
            case HEX:
                select(hex);
                break;
        }
    }
    private void unSelectAll(){
        unSelect(bin);
        unSelect(oct);
        unSelect(dec);
        unSelect(hex);
    }
    private void unSelect(LinearLayout ll){
        int i = ll.getChildCount();
        while(--i >= 0){
            View c = ll.getChildAt(i);
            if(c.getTag().equals("Block")){
                UIUtils.setBackgroundColor(c, R.color.colorTransparent);
            } else {
                UIUtils.setTextViewTextColor(c, R.color.colorF8F8F8);
            }
        }
    }
    private void select(LinearLayout ll){
        int i = ll.getChildCount();
        while(--i >= 0){
            View c = ll.getChildAt(i);
            if(c.getTag().equals("Block")){
                UIUtils.setBackgroundColor(c, R.color.colorBlue);
            } else {
                UIUtils.setTextViewTextColor(c, R.color.colorBlue);
            }
        }
    }
    //
    private void changeType(){
        String type = btnType.getText().toString();
        if(type.equals("QWord")){
            btnType.setText("DWord");
            setType(DWORD);
        } else if(type.equals("DWord")){
            btnType.setText("Word");
            setType(WORD);
        } else if(type.equals("Word")){
            btnType.setText("Byte");
            setType(BYTE);
        } else {
            btnType.setText("QWord");
            setType(QWORD);
        }
    }
    private void setType(int type){
        programmer.setType(type);
    }
    //
    private void setNumber(View view){
        Button b = (Button)view;
        String tag = (String) view.getTag();
        String text = b.getText().toString();
        int id = Integer.valueOf(tag.substring(3, tag.length()));
        if("1".equals(text)){
            programmer.setNumber(id, 0);
            b.setText("0");
        } else {
            programmer.setNumber(id, 1);
            b.setText("1");
        }
    }
}
