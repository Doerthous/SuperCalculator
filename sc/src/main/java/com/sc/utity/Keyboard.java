package com.sc.utity;

/**
 * Created by Doerthous on 2017/6/28.
 */

public class Keyboard {
    // 数字
    public static final String ZERO     = "0";
    public static final String ONE      = "1";
    public static final String TWO      = "2";
    public static final String THREE    = "3";
    public static final String FOUR     = "4";
    public static final String FIVE     = "5";
    public static final String SIX      = "6";
    public static final String SEVEN    = "7";
    public static final String EIGHT    = "8";
    public static final String NINE     = "9";
    public static final String TEN      = "A";
    public static final String ELEVEN   = "B";
    public static final String TWELVE   = "C";
    public static final String THIRTEEN = "D";
    public static final String FOURTEEN = "E";
    public static final String FIFTEEN  = "F";
    // 数学常数
    public static final String PI       = "π";
    public static final String NB       = "nature_base"; // 自然底数e
    // 基本操作符
    public static final String ADD      = "+";
    public static final String SUB      = "—";
    public static final String MUL      = "×";
    public static final String DIV      = "÷";
    // 计算机运算符
    public static final String MOD      = "%";
    public static final String LSH      = "lsh";
    public static final String RSH      = "rsh";
    public static final String ROL      = "rol";
    public static final String ROR      = "ror";
    public static final String OR       = "or";
    public static final String XOR      = "xor";
    public static final String AND      = "and";
    public static final String NOT      = "not";
    // 功能键
    public static final String CLR      = "Clear";
    public static final String DEL      = "Del";
    public static final String CE       = "CE";
    public static final String UP       = "↑";
    public static final String LEFT     = "←";
    public static final String RIGHT    = "→";
    // ?
    public static final String LBRACKET = "(";
    public static final String RBRACKET = ")";
    public static final String SIGN     = "±";
    public static final String POINT    = ".";
    public static final String EQU      = "=";
    // 幂函数
    public static final String SQRT     = "√x";
    public static final String SQUARE   = "x²";
    public static final String CBRT     = "³√x";
    public static final String CUBE     = "x³";
    public static final String NTHRT    = "ⁿ√x";
    public static final String NTHPW    = "xⁿ";
    // 三角函数
    public static final String DEG      = "deg";
    public static final String RAD      = "rad";
    public static final String SIN      = "sin";
    public static final String COS      = "cos";
    public static final String TAN      = "tan";
    public static final String ASIN     = "asin";
    public static final String ACOS     = "acos";
    public static final String ATAN     = "atan";
    // 对数函数
    public static final String LN       = "ln";
    public static final String LOG      = "log";
    // 常用数学函数
    public static final String FTR      = "n!";
    public static final String TEN_NTHPW= "10ⁿ";
    public static final String NB_NTHPW = "eⁿ";
    public static final String DIV_BY_X = "1/x";

    //关系


    public static final String[] BIN_DIGIT = {
            ZERO, ONE,
    };
    public static final String[] OCT_DIGIT = {
            ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN,
    };
    public static final String[] DEC_DIGIT = {
            ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE,
    };
    public static final String[] HEX_DIGIT = {
            ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE,
            TEN, ELEVEN, TWELVE, THIRTEEN, FOURTEEN, FIFTEEN,
    };
    public static final String[] CONSTANT = {
            PI, NB,
    };
    public static final String[] BASE_OPERATOR = {
            ADD, SUB, MUL, DIV,
    };
    public static final String[] OPERATOR = {
            ADD, SUB, MUL, DIV, MOD, SIN, COS, TAN, ASIN, ACOS, ATAN, SQRT, SQUARE, CBRT,
            CUBE, NTHRT, NTHPW, LOG, LN, FTR, AND, OR, NOT, XOR, LSH, RSH, ROL, ROR,
    };
    public static final String[] TRIGONOMETRIC_OPERATOR = {
            SIN, COS, TAN, ASIN, ACOS, ATAN,
    };
    public static final String[] POWER_OPERATOR = {
            SQRT, SQUARE, CBRT, CUBE, NTHRT, NTHPW,
    };
    public static final String[] LOGARITHMIC_OPERATOR = {
            LOG, LN,
    };
    public static final String [] EXPONENTIAL_OPERATOR = {
            ,
    };
    public static final String[] COMPUTE_OPERATOR = {
            MOD, OR, XOR, AND, NOT, ROL, ROR, LSH, RSH
    };

    public static boolean is(String KEYCODE, String string){
        return KEYCODE.equalsIgnoreCase(string);
    }
    public static boolean is(String KEYCODE, Character ch){
        return KEYCODE.equalsIgnoreCase(String.valueOf(ch));
    }
    public static boolean in(String[] KEYCODE, String string){
        for(String key : KEYCODE){
            if(is(key, string)){
                return true;
            }
        }
        return false;
    }
    public static boolean in(String[] KEYCODE, Character ch){
        return in(KEYCODE, String.valueOf(ch));
    }
    public static boolean isOperator(String string){
        if(Keyboard.is(Keyboard.ADD, string)){
            return true;
        }
        if(Keyboard.is(Keyboard.SUB, string)){
            return true;
        }
        if(Keyboard.is(Keyboard.MUL, string)){
            return true;
        }
        if(Keyboard.is(Keyboard.DIV, string)){
            return true;
        }
        if(Keyboard.is(Keyboard.MOD,string)){
            return true;
        }
        return false;
    }
}
