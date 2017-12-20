package com.sc.calculate.plot;

import com.sc.calculate.ModuleWithKeyboard;
import com.sc.datastructrue.records.PlotCalResult;
import com.sc.utity.Keyboard;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Doerthous on 2017/7/6.
 */

public class Plot extends ModuleWithKeyboard {
    private String expr = "";
    @Override
    public void keyClear(String key){
        expr = "";
    }
    @Override
    public void keyDelete(String key){
        if(expr.length() > 0){
            expr = expr.substring(0, expr.length()-1);
        }
    }
    @Override
    public void keyDecDigit(String key){
        expr += key;
    }
    @Override
    public void keyEqual(String key){
        plot();
    }
    @Override
    public void keyBaseOperator(String key){
        expr += key;
    }
    @Override
    public void keyPoint(String key){
        expr += key;
    }
    @Override
    public void keyTrigonometric(String key){
        expr += key;
    }
    @Override
    public void keyLogarithmic(String key){
        expr += key;
    }
    @Override
    public void keySign(String key){
        //expr += key;
    }
    @Override
    public void keyLBracket(String key){
        expr += key;
    }
    @Override
    public void keyRBracket(String key){
        expr += key;
    }
    @Override
    protected void keyConstant(String key){
        if(Keyboard.is(Keyboard.NB, key)){
            expr += "e";
        } else {
            expr += key;
        }
    }
    @Override
    public void keyOther(String key){
        expr += key;
    }

    @Override
    public PlotCalResult output(){
        Double[] dx = x.toArray(new Double[]{});
        Double[] dy = y.toArray(new Double[]{});
        return new PlotCalResult(expr, dx, dy, exception);
    }

    private double XMin;
    private double XMax;
    private double YMin;
    private double YMax;


    public void setXMin(double XMin) {
        this.XMin = XMin;
    }
    public void setXMax(double XMax) {
        this.XMax = XMax;
    }
    public void setYMin(double YMin) {
        this.YMin = YMin;
    }
    public void setYMax(double YMax) {
        this.YMax = YMax;
    }
    public List<Double> x = new ArrayList<>();
    public List<Double> y = new ArrayList<>();

    private String exception;
    private void plot(){
        exception = "";
        String jsExpr = toJsExpr(expr);
        Context cx = Context.enter();
        cx.setOptimizationLevel(-1);
        try {
            Scriptable scope = cx.initStandardObjects();
            x.clear();
            y.clear();
            double span = (XMax - XMin)/100;
            //double span = 0.3f;
            while(XMin <= XMax+span) {
                x.add(XMin);
                String valExpr = jsExpr.replace("x", "("+String.valueOf(XMin)+")");
                Double r = Context.toNumber(cx.evaluateString(scope, valExpr, null, 1, null));
                y.add(r);
                XMin += span;
            }
        } catch (Exception e){
            exception = "请输入正确的表达式";
            expr = "";
            e.printStackTrace();
        }
        finally {
            Context.exit();
        }
    }

    private String toJsExpr(String expr){
        String jsExpr = expr.replace("sin","Math.sin")
                .replace("cos","Math.cos").replace("tan","Math.tan")
                .replace("log","Math.log").replace("pow","Math.pow")
                .replace(Keyboard.PI, "Math.PI").replace("e", "Math.E")
                .replaceAll("(\\(.*?\\))\\^(\\(.*?\\))", "Math.pow($1, $2)")
                .replace(Keyboard.MUL, "*").replace(Keyboard.DIV, "/")
                .replace(Keyboard.SUB, "-");
        return jsExpr;
    }
}

