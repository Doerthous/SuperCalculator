package com.sc.ui.tax;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.sc.R;
import com.sc.calculate.tax.Tax;
import com.sc.ui.tax.BonusFragment;
import com.sc.ui.tax.SlavaryFragment;
import com.sc.utity.UIUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaxFragment extends Fragment implements View.OnClickListener{
    View view;
    Button btnCompute;
    Button btnBonus;
    Button btnSlavary;
    TextView tvResult;
    EditText etSlavary1;
    EditText etQZD;
    Spinner spnLocale;
    EditText etSlavary2;
    EditText etBonus;

    public TaxFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tax, container, false);
        tvResult = view.findViewById(R.id.textView10TaxResult);
        initFragment();
        initButton();
        initEditText();
        initSpinner();
        return view;
    }

    private void initFragment(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.taxFlFrame, new SlavaryFragment());
        transaction.commit();
    }
    private void initButton(){
        btnCompute = view.findViewById(R.id.taxBtnCompute);
        btnCompute.setTag("Slavary");
        btnBonus = view.findViewById(R.id.taxBtnBonus);
        btnSlavary = view.findViewById(R.id.taxBtnSlavary);
        btnCompute.setOnClickListener(this);
        btnSlavary.setOnClickListener(this);
        btnBonus.setOnClickListener(this);
        UIUtils.setBackgroundColor(btnSlavary,R.color.colorBlue);
    }
    private void initEditText(){
        etSlavary1 = view.findViewById(R.id.taxEtSlavary1);
        etQZD = view.findViewById(R.id.taxEtQZD);
        etSlavary2 = view.findViewById(R.id.taxEtSlavary2);
        etBonus = view.findViewById(R.id.taxEtBonus);
    }
    private void initSpinner(){
        spnLocale = view.findViewById(R.id.taxSpnLocale);
    }

    private void showFragment(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //transaction.setCustomAnimations(enter, exit);
        transaction.replace(R.id.taxFlFrame, fragment);
        transaction.commit();
    }
    private void compute(){
        initEditText();
        if(btnCompute.getTag().equals("Slavary")){
            tvResult.setText(Tax.slavary(etSlavary1.getText().toString()));
        } else {
            tvResult.setText(
                Tax.bonous(etSlavary2.getText().toString(),
                        etBonus.getText().toString()));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.taxBtnCompute:
                compute();
                break;
            case R.id.taxBtnSlavary:
                showFragment(new SlavaryFragment());
                btnCompute.setTag("Slavary");
                UIUtils.setBackgroundColor(btnBonus,R.drawable.mybutton2);
                UIUtils.setBackgroundColor(btnSlavary,R.color.colorBlue);
                break;
            case R.id.taxBtnBonus:
                showFragment(new BonusFragment());
                btnCompute.setTag("Bonus");
                UIUtils.setBackgroundColor(btnSlavary,R.drawable.mybutton2);
                UIUtils.setBackgroundColor(btnBonus,R.color.colorBlue);
                break;
        }
    }
}
