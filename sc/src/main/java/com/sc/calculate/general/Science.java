package com.sc.calculate.general;

import android.util.Log;

import com.sc.calculate.ModuleBase;
import com.sc.datastructrue.records.ScienceCalResult;
import com.sc.ui.general.ScienceFragment;
import com.sc.utity.Keyboard;
import com.sc.utity.Utils;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Doerthous on 2017/6/30.
 */

public class Science extends ModuleBase {
    List<String> exprs = new ArrayList<>();
    List<String> exprss = new ArrayList<>();
    List<String> nums = new ArrayList<>();
    List<String> numss = new ArrayList<>();
    int lBracketCnt = 0;

    public void input(String in){
        if(Keyboard.is(Keyboard.CLR, in)){
            exprs.clear();
            exprss.clear();
            nums.clear();
            numss.clear();
            lBracketCnt = 0;
        }
        else if(Keyboard.is(Keyboard.DEL, in)){
            String i = removeLast(nums);
            removeLast(numss);
            forwithCompute();
            if(hasLeftBracket(i)){
                lBracketCnt--;
            }
            else if(Keyboard.is(Keyboard.RBRACKET, i)){
                ++lBracketCnt;
            }
        }
        else if(Keyboard.is(Keyboard.CE, in)){
            nums.clear();
            exprss.clear();
            lBracketCnt = 0;
        } else if(Keyboard.is(Keyboard.RAD, in)){
            ScienceFragment.state = true;
            forwithCompute();
        } else if(Keyboard.is(Keyboard.DEG, in)){
            ScienceFragment.state = false;
            forwithCompute();
        } else if(Keyboard.is(Keyboard.EQU, in)){
            if (exprss.size()>0){
                String record = Utils.listToString(numss);
                numss.clear();
                numss.add(exprss.get(0));
                writeRecord(new ScienceCalResult(record,String.valueOf(exprss.get(0))));
                exprss.clear();
            }else{
                if (numss.size()>0){
                    forwithCompute();
                    writeRecord(new ScienceCalResult(String.valueOf(numss.get(0)),String.valueOf(exprss.get(0))));
                }
                //writeRecord(Utils.listToString(numss));
            }
        } else {
            nums.add(in);
            if (Keyboard.is(Keyboard.SQRT, in)) {
                addPow("^(1÷2)");
            } else if (Keyboard.is(Keyboard.SQUARE, in)) {
                addPow("^2");
            } else if (Keyboard.is(Keyboard.CBRT, in)) {
                addPow("^(1÷3)");
            } else if (Keyboard.is(Keyboard.CUBE, in)) {
                addPow("^3");
            } else if (Keyboard.is(Keyboard.NTHRT, in)) {
                addPow("^(1÷");
                lBracketCnt++;
            } else if (Keyboard.is(Keyboard.NTHPW, in)) {
                numss.add("^");
            } else if (Keyboard.is(Keyboard.NB, in)) {
                addConstant("e");

            } else if(Keyboard.is(Keyboard.PI,in)){
                String temp = formatTransfer();
                addConstant(in);
                //即时计算

            } else if(Keyboard.in(Keyboard.TRIGONOMETRIC_OPERATOR, in) ||
                    Keyboard.in(Keyboard.LOGARITHMIC_OPERATOR, in)){
                lBracketCnt++;
                nums.add(in);
                String str = getLast(nums)+"(";
                addFunction(str);
            } else if (Keyboard.is(Keyboard.TEN_NTHPW, in)) {
                numss.add("10^");
            } else if (Keyboard.is(Keyboard.NB_NTHPW, in)) {
                numss.add("e^");
            } else if (Keyboard.is(Keyboard.DIV_BY_X, in)) {
                numss.add("1÷");
            } else if (Keyboard.is(Keyboard.FTR, in)) {
                if(!isLastPoint()){
                    numss.add("!");
                }
            }else if(Keyboard.is(Keyboard.SIGN,in)){
                String temp = formatTransfer();
                if ("".equals(temp)){
                    numss.add("(-");
                }else{
                    if(!isLastPoint()){
                        numss.add("(-");
                    }
                }
            } else if(Keyboard.isOperator(in)){

                String temp = formatTransfer();
                //输入限制
                if("".equals(temp)){

                }else{
                    String ch = String.valueOf(temp.charAt(temp.length()-1));
                    if("|".equals(ch)){
                        removeLast(numss);
                        numss.add(in);
                    }else if("(".equals(ch)||".".equals(ch)){

                    }else{
                        numss.add(in);
                    }
                }
            }else if(Keyboard.in(Keyboard.DEC_DIGIT,in)){
                String temp = formatTransfer();
                if ("".equals(temp)){
                    numss.add(in);
                    forwithCompute();
                }else{
                    String ch = String.valueOf(temp.charAt(temp.length()-1));
                    if ("e".equals(ch)||"π".equals(ch)){
                        numss.add("×");
                        numss.add(in);
                        forwithCompute();
                    }else {
                        numss.add(in);
                        forwithCompute();
                    }
                }

            }else if (Keyboard.is(Keyboard.LBRACKET,in)){
                addFunction(in);
            }else if (Keyboard.is(Keyboard.RBRACKET,in)){
                String temp = formatTransfer();
                if(temp.contains("(")){
                    numss.add(in);
                }
            } else if (Keyboard.is(Keyboard.POINT,in)){
                String temp = formatTransfer();
                if ("".equals(temp)){

                }else{
                    String ch = String.valueOf(temp.charAt(temp.length()-1));
                    if (Keyboard.in(Keyboard.DEC_DIGIT, ch)){
                        String pin = "";
                        int i = temp.length()-1;
                        while (i > 0){
                            if (Keyboard.in(Keyboard.DEC_DIGIT,String.valueOf(temp.charAt(i)))){
                                i--;
                            }else{
                                break;
                            }
                        }
                        if (".".equals(String.valueOf(temp.charAt(i)))){

                        }else {
                            numss.add(in);
                        }
                    }
                }

            }else {
                numss.add(in);
            }
        }
    }
    public ScienceCalResult output(){
        String n = "";
        String e = "";
        for(String s : numss){
            n += s;
        }
        for(String s: exprss){
            e += s;
        }
        return new ScienceCalResult(n, e);
    }

