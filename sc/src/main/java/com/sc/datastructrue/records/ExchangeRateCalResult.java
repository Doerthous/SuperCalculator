package com.sc.datastructrue.records;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Doerthous on 2017/7/5.
 */

public class ExchangeRateCalResult implements CalResult {
    private String srcCurrency;
    private String objCurrency;
    private String srcMoney;
    private String objMoney;
    private List<HashMap<String, String>> otherRate;

    public ExchangeRateCalResult(String srcCurrency, String objCurrency, String srcMoney, String objMoney) {
        this.srcCurrency = srcCurrency;
        this.objCurrency = objCurrency;
        this.srcMoney = srcMoney;
        this.objMoney = objMoney;
    }
    public ExchangeRateCalResult(String srcCurrency, String objCurrency, Double srcMoney, Double objMoney) {
        this(srcCurrency, objCurrency, String.valueOf(srcMoney), String.valueOf(objMoney));
    }
    public ExchangeRateCalResult(List<HashMap<String, String>> otherRate){
        this.otherRate = otherRate;
    }

    public String getSrcCurrency() {
        return srcCurrency;
    }

    public String getObjCurrency() {
        return objCurrency;
    }

    public String getSrcMoney() {
        return srcMoney;
    }

    public String getObjMoney() {
        return objMoney;
    }

    public List<HashMap<String, String>> getOtherRate() {
        return otherRate;
    }

    public String getExpr(){
        return srcMoney + srcCurrency + "=";
    }
    public String getResult(){
        return objMoney + objCurrency;
    }

    @Override
    public String toString(){
        return srcMoney + srcCurrency + "=" + objMoney + objCurrency;
    }
}
