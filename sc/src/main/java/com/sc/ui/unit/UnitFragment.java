package com.sc.ui.unit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.sc.R;
import com.sc.calculate.unit.Unit;
import com.sc.datastructrue.records.UnitCalResult;
import com.sc.utity.Keyboard;
import com.sc.utity.UIUtils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UnitFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    View view;
    Spinner physics;
    Spinner unitSrc;
    Spinner unitObj;
    TextView result;
    EditText input;
    Unit unit;

    public UnitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_unit, container, false);
        unit = new Unit();
        initButton();
        initTextView();
        initEditView();
        initSpinner();
        update();
        return view;
    }

    private void initButton(){
        view.findViewById(R.id.unitBtn0).setOnClickListener(this);
        view.findViewById(R.id.unitBtn1).setOnClickListener(this);
        view.findViewById(R.id.unitBtn2).setOnClickListener(this);
        view.findViewById(R.id.unitBtn3).setOnClickListener(this);
        view.findViewById(R.id.unitBtn4).setOnClickListener(this);
        view.findViewById(R.id.unitBtn5).setOnClickListener(this);
        view.findViewById(R.id.unitBtn6).setOnClickListener(this);
        view.findViewById(R.id.unitBtn7).setOnClickListener(this);
        view.findViewById(R.id.unitBtn8).setOnClickListener(this);
        view.findViewById(R.id.unitBtn9).setOnClickListener(this);
        view.findViewById(R.id.unitBtn10).setOnClickListener(this);
        view.findViewById(R.id.unitBtn11).setOnClickListener(this);
    }
    private void initTextView(){
        result = view.findViewById(R.id.unitTvResult);
    }
    private void initSpinner(){
        physics = view.findViewById(R.id.unitSpnPhysics);
        unitSrc = view.findViewById(R.id.unitSpnUnit1);
        unitObj = view.findViewById(R.id.unitSpnUnit2);
        // 初始化物理量
        List list = unit.getPhysicalQuantityName();
        ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_text, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        physics.setAdapter(adapter);
        physics.setOnItemSelectedListener(this);
        String p = (String) physics.getSelectedItem();
        //
        list = unit.getUnit(p);
        adapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_text, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSrc.setAdapter(adapter);
        unitSrc.setOnItemSelectedListener(this);
        unitObj.setAdapter(adapter);
        unitObj.setOnItemSelectedListener(this);
    }
    private void initEditView(){
        input = view.findViewById(R.id.unitEtInput);
        UIUtils.disableVirtualKeyboard(input);
    }

    @Override
    public void onClick(View view) {
        unit.setUnitSrc((String) unitSrc.getSelectedItem());
        unit.setUnitObj((String) unitObj.getSelectedItem());
        String tag = (String) view.getTag();
        if(Keyboard.CLR.equals(tag)){
            unit.input(Keyboard.CLR);
        } else {
            unit.input(((Button) view).getText().toString());
        }
        update();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        List list;
        if(physics == adapterView){
            String p = (String) adapterView.getSelectedItem();
            list = unit.getUnit(p);
            ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_text, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            unitSrc.setAdapter(adapter);
            unitObj.setAdapter(adapter);
        } else {
            unit.setUnitSrc((String) unitSrc.getSelectedItem());
            unit.setUnitObj((String) unitObj.getSelectedItem());
            update();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void update(){
        UnitCalResult op = unit.output();
        input.setText(op.getQuantity());
        result.setText(op.getResult());
    }
}
