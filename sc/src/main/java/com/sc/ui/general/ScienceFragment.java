package com.sc.ui.general;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.sc.R;
import com.sc.calculate.general.Science;
import com.sc.datastructrue.records.ScienceCalResult;
import com.sc.utity.Keyboard;
import com.sc.utity.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScienceFragment extends Fragment implements View.OnClickListener{
    Science science = new Science();
    TextView expr;
    TextView number;
    View view;
    Button rad;
    Button deg;
    public static Boolean state = true;
    private List<Button> keyboard;
    private String[] keyboard1 = {
            Keyboard.MOD,Keyboard.FTR,Keyboard.LOG,Keyboard.TEN_NTHPW,Keyboard.SQRT,
            Keyboard.TAN,Keyboard.COS,Keyboard.SIN,Keyboard.NTHPW,Keyboard.SQUARE};
    private String[] keyboard2 = {
            Keyboard.DEG,Keyboard.RAD,Keyboard.LN,Keyboard.NB_NTHPW,Keyboard.DIV_BY_X,Keyboard.ATAN,
            Keyboard.ACOS,Keyboard.ASIN,Keyboard.NTHRT,Keyboard.CUBE};
    private String[] currKeyboard;
    public ScienceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_science, container, false);
        initButton();
        initTextView();
        initScrollView();
        return view;
    }

    private void initScrollView(){
        UIUtils.autoScrollWithData((HorizontalScrollView) view.findViewById(R.id.sciScrvBig), View.FOCUS_RIGHT);
        UIUtils.autoScrollWithData((HorizontalScrollView) view.findViewById(R.id.sciScrvSmall), View.FOCUS_RIGHT);
    }

    private void initButton(){
        currKeyboard = keyboard1;
        keyboard = new ArrayList<>();
        keyboard.add((Button) view.findViewById(R.id.sciBtn25));
        keyboard.add((Button) view.findViewById(R.id.sciBtn26));
        keyboard.add((Button) view.findViewById(R.id.sciBtn27));
        keyboard.add((Button) view.findViewById(R.id.sciBtn28));
        keyboard.add((Button) view.findViewById(R.id.sciBtn29));
        keyboard.add((Button) view.findViewById(R.id.sciBtn30));
        keyboard.add((Button) view.findViewById(R.id.sciBtn31));
        keyboard.add((Button) view.findViewById(R.id.sciBtn32));
        keyboard.add((Button) view.findViewById(R.id.sciBtn33));
        keyboard.add((Button) view.findViewById(R.id.sciBtn34));

        view.findViewById(R.id.sciBtn0).setOnClickListener(this);
        view.findViewById(R.id.sciBtn1).setOnClickListener(this);
        view.findViewById(R.id.sciBtn2).setOnClickListener(this);
        view.findViewById(R.id.sciBtn3).setOnClickListener(this);
        view.findViewById(R.id.sciBtn4).setOnClickListener(this);
        view.findViewById(R.id.sciBtn5).setOnClickListener(this);
        view.findViewById(R.id.sciBtn6).setOnClickListener(this);
        view.findViewById(R.id.sciBtn7).setOnClickListener(this);
        view.findViewById(R.id.sciBtn8).setOnClickListener(this);
        view.findViewById(R.id.sciBtn9).setOnClickListener(this);
        view.findViewById(R.id.sciBtn10).setOnClickListener(this);
        view.findViewById(R.id.sciBtn11).setOnClickListener(this);
        view.findViewById(R.id.sciBtn12).setOnClickListener(this);
        view.findViewById(R.id.sciBtn13).setOnClickListener(this);
        view.findViewById(R.id.sciBtn14).setOnClickListener(this);
        view.findViewById(R.id.sciBtn15).setOnClickListener(this);
        view.findViewById(R.id.sciBtn16).setOnClickListener(this);
        view.findViewById(R.id.sciBtn17).setOnClickListener(this);
        view.findViewById(R.id.sciBtn18).setOnClickListener(this);
        view.findViewById(R.id.sciBtn19).setOnClickListener(this);
        view.findViewById(R.id.sciBtn20).setOnClickListener(this);
        view.findViewById(R.id.sciBtn21).setOnClickListener(this);
        view.findViewById(R.id.sciBtn22).setOnClickListener(this);
        view.findViewById(R.id.sciBtn23).setOnClickListener(this);
        view.findViewById(R.id.sciBtn24).setOnClickListener(this);
        view.findViewById(R.id.sciBtn25).setOnClickListener(this);
        view.findViewById(R.id.sciBtn26).setOnClickListener(this);
        view.findViewById(R.id.sciBtn27).setOnClickListener(this);
        view.findViewById(R.id.sciBtn28).setOnClickListener(this);
        view.findViewById(R.id.sciBtn29).setOnClickListener(this);
        view.findViewById(R.id.sciBtn30).setOnClickListener(this);
        view.findViewById(R.id.sciBtn31).setOnClickListener(this);
        view.findViewById(R.id.sciBtn32).setOnClickListener(this);
        view.findViewById(R.id.sciBtn33).setOnClickListener(this);
        view.findViewById(R.id.sciBtn34).setOnClickListener(this);

        rad = view.findViewById(R.id.sciBtn26);
        deg = view.findViewById(R.id.sciBtn25);
    }
    private void initTextView(){
        expr = view.findViewById(R.id.sciTvSmall);
        number = view.findViewById(R.id.sciTvBig);
    }
    private void changeKeyboard(){
        if(currKeyboard == keyboard1){
            currKeyboard = keyboard2;
            if(state){
                UIUtils.setBackgroundColor(rad,R.color.colorBlue);
                UIUtils.setBackgroundColor(deg,R.drawable.mybutton2);
            }else{
                UIUtils.setBackgroundColor(rad,R.drawable.mybutton2);
                UIUtils.setBackgroundColor(deg,R.color.colorBlue);
            }
        } else {
            currKeyboard = keyboard1;
            UIUtils.setBackgroundColor(rad,R.drawable.mybutton2);
            UIUtils.setBackgroundColor(deg,R.drawable.mybutton2);
        }
        int i = 0;
        for(Button b : keyboard){
            b.setText(currKeyboard[i++]);
        }
    }


    @Override
    public void onClick(View view) {
        String in = ((Button)view).getText().toString();
        switch (in){
            case "↑":
                changeKeyboard();
                break;
            case "rad":
                //设置状态
                state = true;
                UIUtils.setBackgroundColor(rad,R.color.colorBlue);
                UIUtils.setBackgroundColor(deg,R.drawable.mybutton2);
                science.input(in);
                break;
            case "deg":
                //设置状态
                state = false;
                UIUtils.setBackgroundColor(rad,R.drawable.mybutton2);
                UIUtils.setBackgroundColor(deg,R.color.colorBlue);
                science.input(in);
                break;
            default:
                String tag = (String) view.getTag();
                if("NB".equals(tag)){
                    science.input(Keyboard.NB);
                } else if("Clear".equals(tag)){
                    science.input(Keyboard.CLR);
                } else{
                    science.input(in);
                }
                break;
        }
        update();
    }

    private void update(){
        ScienceCalResult result = science.output();
        expr.setText(result.getExpr());
        number.setText(result.getResult());
    }
}
