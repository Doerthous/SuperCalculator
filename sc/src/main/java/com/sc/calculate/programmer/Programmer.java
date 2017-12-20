package com.sc.calculate.programmer;

import com.sc.calculate.general.Standard;
import com.sc.utity.Keyboard;
import com.sc.utity.KeyboardHandler;
import com.sc.utity.Utils;

import java.math.BigInteger;

/**
 * Created by Doerthous on 2017/6/29.
 */

public class Programmer extends KeyboardHandler implements ProgrammConstant {
    Integer format = DEC;
    Integer type = QWORD;
    String expr = "";
    BitArray bi = new BitArray("0", format, type);
    private int lBracketCnt = 0;
    @Override
    protected void keyClear(String key){
        expr = "";
        bi = new BitArray("0", format, type);
        lBracketCnt = 0;
    }
    @Override
    protected void keyDelete(String key){
        String num = bi.toString(format);
        if(num.length() == 1){
            // 如果数字字符串已经是零还删除则删除表达式的元素
            if(expr.length() > 0 && num.equals("0")) {
                String el = exprLast();
                if(el.length() != 0) {
                    // 判断删除项是否是括号
                    if (Keyboard.is(Keyboard.RBRACKET, el)) {
                        lBracketCnt++;
                    } else if (Keyboard.is(Keyboard.LBRACKET, el)) {
                        lBracketCnt--;
                    }
                    // 删除表达式后一个元素
                    expr = expr.substring(0, expr.length() - 1);
                }
            }
            // 如果数字只剩一个字符还删除则置为0
            bi = new BitArray("0", format, type);
        }
        // 删除数字最后一个字符
        else {
            num = num.substring(0, num.length() - 1);
            bi = new BitArray(num, format, type);
        }
    }
    @Override
    protected void keyClearError(String key){
        bi = new BitArray("0", format, type);
    }
    @Override
    protected void keyBaseOperator(String key){
        String el = exprLast();
        boolean exprLastIsNumber = Keyboard.in(Keyboard.HEX_DIGIT, el);
        boolean exprLastIsOperator = Keyboard.in(Keyboard.OPERATOR, el);
        boolean exprLastIsEqual = Keyboard.is(Keyboard.EQU, el);
        boolean exprLastIsLBracket = Keyboard.is(Keyboard.LBRACKET, el);
        boolean exprLastIsRBracket = Keyboard.is(Keyboard.RBRACKET, el);
        // 当表达式为空、表达式最后一个字符为操作符或左括号时
        // 将数字和当前输入的操作符号添加到表达式上
        if(exprLastIsOperator || el.equals("") || exprLastIsLBracket){
            String num = bi.toString(format).toUpperCase();
            expr += num + key;
            bi = new BitArray("0", format, type);
        }
        // 当表达式最后一个字符是右括号或数字时，将当前输入的
        // 操作符添加到表达式上
        else if(exprLastIsRBracket || exprLastIsNumber){
            expr += key;
        }
        // 当表达式最后一个字符是等于号时，将等于号修改为当前
        // 输入的操作符，并将等于的结果加到表达式上
        else if(exprLastIsEqual){
            String num = bi.toString(format).toString();
            expr = expr.substring(0, expr.length()-1)+key+num;
            bi = new BitArray("0", format, type);
        }
        expr = expr.toUpperCase();
    }
    @Override
    protected void keyEqual(String key){
        String el = exprLast();
        boolean exprLastIsEqual = Keyboard.is(Keyboard.EQU, el);
        boolean exprLastIsOperator = Keyboard.in(Keyboard.OPERATOR, el);
        // 如果表达式最后一个字符不是等号，则计算表达式的值
        // 将等号添加到表达式末尾，并设置bi为表达式的结果
        if(!exprLastIsEqual && !el.equals("")){
            // 如果表达式最后一个元素是操作符
            // 则把当前的数字添加到表达式上
            if(exprLastIsOperator){
                expr += bi.toString(format).toUpperCase();
            }
            if(Utils.isHexNumber(expr)){
                return;
            }
            String val = Evaluation.eval(exprTransfer(opTransfer(expr), format, DEC));
            bi = new BitArray(Long.toString(Long.parseLong(val),2), type);
            expr += key;
        }
    }
    @Override
    protected void keyHexDigit(String key){
        boolean exprLastIsEqual = Keyboard.is(Keyboard.EQU, exprLast());
        // 如果表达式最后一个元素是等于号则清除输入
        if(exprLastIsEqual){
            keyClear(key);
        }
        // 如果当前数字的长度加上输入数字字符的长度小于类型限制
        // 则更新bi
        int len = bi.bitValidLength();
        if(len + new BigInteger(key, format).toString(2).length() <= type){
            bi = new BitArray(bi.toString(format)+key, format, type);
        }
    }
    @Override
    protected void keyLBracket(String key){
        expr += "(";
        lBracketCnt++;
    }
    @Override
    protected void keyRBracket(String key){
        if(lBracketCnt > 0) { // 括号匹配
            String el = exprLast();
            boolean exprLastIsDigit = Keyboard.in(Keyboard.HEX_DIGIT, el);
            boolean exprLastIsRBracket = Keyboard.is(Keyboard.RBRACKET, el);
            // 当表达式最后一个元素是数字或右括号时才可以继续添加
            // 右括号
            if(!exprLastIsDigit && !exprLastIsRBracket){
                String num = bi.toString(format).toUpperCase();
                expr += num;
            }
            expr += (")");
            lBracketCnt--;
            bi = new BitArray("0", format, type);
        }
    }
    @Override
    protected void keySign(String key){
        bi = bi.negate();
    }
    @Override
    protected void keyCompute(String key){
        if(Keyboard.is(Keyboard.OR, key)){
            keyBaseOperator("|");
        } else if(Keyboard.is(Keyboard.XOR, key)){
            keyBaseOperator("^");
        } else if(Keyboard.is(Keyboard.AND, key)){
            keyBaseOperator("&");
        } else if(Keyboard.is(Keyboard.NOT, key)){
            bi = bi.not();
        } else if(Keyboard.is(Keyboard.ROL, key)){
            bi = bi.rotateOfLeft();
        } else if(Keyboard.is(Keyboard.ROR, key)){
            bi = bi.rotateOfRight();
        } else if(Keyboard.is(Keyboard.LSH, key)){
            keyBaseOperator("<<");
        } else if(Keyboard.is(Keyboard.RSH, key)){
            keyBaseOperator(">>");
        }
    }
    @Override
    protected String beforeInput(String key){
        if(Keyboard.in(new String[]{Keyboard.POINT}, key)){
            return BREAK_PROCESS;
        }
        return key;
    }

