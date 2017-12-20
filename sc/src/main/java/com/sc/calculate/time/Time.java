package com.sc.calculate.time;

import com.sc.calculate.ModuleWithKeyboard;
import com.sc.datastructrue.records.TimeCalResult;
import com.sc.datastructrue.TimeStruct;
import com.sc.utity.Keyboard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Doerthous on 2017/6/28.
 */

public class Time extends ModuleWithKeyboard {

    private static final int DATE = 0;
    private static final int NUM = 1;
    private static final int ADD = 2;
    private static final int SUB = 3;
    private static final int EQU = 4;
    private static final int CLR = 5;
    private static final int DEL = 6;

    private int state = 1;
    private int inputType;
    private TimeStruct ts = new TimeStruct();
    private SimpleDateFormat df;

    public Time(SimpleDateFormat df){
        this.df = df;
    }
    private int date(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(ts.src);
        long time1 = cal.getTimeInMillis();
        cal.setTime(ts.obj);
        long time2 = cal.getTimeInMillis();
        long between_days=(time1-time2)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));
    }
    private Date days(){
        Calendar cal=Calendar.getInstance();
        cal.setTime(ts.src);
        int days = ts.days;
        if(Keyboard.is(Keyboard.SUB, ts.op)){
            days = -days;
        }
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }
    private String getResult(){
        String retVal;
        if(ts.obj != null){
            retVal = String.valueOf(date());
        } else {
            retVal = df.format(days());
        }
        return retVal;
    }
    private void move(String data, int type) throws ParseException {
        if(type == CLR || state == 10 || state == 9 || state == 8){
            state = 1;
        }
        switch (state){
            case 1:
                if(type == DATE){
                    ts = new TimeStruct();
                    ts.src = df.parse(data);
                    state = 2;
                }
                break;
            case 2:
                if(type == ADD){
                    ts.op = data;
                    state = 3;
                } else if(type == SUB){
                    ts.op = data;
                    state = 4;
                } else if(type == DATE){
                    ts.src = df.parse(data);
                } else if(type == DEL){
                    state = 1;
                }
                break;
            case 3:
                if (type == NUM){
                    ts.days = Integer.valueOf(data);
                    state = 6;
                } else if(type == SUB){
                    ts.op = data;
                    state = 4;
                } else if(type == DATE){
                    ts.src = df.parse(data);
                }
                break;
            case 4:
                if (type == NUM){
                    ts.days = Integer.valueOf(data);
                    state = 7;
                } else if(type == ADD){
                    ts.op = data;
                    state = 3;
                }  else if(type == DATE) {
                    ts.obj = df.parse(data);
                    state = 5;
                }
                break;
            case 5:
                if (type == NUM){
                    ts.days = Integer.valueOf(data);
                    state = 6;
                } else if(type == EQU){
                    state = 8;
                } else if(type == DEL){
                    state = 4;
                }
                break;
            case 6:
                if (type == DATE){
                    ts.obj = df.parse(data);
                    state = 5;
                } else if(type == EQU){
                    state = 9;
                } else if(type == SUB) {
                    ts.op = data;
                    state = 7;
                } else if(type == NUM){
                    ts.days = Integer.valueOf(ts.days+data);
                } else if(type == DEL){
                    state = 3;
                }
                break;
            case 7:
                if (type == DATE){
                    ts.obj = df.parse(data);
                    state = 5;
                } else if(type == EQU){
                    state = 10;
                } else if(type == ADD) {
                    ts.op = data;
                    state = 6;
                } else if(type == NUM){
                    ts.days = Integer.valueOf(ts.days+data);
                } else if(type == DEL){
                    state = 4;
                }
                break;
        }
    }

    @Override
    public void keyClear(String key){
        inputType = CLR;
    }
    @Override
    public void keyDelete(String key){
        inputType = DEL;
    }
    @Override
    public void keyBaseOperator(String key){
        if (Keyboard.is(Keyboard.ADD, key)){
            inputType = ADD;
        } else if(Keyboard.is(Keyboard.SUB, key)){
            inputType = SUB;
        }
    }
    @Override
    public void keyEqual(String key){
        inputType = EQU;
    }
    @Override
    public void keyDecDigit(String key){
        inputType = NUM;
    }
    @Override
    public void keyOther(String key){
        inputType = DATE;
    }
    @Override
    public void afterInput(String key){
        try {
            move(key, inputType);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public TimeCalResult output(){
        String[] retVal = new String[]{"",""};
        switch (state){
            case 1:
                break;
            case 2:
                retVal =  new String[]{"",df.format(ts.src)};
                break;
            case 3:
            case 4:
                retVal =  new String[]{df.format(ts.src)+ts.op,""};
                break;
            case 5:
                retVal =  new String[]{df.format(ts.src)+ts.op,df.format(ts.obj)};
                break;
            case 6:
            case 7:
                retVal =  new String[]{df.format(ts.src)+ts.op,ts.days.toString()};
                break;
            case 8:
                retVal =  new String[]{df.format(ts.src)+ts.op+df.format(ts.obj)+"=",getResult()};
                writeRecord(new TimeCalResult(retVal[0], retVal[1]));
                break;
            case 9:
            case 10:
                retVal =  new String[]{df.format(ts.src)+ts.op+ts.days.toString()+"=",getResult()};
                writeRecord(new TimeCalResult(retVal[0], retVal[1]));
                break;
        }
        return new TimeCalResult(retVal[0], retVal[1]);
    }
}


   /*public void input(String data) {
        int type = 0;
        if(Utils.isInteger(data)){
            type = NUM;
        } else if (Keyboard.is(Keyboard.ADD, data)){
            type = ADD;
        } else if(Keyboard.is(Keyboard.SUB, data)){
            type = SUB;
        } else if(Keyboard.is(Keyboard.EQU, data)){
            type = EQU;
        } else if(Keyboard.is(Keyboard.CLR, data)){
            type = CLR;
        } else if(Keyboard.is(Keyboard.DEL, data)){
            type = DEL;
        } else {
            type = DATE;
        }
        try {
            move(data, type);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }*/