package com.sc.datastructrue.records;

/**
 * Created by Doerthous on 2017/7/5.
 */

public class StandardCalResult implements CalResult {
    private String expr;
    private String result;

    public StandardCalResult(String expr, String result) {
        this.expr = expr;
        this.result = result;
    }

    public String getExpr() {
        return expr;
    }
    public String getResult() {
        return result;
    }

    @Override
    public String toString(){
        return expr + "=" + result;
    }
}