    public static String getLast(List<String> l){
        if(l.size() > 0){
            return l.get(l.size()-1);
        }
        return "";
    }
    public static void setLast(List<String> l, String v){
        if(l.size() > 0){
            l.set(l.size()-1, v);
        } else {
            l.add(v);
        }
    }
    public String removeLast(List<String> l){
        if(l.size() > 0){
            if (l.size() == 1){
                exprss.clear();
            }
            return l.remove(l.size()-1);
        }
        return "";
    }

    /*    private void addMulIfNeed(){
            if(isNumberOrRightBracket(getLast(nums))) {
                nums.add(Keyboard.MUL);
                numss.add(getLast(nums));
            }
        }
        private void inputDigit(String in){
            nums.add(in);
            if(Keyboard.in(Keyboard.HEX_DIGIT, getLast(nums)) ||
                    Keyboard.is(Keyboard.POINT, getLast(nums))){
                String i = getLast(numss);
                if(Keyboard.is(Keyboard.ZERO, in) && Keyboard.is(Keyboard.ZERO, i)
                        && !hasPoint(i)){
                    return;
                }
                setLast(numss, getLast(numss)+getLast(nums));
            } else {
                numss.add(in);
            }

        }*/
    private boolean hasLeftBracket(String in){
        if(Keyboard.is(Keyboard.LBRACKET, in) ||
                Keyboard.in(Keyboard.TRIGONOMETRIC_OPERATOR, in) ||
                Keyboard.in(Keyboard.LOGARITHMIC_OPERATOR, in)){
            return true;
        }
        return false;
    }
/*    private boolean isNumberOrRightBracket(String in){
        if(Keyboard.in(Keyboard.HEX_DIGIT, in) ||
                Keyboard.is(Keyboard.RBRACKET, in) ||
                Keyboard.is(Keyboard.SQRT, in) ||
                Keyboard.in(Keyboard.CONSTANT, in)){
            return true;
        }
        return false;
    }
    private boolean endWithBaseOperator(String in){
        if(Keyboard.in(Keyboard.BASE_OPERATOR, in) ||
                Keyboard.is(Keyboard.NTHRT, in)){
            return true;
        }
        return false;
    }
    private boolean hasPoint(String in){
        return in.indexOf(Keyboard.POINT) >= 0;
    }
    private boolean isPoint(String in) {
        return Keyboard.is(Keyboard.POINT, in);
    }*/