    public String[] output(){
        String bin = formatCompletion(bi.toString(), BIN);
        bin = Utils.addSeparator(bin, 4, false, " ");
        String oct = formatCompletion(bi.toString(OCT), OCT);
        oct = Utils.addSeparator(oct, 3, false, " ");
        String dec = toSignedDecString(bi, type);
        String hex = formatCompletion(bi.toString(HEX).toUpperCase(), HEX);
        hex = Utils.addSeparator(hex, 2, false, " ");
        String ascii = Utils.toASCII(bi.longValue());
        return new String[]{ expr, bi.toString(format).toUpperCase(), bin, oct, dec, hex, ascii };
    }

    public void setFormat(Integer format){
        if(expr.length() != 0){
            expr = exprTransfer(expr, this.format, format).toUpperCase();
        }
        this.format = format;
    }
    public Integer getFormat(){
        return format;
    }
    public void setType(Integer type){
        this.type = type;
        String i1 = bi.toString(2);
        String i2 = "";
        for(int i = 0, j = i1.length()-1; i < type && i < i1.length(); ++i, --j){
            i2 = String.valueOf(i1.charAt(j)) + i2;
        }
        bi = new BitArray(i2, 2, type);
        //bi = new BigInteger(i2, 2);
    }
    public Integer getType(){ return type; }
    public void setNumber(int bit, int value){
        if(value != 0){
            bi = bi.setBit(bit);
        } else {
            bi = bi.clearBit(bit);
        }
    }
    private String transferToKeyboard(String key){
        switch (key){
            case "|":
                return Keyboard.OR;
            case "^":
                return Keyboard.XOR;
            case "&":
                return Keyboard.AND;
            case "<<":
                return Keyboard.LSH;
            case ">>":
                return Keyboard.RSH;
        }
        return key;
    }
    private String exprLast(){
        String exprLast = "";
        if(expr.length() > 0){
            exprLast = Utils.last(expr);
            if(exprLast.equals("<")){
                exprLast += "<";
            } else if(exprLast.equals(">")){
                exprLast += ">";
            }
            exprLast = transferToKeyboard(exprLast);
        }
        return exprLast;
    }
    //
    private static String toSignedDecString(BitArray bi, int type){
        switch (type){
            case BYTE:
                return String.valueOf(bi.byteValue());
            case WORD:
                return String.valueOf(bi.shortValue());
            case DWORD:
                return String.valueOf(bi.intValue());
            case QWORD:
                return String.valueOf(bi.longValue());
        }
        return bi.toString();
    }
    private static String formatCompletion(String str, int format){
        switch (format){
            case BIN:
                return formatCompletion2(str, 4);
            case OCT:
                return formatCompletion2(str, 3);
            case DEC:
                return str;
            case HEX:
                return formatCompletion2(str, 2);
        }
        return str;
    }
    private static String formatCompletion2(String str, int len){
        int i = str.length() % len;
        if(i != 0) {
            if(i == 1 && str.equals("0")) {
                i = len;
            }
            String s = "";
            for (int j = 0; j < len - i; ++j) {
                s += "0";
            }
            str = s + str;
        }
        return str.toUpperCase();
    }
    private static String exprTransfer(String expr, int srcRadix, int objRadix){
        String e = "";
        String num = "";
        for(int i = 0; i <  expr.length(); ++i){
            String ti = expr.substring(i,i+1);
            if(Keyboard.in(Keyboard.HEX_DIGIT,ti)){
                num += ti;
            } else {
                if(num.length() != 0) {
                    e += new BigInteger(num, srcRadix).toString(objRadix) + ti;
                } else {
                    e += ti;
                }
                num = "";
            }
        }
        if(num.length() != 0) {
            e += new BigInteger(num, srcRadix).toString(objRadix);
        }
        return e.toUpperCase();
    }
    private static String opTransfer(String expr){
        expr = expr.replace("×", "*");
        expr = expr.replace("÷","/");
        expr = expr.replace("—", "-");
        return expr;
    }
}
