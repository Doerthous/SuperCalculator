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
import com.sc.calculate.general.Standard;
import com.sc.datastructrue.records.StandardCalResult;
import com.sc.utity.Keyboard;
import com.sc.utity.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StandardFragment extends Fragment implements View.OnClickListener{
    View view;
    List<Button> keyboard;
    TextView tvSmall;
    TextView tvBig;
    //
    Standard standard = new Standard();
    public StandardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_standard, container, false);
        initScrollView();
        initTextView();
        initButton();
        update();
        return view;
    }

    private void initScrollView(){
        UIUtils.autoScrollWithData((HorizontalScrollView) view.findViewById(R.id.stdScrvBig), View.FOCUS_RIGHT);
        UIUtils.autoScrollWithData((HorizontalScrollView) view.findViewById(R.id.stdScrvSmall), View.FOCUS_RIGHT);
    }
    private void initTextView(){
        tvSmall = view.findViewById(R.id.stdTvSmall);
        tvBig = view.findViewById(R.id.stdTvBig);
        tvSmall.setText("");
        tvBig.setText("");
    }
    private void initButton(){
        keyboard = new ArrayList<>();
        keyboard.add((Button) view.findViewById(R.id.stdBtn1));
        keyboard.add((Button) view.findViewById(R.id.stdBtn2));
        keyboard.add((Button) view.findViewById(R.id.stdBtn3));
        keyboard.add((Button) view.findViewById(R.id.stdBtn4));
        keyboard.add((Button) view.findViewById(R.id.stdBtn5));
        keyboard.add((Button) view.findViewById(R.id.stdBtn6));
        keyboard.add((Button) view.findViewById(R.id.stdBtn7));
        keyboard.add((Button) view.findViewById(R.id.stdBtn8));
        keyboard.add((Button) view.findViewById(R.id.stdBtn9));
        keyboard.add((Button) view.findViewById(R.id.stdBtn10));
        keyboard.add((Button) view.findViewById(R.id.stdBtn11));
        keyboard.add((Button) view.findViewById(R.id.stdBtn12));
        keyboard.add((Button) view.findViewById(R.id.stdBtn13));
        keyboard.add((Button) view.findViewById(R.id.stdBtn14));
        keyboard.add((Button) view.findViewById(R.id.stdBtn15));
        keyboard.add((Button) view.findViewById(R.id.stdBtn16));
        keyboard.add((Button) view.findViewById(R.id.stdBtn17));
        keyboard.add((Button) view.findViewById(R.id.stdBtn18));
        for(Button b : keyboard){
            b.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        String tag = (String) view.getTag();
        if("Clear".equals(tag)){
            standard.input(Keyboard.CLR);
        } else {
            standard.input(((Button) view).getText().toString());
        }
        update();
    }

    private void update(){
        StandardCalResult result = standard.output();
        tvSmall.setText(result.getExpr());
        tvBig.setText(result.getResult());
    }
}
