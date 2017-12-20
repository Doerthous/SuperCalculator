package com.sc.calculate.programmer;

import java.math.BigInteger;

/**
 * Created by Doerthous on 2017/7/2.
 */

public class BitArray extends BigInteger {
    private boolean zeroExtend = false;
    private int bitLength;
    public BitArray(String val, int bitLength) {
        this(val, 2, bitLength);
    }
    public BitArray(String val, int radix, int bitLength) {
        super(val, radix);
        String bin = new BigInteger(val, radix).toString(2);
        if(bin.length() < bitLength){
            zeroExtend = true;
        }
        this.bitLength = bitLength;
    }
    @Override
    public int bitLength(){
        return bitLength;
    }
    public int bitValidLength(){ return super.bitLength(); }
    @Override
    public BitArray not(){
        String bin = toExtendBinString();
        String nb = "";
        for(int i = 0; i < bin.length(); ++i){
            if(bin.charAt(i) == '0'){
                nb += "1";
            } else {
                nb += "0";
            }
        }
        return new BitArray(nb, bitLength);
    }
    @Override
    public BitArray negate(){
        return new BitArray(not().add(BigInteger.ONE).toString(2), bitLength);
    }
    @Override
    public BitArray shiftLeft(int n){
        return  new BitArray(super.shiftLeft(n).toString(2), bitLength);
    }
    @Override
    public BitArray shiftRight(int n){
        return  new BitArray(super.shiftRight(n).toString(2), bitLength);
    }
    @Override
    public BitArray setBit(int n){
        StringBuilder bin = new StringBuilder(toExtendBinString());
        bin = bin.reverse();
        bin.setCharAt(n, '1');
        bin = bin.reverse();
        return new BitArray(bin.toString(), bitLength);
    }
    @Override
    public BitArray clearBit(int n){
        StringBuilder bin = new StringBuilder(toExtendBinString());
        bin = bin.reverse();
        bin.setCharAt(n, '0');
        bin = bin.reverse();
        return new BitArray(bin.toString(), bitLength);
    }
    public BitArray rotateOfLeft(){
        String bin = toExtendBinString();
        return new BitArray(bin.substring(1, bin.length())+bin.charAt(0), bitLength);
    }
    public BitArray rotateOfRight(){
        String bin = toExtendBinString();
        int bLen = bin.length()-1;
        return new BitArray(bin.charAt(bLen) + bin.substring(0, bLen), bitLength);
    }
    @Override
    public String toString(){
        return toString(2);
    }
    @Override
    public String toString(int radix){
        return new BigInteger(toExtendBinString(), 2).toString(radix);
    }
    private String toExtendBinString(){
        String bin = super.toString(2);
        String sign = "1";
        if(zeroExtend){
            sign = "0";
        }
        while (bin.length() < bitLength) { // 补全
            bin = sign + bin;
        }
        if(bin.length() > bitLength){ // 截取
            String t = "";
            for(int i = 0; i < bitLength; ++i){
                t = bin.charAt(bin.length()-1-i) + t;
            }
            bin = t;
        }
        return bin;
    }
}