    private String formatTransfer(){
        String[] ops = {
                "+","—","×","÷","%","asin","acos","atan","sin","cos","tan",
                "log","ln","!","^"
        };
        String n = "";
        for(String s : numss){
            n += s;
        }
        n = n.toLowerCase();
        for(String s : ops){
            n = n.replace(s,"|"+s+"|");
        }
        String[] err = {
                "a|sin|","a|cos|","a|tan|"
        };
        for (String er : err){
            String ch = er.replace("|","");
            n = n.replace(er,ch);
        }
        n = n.replace("-","0|—|");
        Log.e("expression",n);
        /*n = eql(n);
        Log.e("ressult",n);*/
        return n;
    }




    //
    //常数
    public  static  ArrayList<String> constant(ArrayList<String> arr){
        ArrayList<String> stk = new ArrayList<>();
        while(arr.size() > 0){
            String ch = arr.remove(0);
            if("e".equals(ch)||"π".equals(ch)){
                if("e".equals(ch)){
                    stk.add(String.valueOf(Math.E));
                }else{
                    stk.add(String.valueOf(Math.PI));
                }
            }else{
                stk.add(ch);
            }
        }
        return stk;
    }

    //阶乘
    public static  ArrayList<String> factorial(ArrayList<String> arr){
        ArrayList<String> stk = new ArrayList<>();
        while(arr.size() > 0){
            String token = arr.remove(0);
            if("!".equals(token)){
                Integer num = Integer.valueOf(stk.remove(stk.size()-1));
                for(int i = num-1;i > 0;--i){
                    num = num * i;
                }
                stk.add(String.valueOf(num));
            }else{
                stk.add(token);
            }
        }
        Log.e("fa_stk", String.valueOf(stk));
        return stk;
    }

    //切换弧度角度
    public  static double changeRD(String str){
        Double num = Double.valueOf(str);
        if(ScienceFragment.state){
            return  num;
        }else{
            return Math.PI/180*num;
        }
    }
    public  static double changeRD(Double dou){
        if(ScienceFragment.state){
            return  dou;
        }else{
            return 180/Math.PI * dou;
        }
    }
    //函数运算
    public static  ArrayList<String> function(ArrayList<String> arr){
        ArrayList<String> stk = new ArrayList<>();
        while(arr.size() > 0){
            String token = arr.remove(0);
            Double num = 0.0;
            Double num2 = 0.0;
            switch (token){
                case "cos":
                    num = changeRD(arr.remove(0));
                    stk.add(String.valueOf(Math.cos(num)));
                    break;
                case "sin":
                    num = changeRD(arr.remove(0));
                    Log.e("num",String.valueOf(num));
                    stk.add(String.valueOf(Math.sin(num)));
                    break;
                case "tan":
                    num = changeRD(arr.remove(0));
                    stk.add(String.valueOf(Math.tan(num)));
                    break;
                case "acos":
                    num = Double.valueOf(arr.remove(0));
                    stk.add(String.valueOf(changeRD(Math.acos(num))));
                    break;
                case "asin":
                    num = Double.valueOf(arr.remove(0));
                    stk.add(String.valueOf(changeRD(Math.asin(num))));
                    break;
                case "atan":
                    num = Double.valueOf(arr.remove(0));
                    stk.add(String.valueOf(changeRD(Math.atan(num))));
                    break;
                case"lg":
                    num = Double.valueOf(stk.remove(stk.size()-1));
                    num2 = Double.valueOf(arr.remove(0));
                    stk.add(String.valueOf(Math.log(num2)/Math.log(num)));
                    break;
                case"ln":
                    num = Double.valueOf(arr.remove(0));
                    stk.add(String.valueOf(Math.log(num)));
                    break;
                case"log":
                    num = Double.valueOf(arr.remove(0));
                    stk.add(String.valueOf(Math.log10(num)));
                    break;
                default:stk.add(token);
            }
        }
        return stk;
    }

