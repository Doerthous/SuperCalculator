package com.sc.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sc.R;
import com.sc.calculate.exchangerate.ExchangeRate;
import com.sc.calculate.loan.LoanActivity;
import com.sc.calculate.plot.Plot;
import com.sc.calculate.relation.Relation;
import com.sc.calculate.unit.Unit;
import com.sc.common.record.RecordActivity;
import com.sc.utity.UIUtils;
import com.sc.utity.Utils;

public class MainActivity extends ActivityBase {
    ImageButton btnReturn;
    TextView tvTitle;
    RecordFragment record;
    FragmentBase setting;
    FragmentBase main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initModule();
        initFragment();
        initButton();
        initTextView();
    }

    private void initFragment(){
        main = new MainFragment();
        record = new RecordFragment();
        record.setParent(this);
        setting = new SettingFragment();
        setting.setParent(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.mainFlRightFrame, setting);
        transaction.add(R.id.mainFlFrame, main);
        transaction.commit();
    }
    private void initButton(){
        btnReturn = (ImageButton) findViewById(R.id.mainBtnReturn);
        btnReturn.setVisibility(View.INVISIBLE);
    }
    private void initTextView(){
        tvTitle = (TextView) findViewById(R.id.mainTvTitle);
        tvTitle.setText("超级计算器");
    }

    private void initModule(){
        if(!Utils.isNetworkConnected(getApplicationContext())){
            Toast.makeText(getApplicationContext(), "网络异常, 部分功能将无法使用", Toast.LENGTH_LONG).show();
        } else {
            ExchangeRate.init();
        }
        Relation.init();
        Unit.init();
    }
    public void toModule(View view){
        btnReturn.setVisibility(View.VISIBLE);
        String vn = (String)view.getTag();
        tvTitle.setText(vn);
        UIUtils.showFragment(getSupportFragmentManager(), MainFragment.getModule(vn),
                R.id.mainFlFrame, R.anim.slide_right_in, R.anim.slide_left_out);
        UIUtils.showFragment(getSupportFragmentManager(), record,
                R.id.mainFlRightFrame, R.anim.slide_right_in, R.anim.slide_left_out);
        record.setCurrentModule(vn);
    }
    public void toMain(View view){
        btnReturn.setVisibility(View.INVISIBLE);
        tvTitle.setText("超级计算器");
        UIUtils.showFragment(getSupportFragmentManager(), main,
                R.id.mainFlFrame,  R.anim.slide_left_in, R.anim.slide_right_out);
        UIUtils.showFragment(getSupportFragmentManager(), setting,
                R.id.mainFlRightFrame, R.anim.slide_right_in, R.anim.slide_left_out);
        record.stop();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mainBtnSetting:
                MainToSetting();
                break;
        }
    }

    //0：未开始；1：暂停中；2：录制中
    /*public static int recordState;*/
    //默认保存路径
    //public static String recordPath;
    /*static boolean isFirstLaunch=true;*/
    //public  static String newFileName;
    //切换到借贷计算Activity
    public void MainToLoan(View view){
        Intent intent = new Intent();
        //(当前Activity，目标Activity)
        intent.setClass(MainActivity.this, LoanActivity.class);
        startActivity(intent);
    }
    //切换到设置Activity
    public void MainToSetting(){
        Intent intent = new Intent();
        //(当前Activity，目标Activity)
        intent.setClass(MainActivity.this, RecordActivity.class);
        startActivity(intent);
    }
}
