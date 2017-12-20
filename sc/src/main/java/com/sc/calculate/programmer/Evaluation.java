package com.sc.calculate.programmer;

import com.sc.utity.Dict2D;
import com.sc.utity.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Doerthous on 2017/7/3.
 */

public class Evaluation {
    public static Dict2D<String, String, String> dict;
    private static void init(){
        if(dict == null){
            dict = new Dict2D();
        }
        dict.put("(","(","<");
        dict.put("(",")","<");
        dict.put("(","*","<");
        dict.put("(","/","<");
        dict.put("(","%","<");
        dict.put("(","+","<");
        dict.put("(","-","<");
        dict.put("(","<<","<");
        dict.put("(",">>","<");
        dict.put("(","&","<");
        dict.put("(","^","<");
        dict.put("(","|","<");
        dict.put(")","(",">");
        dict.put(")",")",">");
        dict.put(")","*",">");
        dict.put(")","/",">");
        dict.put(")","%",">");
        dict.put(")","+",">");
        dict.put(")","-",">");
        dict.put(")","<<",">");
        dict.put(")",">>",">");
        dict.put(")","&",">");
        dict.put(")","^",">");
        dict.put(")","|",">");
        dict.put("*","(","<");
        dict.put("*",")",">");
        dict.put("*","*",">");
        dict.put("*","/",">");
        dict.put("*","%",">");
        dict.put("*","+",">");
        dict.put("*","-",">");
        dict.put("*","<<",">");
        dict.put("*",">>",">");
        dict.put("*","&",">");
        dict.put("*","^",">");
        dict.put("*","|",">");
        dict.put("/","(","<");
        dict.put("/",")",">");
        dict.put("/","*",">");
        dict.put("/","/",">");
        dict.put("/","%",">");
        dict.put("/","+",">");
        dict.put("/","-",">");
        dict.put("/","<<",">");
        dict.put("/",">>",">");
        dict.put("/","&",">");
        dict.put("/","^",">");
        dict.put("/","|",">");
        dict.put("%","(","<");
        dict.put("%",")",">");
        dict.put("%","*",">");
        dict.put("%","/",">");
        dict.put("%","%",">");
        dict.put("%","+",">");
        dict.put("%","-",">");
        dict.put("%","<<",">");
        dict.put("%",">>",">");
        dict.put("%","&",">");
        dict.put("%","^",">");
        dict.put("%","|",">");
        dict.put("+","(","<");
        dict.put("+",")",">");
        dict.put("+","*","<");
        dict.put("+","/","<");
        dict.put("+","%","<");
        dict.put("+","+",">");
        dict.put("+","-",">");
        dict.put("+","<<",">");
        dict.put("+",">>",">");
        dict.put("+","&",">");
        dict.put("+","^",">");
        dict.put("+","|",">");
        dict.put("-","(","<");
        dict.put("-",")",">");
        dict.put("-","*","<");
        dict.put("-","/","<");
        dict.put("-","%","<");
        dict.put("-","+",">");
        dict.put("-","-",">");
        dict.put("-","<<",">");
        dict.put("-",">>",">");
        dict.put("-","&",">");
        dict.put("-","^",">");
        dict.put("-","|",">");
        dict.put("<<","(","<");
        dict.put("<<",")",">");
        dict.put("<<","*","<");
        dict.put("<<","/","<");
        dict.put("<<","%","<");
        dict.put("<<","+","<");
        dict.put("<<","-","<");
        dict.put("<<","<<",">");
        dict.put("<<",">>",">");
        dict.put("<<","&",">");
        dict.put("<<","^",">");
        dict.put("<<","|",">");
        dict.put(">>","(","<");
        dict.put(">>",")",">");
        dict.put(">>","*","<");
        dict.put(">>","/","<");
        dict.put(">>","%","<");
        dict.put(">>","+","<");
        dict.put(">>","-","<");
        dict.put(">>","<<",">");
        dict.put(">>",">>",">");
        dict.put(">>","&",">");
        dict.put(">>","^",">");
        dict.put(">>","|",">");
        dict.put("&","(","<");
        dict.put("&",")",">");
        dict.put("&","*","<");
        dict.put("&","/","<");
        dict.put("&","%","<");
        dict.put("&","+","<");
        dict.put("&","-","<");
        dict.put("&","<<","<");
        dict.put("&",">>","<");
        dict.put("&","&",">");
        dict.put("&","^",">");
        dict.put("&","|",">");
        dict.put("^","(","<");
        dict.put("^",")",">");
        dict.put("^","*","<");
        dict.put("^","/","<");
        dict.put("^","%","<");
        dict.put("^","+","<");
        dict.put("^","-","<");
        dict.put("^","<<","<");
        dict.put("^",">>","<");
        dict.put("^","&","<");
        dict.put("^","^",">");
        dict.put("^","|","<");
        dict.put("|","(","<");
        dict.put("|",")",">");
        dict.put("|","*","<");
        dict.put("|","/","<");
        dict.put("|","%","<");
        dict.put("|","+","<");
        dict.put("|","-","<");
        dict.put("|","<<","<");
        dict.put("|",">>","<");
        dict.put("|","&","<");
        dict.put("|","^",">");
        dict.put("|","|",">");
    }
    private static String[] split(String expr, String[] ops){
        for(int i = 0; i < ops.length; ++i){
            String[] t = expr.split(ops[i]);
            expr = Utils.join(","+ops[i]+",", t);
        }
        return expr.split(",");
    }
    private static String compute(String opn1, String op, String opn2){
        Long n1 = Long.parseLong(opn1, 10);
        Long n2 = Long.parseLong(opn2, 10);
        String retVal = "";
        switch (op){
            case "+":
                retVal = String.valueOf(n1+n2);
                break;
            case "-":
                retVal = String.valueOf(n1-n2);
                break;
            case "*":
                retVal = String.valueOf(n1*n2);
                break;
            case "/":
                retVal = String.valueOf(n1/n2);
                break;
            case "&":
                retVal = String.valueOf(n1&n2);
                break;
            case "|":
                retVal = String.valueOf(n1|n2);
                break;
            case "^":
                retVal = String.valueOf(n1^n2);
                break;
            case ">>":
                retVal = String.valueOf(n1 >> n2);
                break;
            case "<<":
                retVal = String.valueOf(n1 << n2);
                break;
        }
        return retVal;
    }
    private static String base2Compute(String[] expr){
        int i = 0;
        List<String> stack = new ArrayList<>();
        stack.add(expr[i++]);
        while(i < expr.length-2){
            if(dict.get(expr[i],expr[i+2]).equals("<")){
                stack.add(expr[i]);
                stack.add(expr[i+1]);
                i+=2;
            } else {
                String opn = expr[i+1];
                String opp = expr[i];
                String opc = expr[i+2];
                while(dict.get(opp,opc).equals(">")){
                    opn = compute(stack.remove(stack.size()-1), opp,opn);
                    if(!stack.isEmpty()){
                        opp = stack.remove(stack.size()-1);
                    } else {
                        break;
                    }
                }
                i+=2;
                if(!stack.isEmpty()) {
                    stack.add(opp);
                }
                stack.add(opn);
            }
        }
        return stack.get(0);
    }
    private static String base3Compute(String expr){
        expr += "^0";
        String[] e = Evaluation.split(expr, new String[]{"\\+","-","\\*","/","&","\\^","\\|",">>","<<"});
        for(int i = 0; i < e.length; ++i){
            e[i] = e[i].replace("\\","");
        }
        List<String> l = new ArrayList<>();
        for(int i = 0; i < e.length; ++i){
            if(e[i].length() == 0){
                l.add(e[i+1] + e[i+2]);
                i += 2;
            } else {
                l.add(e[i]);
            }
        }
        return base2Compute(l.toArray(new String[l.size()]));
    }
    public static String eval(String expr){
        init();
        List<Integer> lb = new ArrayList<>();
        for(int i = 0; i < expr.length(); ++i){
            if(expr.charAt(i) == '('){
                lb.add(i);
            } else if(expr.charAt(i) == ')'){
                int j = lb.remove(lb.size()-1);
                String f = expr.substring(0, j);
                String b = expr.substring(i+1,expr.length());
                expr = f + base3Compute(expr.substring(j+1, i)) + b;
                i = j;
            }
        }
        expr = base3Compute(expr);
        return expr;
    }
}