    //幂运算
    public static ArrayList<String> power(ArrayList<String> arr) {
        ArrayList<String> stk = new ArrayList<String>();
        ArrayList<String> strack = new ArrayList<>();
        Log.e("arr", String.valueOf(arr));
        while (arr.size() > 0) {
            String token = arr.remove(0);
            Log.e("token", token);
            if ("^".equals(token)) {
                Double num1 = Double.valueOf(stk.remove(stk.size() - 1));
                Double num2 = Double.valueOf(arr.remove(0));
                stk.add(String.valueOf(Math.pow(num1, num2)));
            }else{
                stk.add(token);
            }
            Log.e("stk", String.valueOf(stk));
        }
        while (stk.size() > 0) {
            String token = stk.remove(0);
            Log.e("token_1", token);
            if ("^0.5".equals(token)) {
                Double num1 = Double.valueOf(stk.remove(0));
                strack.add(String.valueOf(Math.pow(num1, 0.5)));
            }else{
                strack.add(token);
            }
            Log.e("strack",String.valueOf(strack));
        }
        return strack;
    }//去幂 开根

    public static Double normal(ArrayList<String> arr) {
        ArrayList<String> stk = new ArrayList<String>();
        //除乘除法
        while (arr.size() > 0) {
            String token = arr.remove(0);
            if ("×".equals(token) || "÷".equals(token)||"%".equals(token)) {
                Double num1 = Double.valueOf(stk.remove(stk.size() - 1));
                Double num2 = Double.valueOf(arr.remove(0));
                if ("×".equals(token)) {
                    stk.add(String.valueOf(num1 * num2));
                }
                if("÷".equals(token)){
                    stk.add(String.valueOf(num1 / num2));
                }
                if("%".equals(token)){
                    stk.add(String.valueOf(num1 % num2));
                }
            } else {
                if (stk.size() == 3) {
                    Double num1 = Double.valueOf(stk.remove(0));
                    String op = String.valueOf(stk.remove(0));
                    Double num2 = Double.valueOf(stk.remove(0));
                    if ("+".equals(op)) {
                        stk.add(String.valueOf(num1 + num2));
                    } else {
                        stk.add(String.valueOf(num1 - num2));
                    }
                }
                stk.add(token);
            }
        }
        if (stk.size() == 3) {
            Double num1 = Double.valueOf(stk.remove(0));
            String op = String.valueOf(stk.remove(0));
            Double num2 = Double.valueOf(stk.remove(0));
            if ("+".equals(op)) {
                stk.add(String.valueOf(num1 + num2));
            } else {
                stk.add(String.valueOf(num1 - num2));
            }
        }
        return Double.valueOf(stk.remove(0));
    }//去乘除法 取模

    public  static ArrayList<String> spl_s(String ope){
        List<String> t = Arrays.asList(ope.split("\\|"));
        ArrayList<String> tokens = new ArrayList<>(t);
        for(int i = 0; i < tokens.size();++i){
            if("".equals(tokens.get(i))){
                tokens.remove(i);
            }
        }
        return tokens;
    }//无括号分离操作数与符号

