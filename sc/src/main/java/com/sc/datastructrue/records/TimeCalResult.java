package com.sc.datastructrue.records;

/**
 * Created by Doerthous on 2017/7/5.
 */

public class TimeCalResult implements CalResult {
    private String dateBegin;
    private String dateEnd;
    private String days;
    private String expr;
    private String result;

    public TimeCalResult(String expr, String result) {
        this("","","",expr,result);
    }
    public TimeCalResult(String dateBegin, String dateEnd, String days, String expr, String result) {
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.days = days;
        this.expr = expr;
        this.result = result;
    }

    public String getDateBegin() {
        return dateBegin;
    }
    public String getDateEnd() {
        return dateEnd;
    }
    public String getDays() {
        return days;
    }
    public String getExpr() {
        return expr;
    }
    public String getResult() {
        return result;
    }

    @Override
    public String toString(){
        return expr + result;
    }
}
