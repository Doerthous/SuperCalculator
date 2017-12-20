package com.sc.calculate.general;

import com.sc.calculate.ModuleWithKeyboard;
import com.sc.datastructrue.records.StandardCalResult;
import com.sc.utity.Keyboard;
import com.sc.utity.Utils;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by Doerthous on 2017/6/29.
 */

public class Standard extends ModuleWithKeyboard {
    String expr = "";
    String result = "";

    @Override
    protected void keyClear(String key){
        expr = "";
        result = "";
    }
    @Override
    protected void keyDelete(String key){
        if(expr.length() > 0){ // 删除表达式的元素
            if (expr.length() == 1){
                result = "";
                expr = expr.substring(0,expr.length()-1);
            }else{
                expr = expr.substring(0,expr.length()-1);
            }
            if (!"".equals(expr)&& Keyboard.in(Keyboard.DEC_DIGIT,String.valueOf(expr.length()-1))){
                result = eval(expr);
            }
        }
    }
    @Override
    protected void keyPoint(String key){
        String el = exprLast();
        boolean exprLastIsDigit = Keyboard.in(Keyboard.DEC_DIGIT, el);
        if(exprLastIsDigit){
            if(!hasPoint()){
                expr += key;
            }
        }
    }
    @Override
    protected void keyDecDigit(String key){
        String el = exprLast();
        boolean exprLastIsZero = Keyboard.is(Keyboard.ZERO, el);
        boolean exprLastIsEqual = Keyboard.is(Keyboard.EQU, el);
        if(exprLastIsEqual){
            return;
        }
        if(exprLastIsZero && Keyboard.is(Keyboard.ZERO, key) && !hasNotZeroPrefix()){
            return;
        }
        if(singleZero()){
            expr = expr.substring(0,expr.length()-1)+ key;
        } else {
            expr += key;
        }
        result = eval(expr);
    }
    @Override
    protected void keyBaseOperator(String key){
        String el = exprLast();
        boolean exprLastIsDigit = Keyboard.in(Keyboard.DEC_DIGIT, el);
        if(exprLastIsDigit){
            // doSomething
            expr += key;
        }

    }
    @Override
    protected void keyEqual(String key){
        String el = exprLast();
        boolean exprLastIsDigit = Keyboard.in(Keyboard.DEC_DIGIT, el);
        if(exprLastIsDigit){
            if(!"".equals(result)){
                writeRecord(new StandardCalResult(expr, result));
                expr = result;
                result = "";
            }
        }
    }

    private boolean hasPoint(){
        for(int i = expr.length()-1; i >= 0; --i){
            if(Keyboard.in(Keyboard.OPERATOR, expr.charAt(i))){
                break;
            } else if(Keyboard.is(Keyboard.POINT, expr.charAt(i))){
                return true;
            }
        }
        return  false;
    }
    private boolean hasNotZeroPrefix(){
        for(int i = expr.length()-1; i >= 0; --i){
            if(Keyboard.in(Keyboard.DEC_DIGIT, expr.charAt(i))){
                if(!Keyboard.is(Keyboard.ZERO, expr.charAt(i))) {
                    return true;
                }
                continue;
            }
            if(Keyboard.is(Keyboard.POINT, expr.charAt(i))){
                return true;
            }
            break;
        }
        return  false;
    }
    private boolean singleZero(){
        if(expr.length() == 0){
            return false;
        }
        else if(expr.length() == 1){
            String e1 = exprLast();
            if(Keyboard.is(Keyboard.ZERO, e1)){
                return true;
            }
        } else {
            String e1 = exprLast();
            String e2 = String.valueOf(expr.charAt(expr.length()-2));
            if(Keyboard.in(Keyboard.BASE_OPERATOR, e2) && Keyboard.is(Keyboard.ZERO, e1)){
                return true;
            }
        }
        return false;
    }
    @Override
    public StandardCalResult output(){
        StandardCalResult op = new StandardCalResult(expr, result);
        if(expr.contains(Keyboard.EQU)) {
            writeRecord(op);
        }
        return op;
    }

    private String exprLast(){
        String exprLast = "";
        if(expr.length() > 0){
            exprLast = Utils.last(expr);
        }
        return exprLast;
    }
    private static ArrayList<String> split(String expr){
        ArrayList<String> tokens = new ArrayList<>();
        for(int i = 0; i < expr.length(); ++i){
            String ch = String.valueOf(expr.charAt(i));
            if(Keyboard.isOperator(ch)){
                tokens.add(expr.substring(0,i));
                tokens.add(String.valueOf(ch));
                expr = expr.substring(i+1,expr.length());
                i = -1;
            }
        }
        tokens.add(expr);
        return tokens;
    }
    public static String eval(String expr) {
        expr = expr.replace(",","");
        ArrayList<String> tokens = split(expr);
        ArrayList<String> stack = new ArrayList<>();
        while(tokens.size() > 0) {
            String token = tokens.remove(0);
            if(Keyboard.is(Keyboard.MUL, token) || Keyboard.is(Keyboard.DIV, token)){
                Double result1 = Double.valueOf(stack.remove(stack.size()-1));
                Double result2 = Double.valueOf(tokens.remove(0));
                if(Keyboard.is(Keyboard.DIV, token)){
                    stack.add(String.valueOf(result1/result2));
                } else {
                    stack.add(String.valueOf(result1*result2));
                }
            } else {
                if(stack.size() == 3){
                    Double result1 = Double.valueOf(stack.remove(0));
                    String op = stack.remove(0);
                    Double result2 = Double.valueOf(stack.remove(0));
                    if(Keyboard.is(Keyboard.ADD, op)){
                        stack.add(String.valueOf(result1+result2));
                    } else {
                        stack.add(String.valueOf(result1-result2));
                    }
                }
                stack.add(token);
            }
        }
        if(stack.size() == 3) {
            Double result1 = Double.valueOf(stack.remove(0));
            String op = stack.remove(0);
            Double result2 = Double.valueOf(stack.remove(0));
            if (Keyboard.is(Keyboard.ADD, op)) {
                stack.add(String.valueOf(result1 + result2));
            } else {
                stack.add(String.valueOf(result1 - result2));
            }
        }

        Double ope = Double.valueOf(stack.remove(0));
        NumberFormat nFormat=NumberFormat.getNumberInstance();
        nFormat.setMaximumFractionDigits(8);
        return nFormat.format(ope);
    }
}
