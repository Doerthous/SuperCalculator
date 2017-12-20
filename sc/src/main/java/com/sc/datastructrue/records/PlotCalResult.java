package com.sc.datastructrue.records;

/**
 * Created by Doerthous on 2017/7/6.
 */

public class PlotCalResult implements CalResult {
    private String epxr;
    private Double[] x;
    private Double[] y;
    private String exception;

    public PlotCalResult(String epxr, Double[] x, Double[] y, String exception) {
        this.epxr = epxr;
        this.x = x;
        this.y = y;
        this.exception = exception;
    }

    public String getEpxr() {
        return epxr;
    }

    public Double[] getX() {
        return x;
    }

    public Double[] getY() {
        return y;
    }

    public String getException() {
        return exception;
    }
}
