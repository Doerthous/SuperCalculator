package com.sc.ui.exchangerate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sc.R;
import com.sc.calculate.exchangerate.ExchangeRate;
import com.sc.datastructrue.records.ExchangeRateCalResult;
import com.sc.utity.Utils;

import java.util.List;

public class ExchangeRateFragment extends Fragment implements View.OnClickListener{
    View view;
    TextView erTvBig;
    TextView erTvSmall;
    EditText erEtMoney;
    Spinner erSpnSrc;
    Spinner erSpnObj;
    ListView erLvOtherRate;
    ExchangeRate exchangeRate;
    public ExchangeRateFragment() {
        // Required empty public constructor
        exchangeRate = new ExchangeRate();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_exchange_rate, container, false);
        init();
        return view;
    }

    private void init(){
        initTextView();
        initButton();
        initSpinner();
        initEditText();
        initListView();
    }
    private void initButton(){
        view.findViewById(R.id.imageButtonExchExchange).setOnClickListener(this);
        view.findViewById(R.id.buttonExchCompute).setOnClickListener(this);
    }
    private void initSpinner() {
        erSpnSrc = view.findViewById(R.id.spinnerExchSrc);
        erSpnObj = view.findViewById(R.id.spinner2ExchObj);
        //第一步：添加一个下拉列表项的list，这里添加的项就是下拉列表的菜单项
        List list = ExchangeRate.getCurrencyName();
        //第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_text, list);
        //第三步：为适配器设置下拉列表下拉时的菜单样式。
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        erSpnSrc.setAdapter(adapter);
        erSpnObj.setAdapter(adapter);
    }
    private void initTextView(){
        erTvSmall = view.findViewById(R.id.textView10ExchSmall);
        erTvBig = view.findViewById(R.id.textView3ExchBig);
    }
    private void initEditText(){
        erEtMoney = view.findViewById(R.id.editTextExchMoney);
    }
    private void initListView(){
        erLvOtherRate = view.findViewById(R.id.listViewExchOtherRate);
    }

    private boolean errorCheckIsOk(){
        String money = erEtMoney.getText().toString();
        if(money.length() == 0){
            Toast.makeText(getContext(), "请输入金额", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!Utils.isNumber(money)){
            Toast.makeText(getContext(), "请输入合法的金额", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!ExchangeRate.hasInited()){
            if(!Utils.isNetworkConnected(getContext())){
                Toast.makeText(getContext(), "网络异常, 无法获取汇率", Toast.LENGTH_SHORT).show();
                return false;
            }
            Toast.makeText(getContext(), "尝试联网获取汇率中...", Toast.LENGTH_SHORT).show();
            ExchangeRate.init();
            return false;
        }
        return true;
    }
    private void update(){
        if(errorCheckIsOk()) {
            updateRate();
            updateOtherRate();
        }
    }
    private void updateRate(){
        String src = (String) erSpnSrc.getSelectedItem();
        String obj = (String) erSpnObj.getSelectedItem();
        String money = erEtMoney.getText().toString();
        exchangeRate.input(src+","+money+","+obj);
        ExchangeRateCalResult op = exchangeRate.output();
        erTvSmall.setText(op.getExpr());
        erTvBig.setText(op.getResult());
    }
    private void updateOtherRate(){
        // ;
        String src = (String) erSpnSrc.getSelectedItem();
        String money = erEtMoney.getText().toString();
        exchangeRate.input(src+","+money);
        ExchangeRateCalResult op = exchangeRate.output();
        //;
        erLvOtherRate.setAdapter(
                new SimpleAdapter(getActivity(), op.getOtherRate(), R.layout.li_exchange_other_rate,
                        new String[] {"tvName", "tvValue"},
                        new int[] {R.id.tvName, R.id.tvValue})
        );
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageButtonExchExchange:
                // 交换两个下拉框的内容
                int si = erSpnSrc.getSelectedItemPosition();
                int oi = erSpnObj.getSelectedItemPosition();
                erSpnSrc.setSelection(oi);
                erSpnObj.setSelection(si);
                break;
            case R.id.buttonExchCompute:
                update();
                break;
        }
    }
}
