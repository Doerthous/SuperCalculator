package com.sc.calculate.unit;

import com.sc.calculate.ModuleWithKeyboard;
import com.sc.datastructrue.records.UnitCalResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Doerthous on 2017/6/30.
 */

public class Unit extends ModuleWithKeyboard {

    public List<String> getPhysicalQuantityName(){
        List<String> l = new ArrayList<>();
        for(String key : unit.keySet()){
            l.add(key);
        }
        return l;
    }
    public List<String> getUnit(String physics){
        return unit.get(physics);
    }
    private static String transfer(String unitSrc, String unitObj, String quantity){
        return String.valueOf(Double.valueOf(quantity)*midexchange(unitSrc)/midexchange(unitObj));
    }
    private static double midexchange(String x) {
        double exg = 1;
        double a = 1000000000;
        switch (x)
        {
            //length
            case "米(m)":exg=1000;break;
            case "分米(dm)":exg=100;break;
            case "厘米(cm)":exg=10;break;
            case "千米(km)":exg=1000000;break;
            //area
            case"平方千米(km²)":exg=1000000;break;
            case"公顷(ha)":exg=10000;break;
            case"公亩(are)":exg=100;break;
            case"平方分米(dm²)":exg=0.01;break;
            case"平方厘米(cm²)":exg=0.0001;break;
            case"平方毫米(mm²)":exg=0.000001;break;
            //volume
            case"立方米(m³)":exg=1000000000;break;
            case "立方分米(dm³)":exg=1000000;break;
            case "立方厘米(cm³)":exg=1000;break;
            case "立方毫米(mm³)":exg=1;break;
            case"升(l)":exg=1000000;break;
            case"分升(dl)":exg=100000;break;
            case"毫升(ml)":exg=1000;break;
            case"厘升(cl)":exg=10000;break;
            case"公石(hl)":exg=100000000;break;
            //speed
            case "米/秒(m/s)":exg=3.6;break;
            case "千米/秒(km/s)":exg=3600;break;
            case "光速(c)":exg=1079252848.8;break;
            case"马赫(mach)":exg=1225.08;break;
            case"英里/时(mile/h)":exg=1.609344;break;
            case"英寸/秒(in/s)":exg=0.09144;break;

            //temperature
            case "华氏度(℉)":exg=-17.2222222;break;
            case "开氏度(K)":exg=-272.15;break;
            case"兰氏度(°R)":exg=-272.5944444;break;
            case"列氏度(°Re)":exg=1.25;break;
            //time
            case "天(d)":exg=8.64e+10;break;
            case "年(yr)":exg=3.1536e+13;break;
            case "周(week)":exg=6.048e+11;break;
            case "时(h)":exg=3.6e+9;break;
            case "分(min)":exg=6e+7;break;
            case "毫秒(ms)":exg=1000;break;
            case "秒(s)":exg=1000000;break;
        }
        return a*exg;
    }
    public static HashMap<String, List<String>> unit = new HashMap<>();
    public static void init(){
        List<String> list = new ArrayList<>();
        list.add("千米(km)");
        list.add("米(m)");
        list.add("分米(dm)");
        list.add("厘米(cm)");
        list.add("毫米(mm)");
        unit.put("长度", list);
        list = new ArrayList<>();
        list.add("平方千米(km²)");
        list.add("公顷(ha)");
        list.add("公亩(are)");
        list.add("平方米(㎡)");
        list.add("平方分米(dm²)");
        list.add("平方厘米(cm²)");
        list.add("平方毫米(mm²)");
        unit.put("面积", list);
        list = new ArrayList<>();
        list.add("立方米(m³)");
        list.add("立方分米(dm³)");
        list.add("立方厘米(cm³)");
        list.add("立方毫米(mm³)");
        list.add("公石(hl)");
        list.add("升(l)");
        list.add("分升(dl)");
        list.add("厘升(cl)");
        list.add("毫升(ml)");
        list.add("微升(mm³)");
        unit.put("体积", list);
        list = new ArrayList<>();
        list.add("米/秒(m/s)");
        list.add("千米/秒(km/s)");
        list.add("千米/时(km/h)");
        list.add("马赫(mach)");
        list.add("光速(c)");
        list.add("英里/时(mile/h)");
        list.add("英寸/秒(in/s)");
        unit.put("速度", list);
        list = new ArrayList<>();
        list.add("摄氏度(℃)");
        list.add("华氏度(℉)");
        list.add("开氏度(K)");
        list.add("兰氏度(°R)");
        list.add("列氏度(°Re)");
        unit.put("温度", list);
        list = new ArrayList<>();
        list.add("年(yr)");
        list.add("周(week)");
        list.add("天(d)");
        list.add("时(h)");
        list.add("分(min)");
        list.add("秒(s)");
        list.add("毫秒(ms)");
        list.add("微秒(μs)");
        unit.put("时间", list);
    }

    //
    private String unitSrc;
    private String unitObj;
    private String quantity = "";
    private String result = "";
    @Override
    protected void keyClear(String key){
        quantity = "";
        result = "";
    }
    @Override
    protected void keyDelete(String key){
        if(quantity.length() > 0) {
            quantity = quantity.substring(0, quantity.length() - 1);
        }
    }
    @Override
    protected void keyDecDigit(String key){
        quantity += key;
    }
    //
    public void setUnitSrc(String unitSrc){
        this.unitSrc = unitSrc;
    }
    public void setUnitObj(String unitObj){
        this.unitObj = unitObj;
    }

    @Override
    public UnitCalResult output(){
        if(quantity.length() != 0){
            result = transfer(unitSrc,unitObj,quantity);
        } else {
            result = "";
        }
        UnitCalResult op = new UnitCalResult(unitSrc, unitObj, quantity, result);
        if(!quantity.equals("")) {
            writeRecord(op);
        }
        return op;
    }
}
