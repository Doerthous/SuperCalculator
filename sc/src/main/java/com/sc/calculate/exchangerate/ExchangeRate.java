package com.sc.calculate.exchangerate;

import com.sc.calculate.ModuleBase;
import com.sc.datastructrue.records.ExchangeRateCalResult;
import com.sc.utity.ThirdPartAPI;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Doerthous on 2017/6/27.
 */

public class ExchangeRate extends ModuleBase {
    public static boolean hasInited(){
        return inited;
    }
    public static boolean inited = false;
    private static DecimalFormat dcmFmt = new DecimalFormat("0.0000");
    public static void init(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //new ExchangeRate().getRates("人民币(CNY)","1.0");
                /*
                    bug
                 */
                ExchangeRate.inited = true;
            }
        }).start();
    }

    public List<HashMap<String, String>> getRates(String src, String money){
        Double m = Double.valueOf(money);
        HashMap<String, Double> rates = ThirdPartAPI.getRates(src);
        List<HashMap<String, String>> data = new ArrayList<>();
        for(String name: rates.keySet()){
            HashMap<String, String> map = new HashMap<>();
            map.put("tvName", name);
            map.put("tvValue", String.valueOf(dcmFmt.format(rates.get(name)*m)));
            data.add(map);
        }
        return data;
    }

    private String srcCurrency;
    private String objCurrency;
    private String srcMoney;

    public void input(String data){
        String[] d = data.split(",");
        if(d.length == 2){
            srcCurrency = d[0];
            srcMoney = d[1];
            objCurrency = "";
        } else {
            srcCurrency = d[0];
            srcMoney = d[1];
            objCurrency = d[2];
        }
    }
    @Override
    public ExchangeRateCalResult output(){
        ExchangeRateCalResult op;
        if(!"".equals(objCurrency)) {
            Double rate = ThirdPartAPI.getRate(srcCurrency, objCurrency);
            Double m = Double.valueOf(srcMoney);
/*        String[] t = new String[2];
        t[0] = dcmFmt.format(m).toString() +  src + "=";
        t[1] = dcmFmt.format(m*rate).toString() + obj;
        //t += dcmFmt.format(m).toString() +  obj + "=" + dcmFmt.format(m/rate).toString() + src + "";*/
            op = new ExchangeRateCalResult(srcCurrency, objCurrency, m, m * rate);
            writeRecord(op);
        } else {
            Double m = Double.valueOf(srcMoney);
            HashMap<String, Double> rates = ThirdPartAPI.getRates(srcCurrency);
            List<HashMap<String, String>> data = new ArrayList<>();
            for(String name: rates.keySet()){
                HashMap<String, String> map = new HashMap<>();
                map.put("tvName", name);
                map.put("tvValue", String.valueOf(dcmFmt.format(rates.get(name)*m)));
                data.add(map);
            }
            op = new ExchangeRateCalResult(data);
        }
        return op;
    }
    public static List<String> getCurrencyName(){
        return Arrays.asList(ThirdPartAPI.getCurrencyName());
    }
}
