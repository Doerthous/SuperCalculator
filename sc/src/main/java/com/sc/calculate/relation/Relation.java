package com.sc.calculate.relation;

import android.util.Log;

import com.sc.calculate.ModuleBase;
import com.sc.datastructrue.records.RelationCalResult;
import com.sc.utity.Keyboard;

/**
 * Created by Dromatack on 2017/7/3.
 */

public class Relation extends ModuleBase{
    static Boolean isMale = true;
    static Boolean isFromMe = true;
    static String expression = "自己";
    static String relation = "自己";
    static String result = "";
    static String lastInput = "自己";

    public static void init(){RelationTree.init();}

    public Relation(){
        isMale = true;
        isFromMe = true;
        expression = "自己";
        relation = "自己";
        result = "";
        lastInput = "自己";
    }

    public void inputRlt(String in){
        switch (in){
            case "C":
                expression = "自己";
                relation = "自己";
                result = "";
                lastInput = "自己";
                break;
            case "Del":
                if (relation.length() > 2){
                    if ("=".equals(String.valueOf(relation.charAt(relation.length()-1))))
                    {
                        relation = relation.substring(0,relation.length()-1);
                    }else {
                        expression = expression.substring(0, expression.length() - 2);
                        relation = relation.substring(0, relation.length() - 3);
                    }
                }
                if(expression .length() > 2){
                    lastInput = expression.substring(expression.length()-1,expression.length());
                }else{
                    lastInput = "自己";
                }
                if (relation.length() ==2){
                    result = "";
                }
                compute();
                break;
            case "男生":
                isMale = false;
                compute();
                break;
            case "女生":
                isMale = true;
                compute();
                break;
            case "互换":
                if (isFromMe){
                    isFromMe = false;
                    compute();
                }else {
                    isFromMe = true;
                    compute();
                }
                break;
            case "=":
                if (!"".equals(relation)&&!relation.contains(" ")){
                    if (!"=".equals(String.valueOf(relation.charAt(relation.length()-1)))){
                        compute();
                        relation = relation + "=";
                    }
                }
                break;
            case "父":
               addname(in,"的爸爸");
                break;
            case "母":
                addname(in,"的妈妈");
                break;
            case "儿":
                addname(in,"的儿子");
                break;
            case "女":
                addname(in,"的女儿");
                break;
            case "兄":
                addname(in,"的哥哥");
                break;
            case "弟":
                addname(in,"的弟弟");
                break;
            case "姐":
                addname(in,"的姐姐");
                break;
            case "妹":
                addname(in,"的妹妹");
                break;
            case "夫":
                addname(in,"的丈夫");
                break;
            case "妻":
                addname(in,"的妻子");
                break;
            default:
                inputName(in);
        }


    }

    public void inputName(String in){
        /*String[] relationName = {"爷爷","奶奶","祖父","祖母", "姑妈","姑丈","伯父","伯母","叔父","婶婶","表哥","表弟","表姐","表妹"
        ,"哥哥","弟弟","嫂嫂","弟媳","姐姐","妹妹","姐夫","妹婿","侄子","侄女","外甥","外甥女","儿子","女儿","儿媳"
        ,"女婿","孙子","孙女","外孙","外孙女","舅舅","舅妈","姨妈","姨丈","外祖父","外祖母","堂哥","堂弟","堂姐","堂妹"
        ,"妻子","丈夫"};
        if (!Keyboard.in(relationName,in)){*/
            expression = in;
            if ("".equals(expression)){
                relation = "";
                result = "请输入称谓";
            }else {
                relation = in  + " 是自己";
                result = RelationTree.findRelation(expression);

            }
    }

    public RelationCalResult output()
    {
        RelationCalResult op = new RelationCalResult(relation, result);
        if(relation.contains(Keyboard.EQU) || relation.contains("是")){
            writeRecord(op);
        }
        return op;
    }

    public static  Boolean genderLimit(){
        String[] male = {"父","子","兄","弟","夫"};
        String[] female = {"母","女","姐","妹","妻"};
        Log.e("lastInput",lastInput);
        if("自己".equals(lastInput)){
            if (isMale){
                return true;
            }else{
                return false;
            }
        }else{
            Boolean i = true;
            for (String m : male){
                if(m.equals(lastInput)){
                    i = true;
                    break;
                }
            }

            for (String f : female){
                if(f.equals(lastInput)){
                    i = false;
                    break;
                }
            }
           return i;
        }
    }
    public static
    void compute(){
        Log.e("FromMe",String.valueOf(isFromMe));
        if (isFromMe){
            String find = RelationTree.findCallFromMe(expression,isMale);
            result = find;
        }else{
            String find = RelationTree.findCallToMe(expression,isMale);
            result = "称呼自己为 "+ find;
        }

    }
    public static void addname(String in,String relationName){
        if (!"".equals(relation)&&!relation.contains(" ")){
            expression = expression + "的" + in;
            relation = relation + relationName;
            lastInput = in;
            compute();
        }else{
                expression = "自己";
                relation = "自己";
                result = "";
                lastInput = "自己";
                expression = expression + "的" + in;
                relation = relation + relationName;
                lastInput = in;
                compute();
        }
    }
}
