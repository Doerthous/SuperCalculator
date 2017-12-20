package com.sc.ui;


import android.support.v4.app.Fragment;

import com.sc.R;
import com.sc.ui.exchangerate.ExchangeRateFragment;
import com.sc.ui.general.ScienceFragment;
import com.sc.ui.general.StandardFragment;
import com.sc.ui.programmer.ProgrammerFragment;
import com.sc.ui.relation.RelationFragment;
import com.sc.ui.tax.TaxFragment;
import com.sc.ui.time.TimeFragment;
import com.sc.ui.unit.UnitFragment;

import com.sc.ui.plot.PlotFragment;

import layout.MatrixFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends FragmentBase {

    public MainFragment() {
        fragmentId = R.layout.fragment_main;
    }

    @Override
    public void init() {
        view.findViewById(R.id.mainLlvRate).setTag(EXCHANGE_RATE);
        view.findViewById(R.id.mainLlvTime).setTag(TIME);
        view.findViewById(R.id.mainLlvTax).setTag(TAX);
        view.findViewById(R.id.mainLlvRelation).setTag(RELATION);
        view.findViewById(R.id.mainLlvStandard).setTag(STANDARD);
        view.findViewById(R.id.mainLlvScience).setTag(SCIENCE);
        view.findViewById(R.id.mainLlvProgrammer).setTag(PROGRAMMER);
        view.findViewById(R.id.mainLlvUnit).setTag(UNIT);
        view.findViewById(R.id.mainLlvLoan).setTag(LOAN);
        view.findViewById(R.id.mainLlvPlot).setTag(PLOT);
        view.findViewById(R.id.mainLlvMatrix).setTag(MATRIX);
    }

    // 模块工厂
    public static final String STANDARD =       "标准计算器";
    public static final String SCIENCE =        "科学计算器";
    public static final String LOAN =           "借贷计算器";
    public static final String EXCHANGE_RATE =  "汇率计算器";
    public static final String TAX =            "税务计算器";
    public static final String RELATION =       "辈分计算器";
    public static final String UNIT =           "单位换算器";
    public static final String TIME =           "时间计算器";
    public static final String PROGRAMMER =     "程序员计算器";
    public static final String PLOT =           "绘图计算器";
    public static final String MATRIX =         "矩阵计算器";
    public static Fragment getModule(String name){
        if(TIME.equals(name)){
            return new TimeFragment();
        } else if (EXCHANGE_RATE.equals(name)) {
            return new ExchangeRateFragment();
        } else if(TAX.equals(name)){
            return new TaxFragment();
        } else if(RELATION.equals(name)){
            return new RelationFragment();
        } else if(STANDARD.equals(name)){
            return new StandardFragment();
        } else if(SCIENCE.equals(name)){
            return new ScienceFragment();
        } else if(PROGRAMMER.equals(name)){
            return new ProgrammerFragment();
        } else if(UNIT.equals(name)){
            return new UnitFragment();
        } else if(LOAN.equals(name)){
            return new UnitFragment();
        } else if(PLOT.equals(name)){
            return new PlotFragment();
        } else if(MATRIX.equals(name)){
            return new MatrixFragment();
        }
        return null;
    }
}
