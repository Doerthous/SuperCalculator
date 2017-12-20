package com.sc.datastructrue.records;

/**
 * Created by Doerthous on 2017/7/5.
 */

public class UnitCalResult implements CalResult {
    private String src;
    private String obj;
    private String quantity;
    private String result;

    public UnitCalResult(String src, String obj, String quantity, String result) {
        this.src = src;
        this.obj = obj;
        this.quantity = quantity;
        this.result = result;
    }

    public String getSrc() {
        return src;
    }
    public String getObj() {
        return obj;
    }
    public String getQuantity() {
        return quantity;
    }
    public String getResult() {
        return result;
    }

    @Override
    public String toString(){
        return quantity + src + "=" + result + obj;
    }
}
