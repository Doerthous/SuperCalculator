package com.sc.calculate;

import com.sc.utity.Keyboard;

/**
 * Created by Doerthous on 2017/7/5.
 */

public class ModuleWithKeyboard extends ModuleBase{

    protected String BREAK_PROCESS = "BREAK_PROCESS";

    // for override
    protected void keyBaseOperator(String key){}
    protected void keyOperator(String key){}
    protected void keyBinDigit(String key){}
    protected void keyOctDigit(String key){}
    protected void keyDecDigit(String key){}
    protected void keyHexDigit(String key){}
    protected void keyEqual(String key){}
    protected void keyFunctional(String key){}
    protected void keyClear(String key){}
    protected void keyDelete(String key){}
    protected void keyClearError(String key){}
    protected void keySign(String key){}
    protected void keyLBracket(String key){}
    protected void keyRBracket(String key){}
    protected void keyBracket(String key){}
    protected void keyOther(String key){}
    protected void keyTrigonometric(String key){}
    protected void keyPower(String key){}
    protected void keyLogarithmic(String key){}
    protected void keyExponential(String key){}
    protected void keySqrt(String key){}
    protected void keySquare(String key){}
    protected void keyCbrt(String key){}
    protected void keyCube(String key){}
    protected void keyNthrt(String key){}
    protected void keyNthpw(String key){}
    protected void keyCompute(String key){}
    protected void keyPoint(String key){}
    protected void keyConstant(String key){}

    protected String beforeInput(String in){ return in; }
    public void input(String in){
        in = beforeInput(in);
        if(in.equals(BREAK_PROCESS)){ // 返回命令控制输入过程
            return;
        }
        if(Keyboard.is(Keyboard.CLR, in)){
            keyClear(in);
        } else if(Keyboard.is(Keyboard.DEL, in)){
            keyDelete(in);
        } else if(Keyboard.is(Keyboard.CE, in)){
            keyClearError(in);
        } else if(Keyboard.in(Keyboard.CONSTANT, in)){
            keyConstant(in);
        } else if(Keyboard.in(Keyboard.OPERATOR, in)){
            keyOperator(in);
            if(Keyboard.in(Keyboard.BASE_OPERATOR, in) || Keyboard.is(Keyboard.MOD, in)){
                keyBaseOperator(in);
            } else if(Keyboard.in(Keyboard.TRIGONOMETRIC_OPERATOR, in)){
                keyTrigonometric(in);
            } else if(Keyboard.in(Keyboard.POWER_OPERATOR, in)){
                keyPower(in);
                if(Keyboard.is(Keyboard.SQRT, in)){
                    keySqrt(in);
                } else if(Keyboard.is(Keyboard.SQUARE, in)){
                    keySquare(in);
                } else if(Keyboard.is(Keyboard.CBRT, in)){
                    keyCbrt(in);
                } else if(Keyboard.is(Keyboard.CUBE, in)){
                    keyCube(in);
                } else if(Keyboard.is(Keyboard.NTHRT, in)){
                    keyNthrt(in);
                } else if(Keyboard.is(Keyboard.NTHPW, in)){
                    keyNthpw(in);
                }
            } else if(Keyboard.in(Keyboard.LOGARITHMIC_OPERATOR, in)){
                keyLogarithmic(in);
            } else if(Keyboard.in(Keyboard.EXPONENTIAL_OPERATOR, in)){
                keyExponential(in);
            } else if(Keyboard.in(Keyboard.COMPUTE_OPERATOR, in)){
                keyCompute(in);
            }
        } else if(Keyboard.is(Keyboard.EQU, in)){
            keyEqual(in);
        } else if(Keyboard.is(Keyboard.SIGN, in)){
            keySign(in);
        } else if(Keyboard.is(Keyboard.LBRACKET, in)){
            keyLBracket(in);
            keyBracket(in);
        } else if(Keyboard.is(Keyboard.RBRACKET, in)){
            keyRBracket(in);
            keyBracket(in);
        } else if(Keyboard.is(Keyboard.POINT, in)){
            keyPoint(in);
        } else {
            if(Keyboard.in(Keyboard.HEX_DIGIT, in)){
                keyHexDigit(in);
                if(Keyboard.in(Keyboard.DEC_DIGIT, in)){
                    keyDecDigit(in);
                    if(Keyboard.in(Keyboard.OCT_DIGIT, in)){
                        keyOctDigit(in);
                        if(Keyboard.in(Keyboard.BIN_DIGIT, in)){
                            keyBinDigit(in);
                        }
                    }
                }
            } else {
                keyOther(in);
            }
        }
        afterInput(in);
    }
    protected void afterInput(String in){ }
}
