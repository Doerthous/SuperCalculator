package com.sc.calculate.loan;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import com.sc.R;
import com.sc.common.record.RecordActivity;
import com.sc.ui.MainActivity;
import com.sc.utity.DialogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.text.DecimalFormat;


public class LoanActivity extends AppCompatActivity{
    //默认汇率
    final double busnDefaultRate1=4.35;
    final double busnDefaultRate2=4.75;
    final double busnDefaultRate3=4.90;
    final double fundDefaultRate1=2.75;
    final double fundDefaultRate2=3.25;
    //声明借贷计算涉及的操作数
    double busnLoanMoney;       //商业贷款金额，单位：元
    double fundLoanMoney;      //公积金贷款金额，单位：元
    double loanMonth;              //贷款月数，单位：月
    double busnLoanRate;       //商业贷款月利率,单位：月利率
    double fundLoanRate;        //公积金贷款月利率,单位：月利率
    static int payMode;                  //还款方式，0：等额本息；1：等额本金
    ArrayList<Double> monthPayList=new ArrayList<>();//月供，单位：元，获取第n个月的月供调用monthPay.get(n-1)即可
    ArrayList<String> surplusPayList=new ArrayList<>();//剩余
    ArrayList<String> monthList=new ArrayList<>();  //月份列表
    ArrayList<String> showMonthPayList=new ArrayList<>();  //月供
    double interestAll;         //利息总计，单位：元
    double baseAndInterestAll; //本息总计，单位：元
    //声明与控件关联的变量
    EditText editText1Busnloan;     //商业借贷金额编辑框
    EditText editText2Busnloan;     //商业借贷年限编辑框
    EditText editText3Busnloan;     //商业借贷利率编辑框
    EditText editText1Fundloan;     //公积金借贷金额编辑框
    EditText editText2Fundloan;     //公积金借贷年限编辑框
    EditText editText3Fundloan;     //公积金借贷利率编辑框
    EditText editText1Combloan;     //组合借贷商业借贷金额编辑框
    EditText editText2Combloan;     //组合借贷公积金借贷金额编辑框
    EditText editText3Combloan;     //组合借贷年限编辑框
    EditText editText4Combloan;     //组合借贷商业借贷利率编辑框
    EditText editText5Combloan;     //组合借贷公积金借贷利率编辑框
    TextView textView5Loan;          //利息总计
    TextView textView6Loan;          //本息总计
    TextView textView7Loan;          //月供
    TabHost tabhost;                  //TabHost
    Spinner spinner1;                 //Spinner
    Spinner spinner2;
    Spinner spinner3;
    //定义数据格式
    DecimalFormat df;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);
        //隐藏系统标题栏
        getSupportActionBar().hide();
        //绑定控件
        editText1Busnloan =(EditText)findViewById(R.id.editText1Busnloan);  //商业借贷金额编辑框
        editText2Busnloan =(EditText)findViewById(R.id.editText2Busnloan);  //商业借贷年限编辑框
        editText3Busnloan =(EditText)findViewById(R.id.editText3Busnloan);  //商业借贷利率编辑框
        editText1Fundloan =(EditText)findViewById(R.id.editText1Fundloan);  //公积金借贷金额编辑框
        editText2Fundloan =(EditText)findViewById(R.id.editText2Fundloan);  //公积金借贷年限编辑框
        editText3Fundloan =(EditText)findViewById(R.id.editText3Fundloan);  //公积金借贷利率编辑框
        editText1Combloan =(EditText)findViewById(R.id.editText1Combloan);  //组合借贷商业借贷金额编辑框
        editText2Combloan =(EditText)findViewById(R.id.editText2Combloan);  //组合借贷公积金借贷金额编辑框
        editText3Combloan =(EditText)findViewById(R.id.editText3Combloan);  //组合借贷年限编辑框
        editText4Combloan =(EditText)findViewById(R.id.editText4Combloan);  //组合借贷商业借贷利率编辑框
        editText5Combloan =(EditText)findViewById(R.id.editText5Combloan);  //组合借贷公积金借贷利率编辑框
        textView5Loan = (TextView) findViewById(R.id.textView5Loan);          //利息总计
        textView6Loan = (TextView) findViewById(R.id.textView6Loan);          //本息总计
        textView7Loan = (TextView) findViewById(R.id.textView7Loan);          //月供
        tabhost = (TabHost) findViewById(R.id.tabhostLoan);                     //TabHost
        spinner1=(Spinner) findViewById(R.id.spinner1Busnloan);                //Spinner
        spinner2=(Spinner) findViewById(R.id.spinner1Fundloan);
        spinner3=(Spinner) findViewById(R.id.spinner1Combloan);
        //初始化spinner
        spinnerInit(spinner1);
        spinnerInit(spinner2);
        spinnerInit(spinner3);
        //初始化TabHost
        tabhost.setup();
        tabhost.addTab(tabhost.newTabSpec("first").setIndicator("商业贷款").setContent(R.id.tabBusnloan));
        tabhost.addTab(tabhost.newTabSpec("second").setIndicator("公积金贷款").setContent(R.id.tabFundloan));
        tabhost.addTab(tabhost.newTabSpec("third").setIndicator("组合贷款").setContent(R.id.tabCombloan));

        for (int i = 0;i < tabhost.getTabWidget().getChildCount(); i++){
            TabWidget view = tabhost.getTabWidget();
            TextView tv = (TextView) tabhost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextSize(16);
            tv.setTextColor(Color.parseColor("#F8F8F8"));
            /*tv.setTypeface(Typeface.SERIF, 2);*/
        }// 设置字体和风格 

        //初始化数据格式
        df = new DecimalFormat("#0.00");
        //商业借贷面板年限编辑框的事件监听函数
        editText2Busnloan.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (editText2Busnloan.length() != 0) {
                    if(editText2Busnloan.getText().toString().substring(0,1).equals(".")) {
                        editText2Busnloan.setText("0.");    //如果用户第一个字符输入“.”，则自动转换为“0.”
                        editText2Busnloan.setSelection(2);   //将光标移动到末尾
                    }
                    loanMonth = (Double.valueOf(editText2Busnloan.getText().toString())) * 12;
                    if (loanMonth <= 12)
                        editText3Busnloan.setText(Double.toString(busnDefaultRate1));
                    else if (loanMonth > 12 && loanMonth <= 60)
                        editText3Busnloan.setText(Double.toString(busnDefaultRate2));
                    else
                        editText3Busnloan.setText(Double.toString(busnDefaultRate3));
                }
            }
        });
        //公积金借贷面板年限编辑框的事件监听函数
        editText2Fundloan.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(editText2Fundloan.length()!=0) {
                    if(editText2Fundloan.getText().toString().substring(0,1).equals(".")) {
                        editText2Fundloan.setText("0.");
                        editText2Fundloan.setSelection(2);
                    }
                    loanMonth = (Double.valueOf(editText2Fundloan.getText().toString())) * 12;
                    if (loanMonth <= 60)
                        editText3Fundloan.setText(Double.toString(fundDefaultRate1));
                    else
                        editText3Fundloan.setText(Double.toString(fundDefaultRate2));
                }
            }
        });
        //组合借贷面板年限编辑框的事件监听函数
        editText3Combloan.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(editText3Combloan.length()!=0) {
                    if(editText3Combloan.getText().toString().substring(0,1).equals(".")) {
                        editText3Combloan.setText("0.");
                        editText3Combloan.setSelection(2);
                    }
                    loanMonth = (Double.valueOf(editText3Combloan.getText().toString())) * 12;
                    if (loanMonth <= 12) {
                        editText4Combloan.setText(Double.toString(busnDefaultRate1));
                        editText5Combloan.setText(Double.toString(fundDefaultRate1));
                    } else if (loanMonth > 12 && loanMonth <= 60) {
                        editText4Combloan.setText(Double.toString(busnDefaultRate2));
                        editText5Combloan.setText(Double.toString(fundDefaultRate1));
                    } else {
                        editText4Combloan.setText(Double.toString(busnDefaultRate3));
                        editText5Combloan.setText(Double.toString(fundDefaultRate2));
                    }
                }
            }
        });
    }


    //计算Button
    public void button1LoanWasClicked(View v){
        //数据初始化
        DataInit();
        //输入检测
        if(InputNullHandler()==true){
            //获取数据
            GetData();
            //初始化月供数组列表
            for(int i=0;i<loanMonth;i++)  monthPayList.add(i,0.00);
            //计算
            switch (tabhost.getCurrentTabTag()) {
                case "first":
                    Calculate(busnLoanMoney, loanMonth, busnLoanRate, payMode);
                    if(RecordActivity.recordState==2) {
                        //保存一条记录
                        String recordStr;
                        recordStr = "record begin\n商业贷款\n";
                        recordStr += "金额：";
                        recordStr += df.format(busnLoanMoney);
                        recordStr += "元\n";
                        recordStr += "年利率：";
                        recordStr += (busnLoanRate * 100*12);
                        recordStr += "%\n";
                        recordStr += "年限：";
                        recordStr += loanMonth/12;
                        recordStr += "年\n";
                        recordStr += "还款方式：";
                        if (payMode == 0) {
                            recordStr += "等额本息\n\n";
                            recordStr += "月供：";
                            recordStr += df.format(monthPayList.get(0));
                            recordStr += "元\n";
                            recordStr += "利息总计：";
                            recordStr += df.format(interestAll);
                            recordStr += "元\n";
                            recordStr += "本息总计：";
                            recordStr += df.format(baseAndInterestAll);
                            recordStr += "元\nrecord end\n";
                        }
                        else {
                            recordStr += "等额本金\n\n";
                            recordStr += "利息总计：";
                            recordStr += df.format(interestAll);
                            recordStr += "元\n";
                            recordStr += "本息总计：";
                            recordStr += df.format(baseAndInterestAll);
                            recordStr += "元\nrecord end\n";
                        }
                        RecordActivity.record(recordStr);
                    }
                    break;
                case "second":
                    Calculate(fundLoanMoney, loanMonth, fundLoanRate, payMode);
                    if(RecordActivity.recordState==2) {
                        //保存一条记录
                        String recordStr;
                        recordStr = "record begin\n公积金贷款\n";
                        recordStr += "金额：";
                        recordStr += df.format(fundLoanMoney);
                        recordStr += "元\n";
                        recordStr += "年利率：";
                        recordStr += (fundLoanRate * 100 * 12);
                        recordStr += "%\n";
                        recordStr += "年限：";
                        recordStr += loanMonth / 12;
                        recordStr += "年\n";
                        recordStr += "还款方式：";
                        if (payMode == 0) {
                            recordStr += "等额本息\n\n";
                            recordStr += "月供：";
                            recordStr += df.format(monthPayList.get(0));
                            recordStr += "元\n";
                            recordStr += "利息总计：";
                            recordStr += df.format(interestAll);
                            recordStr += "元\n";
                            recordStr += "本息总计：";
                            recordStr += df.format(baseAndInterestAll);
                            recordStr += "元\nrecord end\n";
                        } else {
                            recordStr += "等额本金\n\n";
                            recordStr += "利息总计：";
                            recordStr += df.format(interestAll);
                            recordStr += "元\n";
                            recordStr += "本息总计：";
                            recordStr += df.format(baseAndInterestAll);
                            recordStr += "元\nrecord end\n";
                        }
                        RecordActivity.record(recordStr);
                    }
                    break;
                case "third":
                    Calculate(busnLoanMoney, loanMonth, busnLoanRate, payMode);
                    Calculate(fundLoanMoney, loanMonth, fundLoanRate, payMode);
                    //保存一条记录
                        if(RecordActivity.recordState==2) {
                            //保存一条记录
                            String recordStr;
                            recordStr = "record begin\n组合贷款\n";
                            recordStr += "金额：";
                            recordStr += df.format(busnLoanMoney+fundLoanMoney);
                            recordStr += "元\n";
                            recordStr += "年利率：";
                            recordStr += (busnLoanRate * 100 * 12);
                            recordStr += "%，";
                            recordStr += (fundLoanRate * 100 * 12);
                            recordStr += "%\n";
                            recordStr += "年限：";
                            recordStr += loanMonth / 12;
                            recordStr += "年\n";
                            recordStr += "还款方式：";
                            if (payMode == 0) {
                                recordStr += "等额本息\n\n";
                                recordStr += "月供：";
                                recordStr += df.format(monthPayList.get(0));
                                recordStr += "元\n";
                                recordStr += "利息总计：";
                                recordStr += df.format(interestAll);
                                recordStr += "元\n";
                                recordStr += "本息总计：";
                                recordStr += df.format(baseAndInterestAll);
                                recordStr += "元\nrecord end\n";
                            } else {
                                recordStr += "等额本金\n\n";
                                recordStr += "利息总计：";
                                recordStr += df.format(interestAll);
                                recordStr += "元\n";
                                recordStr += "本息总计：";
                                recordStr += df.format(baseAndInterestAll);
                                recordStr += "元\nrecord end\n";
                            }
                            RecordActivity.record(recordStr);
                        }
                    break;
            }
            //显示结果
            ShowResult(payMode);
        };

    }


    //获取数据
    public void GetData(){
        switch (tabhost.getCurrentTabTag()) {
            case "first":
                //获取贷款金额,单位：元
                busnLoanMoney = (Double.valueOf(editText1Busnloan.getText().toString())) * 10000;
                //获取贷款月数,单位：月
                loanMonth = (Double.valueOf(editText2Busnloan.getText().toString())) * 12;
                //获取月利率，单位：月利率
                busnLoanRate = ((Double.valueOf(editText3Busnloan.getText().toString())) / 100) / 12;
                break;
            case "second":
                //获取贷款金额,单位：元
                fundLoanMoney = (Double.valueOf(editText1Fundloan.getText().toString())) * 10000;
                //获取贷款月数,单位：月
                loanMonth = (Double.valueOf(editText2Fundloan.getText().toString())) * 12;
                //获取月利率，单位：月利率
                fundLoanRate = ((Double.valueOf(editText3Fundloan.getText().toString())) / 100) / 12;
                break;
            case "third":
                //获取商业贷款金额,单位：元
                busnLoanMoney = (Double.valueOf(editText1Combloan.getText().toString())) * 10000;
                //获取公积金贷款金额,单位：元
                fundLoanMoney = (Double.valueOf(editText2Combloan.getText().toString())) * 10000;
                //获取贷款月数,单位：月
                loanMonth = (Double.valueOf(editText3Combloan.getText().toString())) * 12;
                //获取商业贷款月利率，单位：月利率
                busnLoanRate = ((Double.valueOf(editText4Combloan.getText().toString())) / 100) / 12;
                //获取公积金贷款月利率，单位：月利率
                fundLoanRate = ((Double.valueOf(editText5Combloan.getText().toString())) / 100) / 12;
                break;
        }
    }


    //通用借贷计算
    public void Calculate(double loanmoney,double loanmonth,double loanrate,int paymode){
        //等额本息模式
        if (paymode == 0) {
            double temp1, temp2, monthpay;
            temp1 = Math.pow(loanrate + 1, loanmonth);
            temp1 *= loanmoney * loanrate;
            temp2 = Math.pow(1 + loanrate, loanmonth) - 1;
            monthpay = temp1 / temp2;
            //计算月供，保存到数组队列的第一个数组元素中，因为等额本息模式下每个月月供相等，因此只保存一次即可
            monthPayList.set(0, monthPayList.get(0) + monthpay);
            //计算本息总计
            baseAndInterestAll += monthpay * loanmonth;
            //计算利息总计
            interestAll += (baseAndInterestAll - loanmoney);
        }
        //等额本金模式
        else {
            double temp, monthpay;
            for (int n = 1; n <= loanmonth; n++) {
                //计算月供,保存到数组队列中
                temp = loanmoney / loanmonth;
                monthpay = temp + (loanmoney - (n - 1) * temp) * loanrate;
                monthPayList.set(n - 1, monthPayList.get(n - 1) + monthpay);
                //计算本息总计
                baseAndInterestAll += monthpay;
            }
            //计算利息总计
            interestAll += (baseAndInterestAll - loanmoney);
        }
    }


    //显示结果
    public void ShowResult(final int paymode){
        //输出利息总计，单位：万元
        textView5Loan.setText(df.format(interestAll / 10000) + "万元");
        //输出本息总计，单位：万元
        textView6Loan.setText(df.format(baseAndInterestAll / 10000) + "万元");
        //输出月供，单位：元
        //等额本息
        if (paymode == 0)
            textView7Loan.setText(df.format(monthPayList.get(0)) + "元");
            //等额本金
        else {
            textView7Loan.setText("查看详情");
            textView7Loan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(payMode==1){
                        //弹出对话框
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoanActivity.this);
                        //设置内容为自定义View
                        final LinearLayout dialog = (LinearLayout)getLayoutInflater().inflate(R.layout.fragment_loan_result, null);
                        builder.setView(dialog);
                        monthList.clear();
                        surplusPayList.clear();
                        showMonthPayList.clear();
                        double surplus=baseAndInterestAll;
                        for (int i=0;i<loanMonth;i++){
                            double monthpay=monthPayList.get(i);
                            surplus=surplus - monthPayList.get(i);
                            monthList.add(Integer.valueOf(i+1).toString());
                            showMonthPayList.add(df.format(Double.valueOf(monthpay)).toString());
                            surplusPayList.add(df.format(Double.valueOf(surplus)).toString());
                        }
                        ListView listView=dialog.findViewById(R.id.ListViewLoan);
                        listView.setAdapter(
                                new SimpleAdapter(LoanActivity.this, function(), R.layout.li_loan,
                                        new String[] {"month", "monthpay","surplus"},
                                        new int[] {R.id.textView1LoanList, R.id.textView2LoanList,R.id.textView3LoanList,})
                        );
                        //显示对话框
                        builder.setCancelable(true);
                        final AlertDialog mydialog = builder.create();
                        mydialog.show();
                        ImageButton imageButton=(ImageButton)dialog.findViewById(R.id.imageButtonLoanResult);
                        imageButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mydialog.dismiss();
                            }
                        });
                    }
                }
            });
        }
    }

    public List<HashMap<String,String>> function(){
        List<HashMap<String, String>> data = new ArrayList<>();
        for(int i=0;i<monthList.size();i++){
            HashMap<String, String> map = new HashMap<>();
            map.put("month", monthList.get(i));
            map.put("monthpay", showMonthPayList.get(i));
            map.put("surplus", surplusPayList.get(i));
            data.add(map);
        }
        return data;
    }


    //spinner控件初始化及事件监听
    public void  spinnerInit( Spinner spinner){
        final List<String> list = new ArrayList<>();
        //添加子项
        list .add("等额本息（每月等额还款）");
        list .add("等额本金（每月递减还款）");
        //将可选内容list与ArrayAdapter相连接
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.simple_spinner_text, list );
        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将Adapter添加到Spinner
        spinner.setAdapter(adapter);
        //添加spinner监听事件
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if(list.get(arg2)=="等额本息（每月等额还款）")
                    payMode=0;
                else
                    payMode=1;
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                payMode=0;
            }
        });
    }


    //全局数据初始化
    public void DataInit(){
        interestAll=0;
        baseAndInterestAll=0;
        monthPayList.clear();
    }

    //数据检测
    public boolean InputNullHandler() {
        switch (tabhost.getCurrentTabTag()){
            case "first":
                if (editText1Busnloan.length() == 0)
                //Toast.makeText(getApplicationContext(), "请输入贷款金额", Toast.LENGTH_SHORT).show();
                {
                    DialogUtil.ShowMsg("请输入贷款金额",LoanActivity.this);
                    return false;
                }
                if (editText2Busnloan.length() == 0)
                // Toast.makeText(getApplicationContext(), "请输入贷款年限", Toast.LENGTH_SHORT).show();
                {
                    DialogUtil.ShowMsg("请输入贷款年限",LoanActivity.this);
                    return false;
                }
                if (Double.valueOf(editText2Busnloan.getText().toString()) == 0)
                // Toast.makeText(getApplicationContext(), "请输入贷款年限", Toast.LENGTH_SHORT).show();
                {
                    DialogUtil.ShowMsg("贷款年限不能为0",LoanActivity.this);
                    return false;
                }
                if (editText3Busnloan.length() == 0)
                // Toast.makeText(getApplicationContext(), "请输入贷款利率", Toast.LENGTH_SHORT).show();
                {
                    DialogUtil.ShowMsg("请输入贷款利率",LoanActivity.this);
                    return false;
                }
                break;
            case "second":
                if (editText1Fundloan.length() == 0)
                // Toast.makeText(getApplicationContext(), "请输入贷款金额", Toast.LENGTH_SHORT).show();
                {
                    DialogUtil.ShowMsg("请输入贷款金额",LoanActivity.this);
                    return false;
                }
                if (editText2Fundloan.length() == 0)
                {
                    DialogUtil.ShowMsg("请输入贷款年限",LoanActivity.this);
                    return false;
                }
                if (Double.valueOf(editText2Fundloan.getText().toString()) == 0)
                {
                    DialogUtil.ShowMsg("贷款年限不能为0",LoanActivity.this);
                    return false;
                }
                if (editText3Fundloan.length() == 0)
                {
                    DialogUtil.ShowMsg("请输入贷款利率",LoanActivity.this);
                    return false;
                }
                break;
            case "third":
                if (editText1Combloan.length() == 0)
                {
                    DialogUtil.ShowMsg("请输入商业贷款金额",LoanActivity.this);
                    return false;
                }
                if (editText2Combloan.length() == 0)
                {
                    DialogUtil.ShowMsg("请输入公积金贷款金额",LoanActivity.this);
                    return false;
                }
                if (editText3Combloan.length() == 0)
                {
                    DialogUtil.ShowMsg("请输入贷款年限",LoanActivity.this);
                    return false;
                }
                if (Double.valueOf(editText3Combloan.getText().toString()) == 0)
                {
                    DialogUtil.ShowMsg("贷款年限不能为0",LoanActivity.this);
                    return false;
                }
                if (editText4Combloan.length() == 0)
                {
                    DialogUtil.ShowMsg("请输入商业贷款利率",LoanActivity.this);
                    return false;
                }
                if (editText5Combloan.length() == 0)
                {
                    DialogUtil.ShowMsg("请输入公积金贷款利率",LoanActivity.this);
                    return false;
                }
                break;
        }
        return true;
    }


    //返回到主界面按钮
    public void BackBtnClicked(View view){
        Intent intent = new Intent();
        //(当前Activity，目标Activity)
        intent.setClass(LoanActivity.this, MainActivity.class);
        //销毁当前活动
        this.finish();
        //跳转到主界面活动
        startActivity(intent);
    }

    //暂停按钮
    public void BeginOrPauseBtnWasClicked(View view){
        DialogUtil.PauseBtnWasClicked(LoanActivity.this,(ImageButton)findViewById(R.id.imageButton1Loan));
    }

    //停止按钮
    public  void  StopBtnWasClicked(View view) {
        DialogUtil.StopBtnWasClicked(LoanActivity.this,(ImageButton)findViewById(R.id.imageButton1Loan));
    }


}

