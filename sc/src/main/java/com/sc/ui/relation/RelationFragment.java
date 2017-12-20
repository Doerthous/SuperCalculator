package com.sc.ui.relation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.sc.R;
import com.sc.calculate.relation.Relation;
import com.sc.datastructrue.records.RelationCalResult;
import com.sc.utity.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RelationFragment extends Fragment implements View.OnClickListener{
    View view;
    List<Button> keyboard;
    TextView rltTvRalation;
    TextView rltTvResult;
    EditText rltEtRation;
    Button gender;
    Button rltBtn8;
    Button rltBtn9;
    Relation relation;
    public RelationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_relation, container, false);
        initTextView();
        initButton();
        relation = new Relation();
        //Relation.initReation();
        initScrollView();
        return view;
    }

    private void initScrollView(){
        UIUtils.autoScrollWithData((HorizontalScrollView) view.findViewById(R.id.rltScrvResultion), View.FOCUS_RIGHT);
        UIUtils.autoScrollWithData((HorizontalScrollView) view.findViewById(R.id.rltScrvResult), View.FOCUS_RIGHT);
    }

    private void initTextView(){
        rltTvRalation = view.findViewById(R.id.rltTvRalation);
        rltTvResult = view.findViewById(R.id.rltTvResult);
        rltEtRation = view.findViewById(R.id.rltEtRation);
        rltTvRalation.setText("自己");
        rltTvResult.setText("");
        rltEtRation.setText("");
    }

    private void initButton(){
        keyboard = new ArrayList<>();
        keyboard.add((Button) view.findViewById(R.id.rltBtn0));
        keyboard.add((Button) view.findViewById(R.id.rltBtn1));
        keyboard.add((Button) view.findViewById(R.id.rltBtn2));
        keyboard.add((Button) view.findViewById(R.id.rltBtn3));
        keyboard.add((Button) view.findViewById(R.id.rltBtn4));
        keyboard.add((Button) view.findViewById(R.id.rltBtn5));
        keyboard.add((Button) view.findViewById(R.id.rltBtn6));
        keyboard.add((Button) view.findViewById(R.id.rltBtn7));
        keyboard.add((Button) view.findViewById(R.id.rltBtn8));
        keyboard.add((Button) view.findViewById(R.id.rltBtn9));
        keyboard.add((Button) view.findViewById(R.id.rltBtnClear));
        keyboard.add((Button) view.findViewById(R.id.rltBtnDel));
        keyboard.add((Button) view.findViewById(R.id.rltBtnGender));
        keyboard.add((Button) view.findViewById(R.id.rltBtnExchange));
        keyboard.add((Button) view.findViewById(R.id.rltBtnEqual));
        keyboard.add((Button) view.findViewById(R.id.rltBtnCompute));

        for(Button b :keyboard){
            b.setOnClickListener(this);
        }

        rltBtn8 = view.findViewById(R.id.rltBtn8);
        rltBtn9 = view.findViewById(R.id.rltBtn9);

        gender = view.findViewById(R.id.rltBtnGender);
        UIUtils.setBackgroundColor(gender,R.color.colorBlue);
        UIUtils.disableButton(rltBtn8,R.color.colorGray);
        UIUtils.enableButton(rltBtn9,R.color.colorF8F8F8,this);
    }

    public  void setGenderLimit(){
        if(Relation.genderLimit()){
            UIUtils.disableButton(rltBtn8,R.color.colorGray);
            UIUtils.enableButton(rltBtn9,R.color.colorF8F8F8,this);
        }else{
            UIUtils.disableButton(rltBtn9,R.color.colorGray);
            UIUtils.enableButton(rltBtn8,R.color.colorF8F8F8,this);
        }
    }

    public void onClick(View view){
        String in = ((Button) view).getText().toString();
        switch (in){
            case "男生":
                ((Button) view).setText("女生");
                UIUtils.setBackgroundColor(view,R.color.colorAccent);
                UIUtils.disableButton(rltBtn9,R.color.colorGray);
                UIUtils.enableButton(rltBtn8,R.color.colorF8F8F8,this);
                relation.inputRlt(in);
                update();
                Log.e("genderLimit",String .valueOf(Relation.genderLimit()));
                setGenderLimit();

                break;
            case "女生":
                ((Button) view).setText("男生");
                UIUtils.setBackgroundColor(view,R.color.colorBlue);
                UIUtils.disableButton(rltBtn8,R.color.colorGray);
                UIUtils.enableButton(rltBtn9,R.color.colorF8F8F8,this);
                relation.inputRlt(in);
                update();
                Log.e("genderLimit",String .valueOf(Relation.genderLimit()));
                setGenderLimit();
                break;
            /*case "互换":
                break;*/
            case "查找":
                String name =  rltEtRation.getText().toString();
                relation.inputRlt(name);
                update();
                break;
            default:
                relation.inputRlt(in);
                update();
                Log.e("genderLimit",String .valueOf(Relation.genderLimit()));
                setGenderLimit();
                break;
        }

    }

    private void update(){
        RelationCalResult op = relation.output();
        rltTvRalation.setText(op.getExpr());
        rltTvResult.setText(op.getResult());
    }
}