    public static String spl_bracket(String ope){
        if(ope.contains("(")) {
            int start = 0;
            int end = 0;
            for(int i = ope.length()-1; i >-1; --i){
                if("(".equals(String.valueOf(ope.charAt(i)))){
                    start = i + 1;
                    break;
                }
            }
            for(int i = start;i < ope.length();++i){
                if(")".equals(String.valueOf(ope.charAt(i)))){
                    end = i;
                    break;
                }
            }
            String left = ope.substring(0,start-1);
            String mid =  ope.substring(start,end);
            String right = ope.substring(end+1,ope.length());
            String str = "";
            //6(6+2) (6+2)(6+2)   (6+2)6
            if(left.length()> 0 ){
                String left_last = String.valueOf(left.charAt(left.length()-1));
                if(!"(".equals(left_last)&& !"|".equals(left_last)){
                    str = left+ "|×|"+String.valueOf(normal(power(factorial(function(constant(spl_s(mid))))))) ;
                }else{
                    str = left+ String.valueOf(normal(power(factorial(function(constant(spl_s(mid)))))));
                }
            }else {
                str = left+ String.valueOf(normal(power(factorial(function(constant(spl_s(mid)))))));
            }
            if(right.length()> 0 ){
                String right_last = String.valueOf(right.charAt(0));
                if(!")".equals(right_last)&& !"|".equals(right_last)){
                    str = str + "|×|"+right;
                }else{
                    str =str + right;
                }
            }else {
                str =str + right;
            }
            Log.e("fff",str);
            if(str.contains("(")){
                return spl_bracket(str);
            }else{
                return str;
            }
        }else{
            return ope;
        }

    }//去括号

    public static String eql(String ope) {
        //设置小数位数
        ope = ope.replace(",","");
        NumberFormat nFormat=NumberFormat.getNumberInstance();
        nFormat.setMaximumFractionDigits(8);
        return nFormat.format(normal(power(factorial(function(constant(spl_s(spl_bracket(ope))))))));
    }


    //输入限制
    public  void addConstant(String str){
        String temp = formatTransfer();
        if("".equals(temp)){
            numss.add(str);
            //即时计算
            forwithCompute();
        }else{
            String ch = String.valueOf(temp.charAt(temp.length()-1));
            if("|".equals(ch)||"(".equals(ch)){
                numss.add(str);
                //即时计算
                forwithCompute();
            }else if(".".equals(ch)){

            }else{
                str = "×" + str;
                numss.add(str);
                //即时计算
                forwithCompute();
            }
        }
    }//PI E
    public  void addPow(String str){
        String temp =formatTransfer();
        if("".equals(temp)){

        }else{
            String ch = String.valueOf(temp.charAt(temp.length()-1));
            if("|".equals(ch)||"(".equals(ch)||".".equals(ch)){

            }else{
                numss.add(str);
                //即时计算
                forwithCompute();
            }
        }
    }

    //加函数
    public  void addFunction(String str){
        String temp = formatTransfer();
        if ("".equals(temp)){
            numss.add(str);
        }else{
            String ch = String.valueOf(temp.charAt(temp.length()-1));
            if(".".equals(ch)){

            }else if(")".equals(ch)||"!".equals(ch)||Keyboard.in(Keyboard.DEC_DIGIT,ch)){
                str = "×" + str;
                numss.add(str);
            }else{
                numss.add(str);
            }
        }

    }


    //加括号

    //最后个字符是否是小数点
    public Boolean  isLastPoint(){
        String temp = formatTransfer();
        if (temp.length() > 0){
            String ch = String.valueOf(temp.charAt(temp.length()-1));
            if(".".equals(ch)){
                return true;
            }else {
                return false;
            }
        }else{
            return  false;
        }
    }

    //即时计算
    public void forwithCompute(){
        String temp = formatTransfer();
        //即时计算 先加后算
        //判断有无括号（有则补全）
        //判断能否计算
        if("".equals(temp)){

        }else{
            String ch = String.valueOf(temp.charAt(temp.length()-1));
            if(Keyboard.in(Keyboard.DEC_DIGIT,ch)||"!".equals(ch)||")".equals(ch)||"e".equals(ch)||"π".equals(ch)){
                if(temp.contains("(")){
                    int left = 0;
                    int right = 0;
                    for(int i = 0;i < temp.length();i++){
                        String bracket = String.valueOf(temp.charAt(i));
                        if("(".equals( bracket)){
                            left++;
                        }
                        if(")".equals( bracket)){
                            right++;
                        }
                    }
                    int difference = left - right;
                    if(difference > 0){
                        while(difference >0){
                            temp = temp + ")";
                            difference--;
                        }
                    }
                    exprss.clear();
                    exprss.add(eql(temp));
                    Log.e("exprss",String.valueOf(exprss));

                }else{
                    exprss.clear();
                    exprss.add(eql(temp));

                }

            }else{
                //nothing to do
            }
        }
    }
}



