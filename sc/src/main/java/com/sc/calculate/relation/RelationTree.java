package com.sc.calculate.relation;

import com.sc.utity.Dict2D;
import com.sc.utity.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Dromatack on 2017/7/3.
 */

public class RelationTree {
    private static Dict2D<String, String, String> relationMale;
    private static Dict2D<String, String, String> relationFemale;

    public static boolean sex()
    {
        if(false)
            return false;
        else
            return  true;
    }

    public static void init(){

        relationMale = new Dict2D<>();
        relationFemale = new Dict2D<>();
        //
            relationMale.put("自己", "父", "爸爸");
            relationMale.put("自己", "母", "妈妈");
            relationMale.put("自己", "兄", "哥哥");
            relationMale.put("自己", "弟", "弟弟");
            relationMale.put("自己", "姐", "姐姐");
            relationMale.put("自己", "妹", "妹妹");
            relationMale.put("自己", "儿", "儿子");
            relationMale.put("自己", "女", "女儿");
            relationMale.put("自己", "妻", "妻子");

            //
            relationMale.put("爸爸", "父", "祖父");
            relationMale.put("爸爸", "母", "祖母");
            relationMale.put("爸爸", "兄", "伯父");
            relationMale.put("爸爸", "弟", "叔父");
            relationMale.put("爸爸", "姐", "姑妈");
            relationMale.put("爸爸", "妹", "姑妈");
            relationMale.put("爸爸", "儿", "自己,哥哥,弟弟"); //?
            relationMale.put("爸爸", "女", "姐姐,妹妹");
            relationMale.put("爸爸", "妻", "妈妈");
            //
            relationMale.put("妈妈", "父", "外祖父");
            relationMale.put("妈妈", "母", "外祖母");
            relationMale.put("妈妈", "兄", "舅舅");
            relationMale.put("妈妈", "弟", "舅舅");
            relationMale.put("妈妈", "姐", "姨妈");
            relationMale.put("妈妈", "妹", "姨妈");
            relationMale.put("妈妈", "儿", "自己,哥哥,弟弟");
            relationMale.put("妈妈", "女", "姐姐,妹妹");
            relationMale.put("妈妈", "夫", "爸爸");
            //
            relationMale.put("哥哥", "父", "爸爸");
            relationMale.put("哥哥", "母", "妈妈");
            relationMale.put("哥哥", "兄", "哥哥");
            relationMale.put("哥哥", "弟", "自己,哥哥,弟弟");
            relationMale.put("哥哥", "姐", "姐姐");
            relationMale.put("哥哥", "妹", "姐姐,妹妹");
            relationMale.put("哥哥", "儿", "侄子2");
            relationMale.put("哥哥", "女", "侄女2");
            relationMale.put("哥哥", "妻", "嫂嫂");
            //
            relationMale.put("弟弟", "父", "爸爸");
            relationMale.put("弟弟", "母", "妈妈");
            relationMale.put("弟弟", "兄", "自己,哥哥,弟弟");
            relationMale.put("弟弟", "弟", "弟弟");
            relationMale.put("弟弟", "姐", "姐姐,妹妹");
            relationMale.put("弟弟", "妹", "妹妹");
            relationMale.put("弟弟", "儿", "侄子1");
            relationMale.put("弟弟", "女", "侄女1");
            relationMale.put("弟弟", "妻", "弟媳");
            //
            relationMale.put("姐姐", "父", "爸爸");
            relationMale.put("姐姐", "母", "妈妈");
            relationMale.put("姐姐", "兄", "哥哥");
            relationMale.put("姐姐", "弟", "自己,哥哥,弟弟");
            relationMale.put("姐姐", "姐", "姐姐");
            relationMale.put("姐姐", "妹", "姐姐,妹妹");
            relationMale.put("姐姐", "儿", "外甥2");
            relationMale.put("姐姐", "女", "外甥女2");
            relationMale.put("姐姐", "夫", "姐夫");
            //
            relationMale.put("妹妹", "父", "爸爸");
            relationMale.put("妹妹", "母", "妈妈");
            relationMale.put("妹妹", "兄", "自己,哥哥,弟弟");
            relationMale.put("妹妹", "弟", "弟弟");
            relationMale.put("妹妹", "姐", "姐姐,妹妹");
            relationMale.put("妹妹", "妹", "妹妹");
            relationMale.put("妹妹", "儿", "外甥1");
            relationMale.put("妹妹", "女", "外甥女1");
            relationMale.put("妹妹", "夫", "妹婿");
            //
            relationMale.put("女儿", "父", "自己");
            relationMale.put("女儿", "母", "妻子");
            relationMale.put("女儿", "兄", "儿子");
            relationMale.put("女儿", "弟", "儿子");
            relationMale.put("女儿", "姐", "女儿");
            relationMale.put("女儿", "妹", "女儿");
            relationMale.put("女儿", "儿", "外孙");
            relationMale.put("女儿", "女", "外孙女");
            relationMale.put("女儿", "夫", "女婿");
            //
            relationMale.put("儿子", "父", "自己");
            relationMale.put("儿子", "母", "妻子");
            relationMale.put("儿子", "兄", "儿子");
            relationMale.put("儿子", "弟", "儿子");
            relationMale.put("儿子", "姐", "女儿");
            relationMale.put("儿子", "妹", "女儿");
            relationMale.put("儿子", "儿", "孙子");
            relationMale.put("儿子", "女", "孙女");
            relationMale.put("儿子", "妻", "儿媳");
            //
            relationMale.put("妻子", "父", "爸爸");
            relationMale.put("妻子", "母", "妈妈");
            relationMale.put("妻子", "兄", "哥哥");
            relationMale.put("妻子", "弟", "弟弟");
            relationMale.put("妻子", "姐", "姐姐");
            relationMale.put("妻子", "妹", "妹妹");
            relationMale.put("妻子", "儿", "儿子");
            relationMale.put("妻子", "女", "女儿");
            relationMale.put("妻子", "夫", "自己");
            //

            //
            relationMale.put("伯父", "父", "祖父");
            relationMale.put("伯父", "母", "祖母");
            relationMale.put("伯父", "兄", "伯父");
            relationMale.put("伯父", "弟", "叔父,伯父,爸爸");//?
            relationMale.put("伯父", "姐", "姑母");
            relationMale.put("伯父", "妹", "姑母");
            relationMale.put("伯父", "儿", "堂弟2,堂哥2");
            relationMale.put("伯父", "女", "堂妹2,堂姐2");
            relationMale.put("伯父", "妻", "伯母");
            //
            relationMale.put("叔父", "父", "祖父");
            relationMale.put("叔父", "母", "祖母");
            relationMale.put("叔父", "兄", "伯父,叔父,爸爸");//?
            relationMale.put("叔父", "弟", "叔父");
            relationMale.put("叔父", "姐", "姑母");
            relationMale.put("叔父", "妹", "姑母");
            relationMale.put("叔父", "儿", "堂弟1,堂哥1");
            relationMale.put("叔父", "女", "堂妹1,堂姐1");
            relationMale.put("叔父", "妻", "婶婶");
            //
            relationMale.put("姑妈", "父", "祖父");
            relationMale.put("姑妈", "母", "祖母");
            relationMale.put("姑妈", "兄", "伯父,叔父,爸爸");//?
            relationMale.put("姑妈", "弟", "叔父,伯父,爸爸");//?
            relationMale.put("姑妈", "姐", "姑妈");
            relationMale.put("姑妈", "妹", "姑妈");
            relationMale.put("姑妈", "儿", "表哥3,表弟3");
            relationMale.put("姑妈", "女", "表姐3,表妹3");
            relationMale.put("姑妈", "夫", "姑丈");
            //
            relationMale.put("姑丈", "儿", "表哥3,表弟3");
            relationMale.put("姑丈", "女", "表姐3,表妹3");
            relationMale.put("姑丈", "妻", "姑妈");
            //
            relationMale.put("伯母", "儿", "堂弟2,堂哥2");
            relationMale.put("伯母", "女", "堂妹2,堂姐2");
            relationMale.put("伯母", "夫", "伯父");
            //
            relationMale.put("婶婶", "儿", "堂弟1,堂哥1");
            relationMale.put("婶婶", "女", "堂妹1,堂姐1");
            relationMale.put("婶婶", "夫", "叔父");
            //
            relationMale.put("祖父", "儿", "伯父,叔父,爸爸");//?
            relationMale.put("祖父", "女", "姑妈");
            relationMale.put("祖父", "妻", "祖母");
            //
            relationMale.put("祖母", "儿", "伯父,叔父,爸爸");//?
            relationMale.put("祖母", "女", "姑妈");
            relationMale.put("祖母", "夫", "祖父");
            //
            relationMale.put("舅舅", "父", "外祖父");
            relationMale.put("舅舅", "母", "外祖母");
            relationMale.put("舅舅", "兄", "舅舅");
            relationMale.put("舅舅", "弟", "舅舅");
            relationMale.put("舅舅", "姐", "姨妈,妈妈");
            relationMale.put("舅舅", "妹", "姨妈");
            relationMale.put("舅舅", "儿", "表哥1,表弟1");
            relationMale.put("舅舅", "女", "表姐1,表妹1");
            relationMale.put("舅舅", "妻", "舅妈");
            //
            relationMale.put("舅妈", "儿", "表哥1,表弟1");
            relationMale.put("舅妈", "女", "表姐1,表妹1");
            relationMale.put("舅妈", "夫", "舅舅");
            //
            relationMale.put("姨妈", "父", "外祖父");
            relationMale.put("姨妈", "母", "外祖母");
            relationMale.put("姨妈", "兄", "舅舅");
            relationMale.put("姨妈", "弟", "舅舅");
            relationMale.put("姨妈", "姐", "姨妈,妈妈");
            relationMale.put("姨妈", "妹", "姨妈,妈妈");
            relationMale.put("姨妈", "儿", "表哥1,表弟1");
            relationMale.put("姨妈", "女", "表姐1,表妹1");
            relationMale.put("姨妈", "夫", "姨丈");
            //
            relationMale.put("姨丈", "儿", "表哥1,表弟1");
            relationMale.put("姨丈", "女", "表姐1,表妹1");
            relationMale.put("姨丈", "妻", "姨妈");
            //
            relationMale.put("外祖父", "儿", "舅舅");
            relationMale.put("外祖父", "女", "姨妈,妈妈");
            relationMale.put("外祖父", "妻", "外祖母");
            //
            relationMale.put("外祖母", "儿", "舅舅");
            relationMale.put("外祖母", "女", "姨妈,妈妈");
            relationMale.put("外祖母", "夫", "外祖父");
            //
            relationMale.put("女婿", "儿", "外孙");
            relationMale.put("女婿", "女", "外孙女");
            relationMale.put("女婿", "妻", "女儿");
            //
            relationMale.put("儿媳", "儿", "孙子");
            relationMale.put("儿媳", "女", "孙女");
            relationMale.put("儿媳", "夫", "儿子");
            //
            relationMale.put("妹婿", "儿", "外甥1");
            relationMale.put("妹婿", "女", "外甥女1");
            relationMale.put("妹婿", "妻", "妹妹");
            //
            relationMale.put("弟媳", "儿", "侄子1");
            relationMale.put("弟媳", "女", "侄女1");
            relationMale.put("弟媳", "夫", "弟弟");

            //
            relationMale.put("嫂嫂", "儿", "侄子2");
            relationMale.put("嫂嫂", "女", "侄女2");
            relationMale.put("嫂嫂", "夫", "哥哥");
            //
            relationMale.put("姐夫", "儿", "外甥2");
            relationMale.put("姐夫", "女", "外甥女2");
            relationMale.put("姐夫", "妻", "姐姐");
            //
            relationMale.put("侄子1", "父", "弟弟");
            relationMale.put("侄子1", "母", "弟媳");
            relationMale.put("侄子1", "兄", "侄子1");
            relationMale.put("侄子1", "弟", "侄子1");
            relationMale.put("侄子1", "姐", "侄女1");
            relationMale.put("侄子1", "妹", "侄女1");
            //
            relationMale.put("侄子2", "父", "哥哥");
            relationMale.put("侄子2", "母", "嫂嫂");
            relationMale.put("侄子2", "兄", "侄子2");
            relationMale.put("侄子2", "弟", "侄子2");
            relationMale.put("侄子2", "姐", "侄女2");
            relationMale.put("侄子2", "妹", "侄女2");
            //
            relationMale.put("侄女1", "父", "弟弟");
            relationMale.put("侄女1", "母", "弟媳");
            relationMale.put("侄女1", "兄", "侄子1");
            relationMale.put("侄女1", "弟", "侄子1");
            relationMale.put("侄女1", "姐", "侄女1");
            relationMale.put("侄女1", "妹", "侄女1");
            //
            relationMale.put("侄女2", "父", "哥哥");
            relationMale.put("侄女2", "母", "嫂嫂");
            relationMale.put("侄女2", "兄", "侄子2");
            relationMale.put("侄女2", "弟", "侄子2");
            relationMale.put("侄女2", "姐", "侄女2");
            relationMale.put("侄女2", "妹", "侄女2");
            //
            relationMale.put("外甥女1", "父", "妹婿");
            relationMale.put("外甥女1", "母", "妹妹");
            relationMale.put("外甥女1", "兄", "外甥1");
            relationMale.put("外甥女1", "弟", "外甥1");
            relationMale.put("外甥女1", "姐", "外甥女1");
            relationMale.put("外甥女1", "妹", "外甥女1");
            //
            relationMale.put("外甥1", "父", "妹婿");
            relationMale.put("外甥1", "母", "妹妹");
            relationMale.put("外甥1", "兄", "外甥1");
            relationMale.put("外甥1", "弟", "外甥1");
            relationMale.put("外甥1", "姐", "外甥女1");
            relationMale.put("外甥1", "妹", "外甥女1");
            //
            relationMale.put("外甥女2", "父", "姐夫");
            relationMale.put("外甥女2", "母", "姐姐");
            relationMale.put("外甥女2", "兄", "外甥2");
            relationMale.put("外甥女2", "弟", "外甥2");
            relationMale.put("外甥女2", "姐", "外甥女2");
            relationMale.put("外甥女2", "妹", "外甥女2");
            //
            relationMale.put("外甥2", "父", "姐夫");
            relationMale.put("外甥2", "母", "姐姐");
            relationMale.put("外甥2", "兄", "外甥2");
            relationMale.put("外甥2", "弟", "外甥2");
            relationMale.put("外甥2", "姐", "外甥女2");
            relationMale.put("外甥2", "妹", "外甥女2");

            //
            relationMale.put("孙子", "父", "儿子");
            relationMale.put("孙子", "母", "儿媳");
            relationMale.put("孙子", "兄", "孙子");
            relationMale.put("孙子", "弟", "孙子");
            relationMale.put("孙子", "姐", "孙女");
            relationMale.put("孙子", "妹", "孙女");
            //
            relationMale.put("孙女", "父", "儿子");
            relationMale.put("孙女", "母", "儿媳");
            relationMale.put("孙女", "兄", "孙子");
            relationMale.put("孙女", "弟", "孙子");
            relationMale.put("孙女", "姐", "孙女");
            relationMale.put("孙女", "妹", "孙女");
            //
            relationMale.put("外孙", "父", "女婿");
            relationMale.put("外孙", "母", "女儿");
            relationMale.put("外孙", "兄", "外孙");
            relationMale.put("外孙", "弟", "外孙");
            relationMale.put("外孙", "姐", "外孙女");
            relationMale.put("外孙", "妹", "外孙女");
            //
            relationMale.put("外孙女", "父", "女婿");
            relationMale.put("外孙女", "母", "女儿");
            relationMale.put("外孙女", "兄", "外孙");
            relationMale.put("外孙女", "弟", "外孙");
            relationMale.put("外孙女", "姐", "外孙女");
            relationMale.put("外孙女", "妹", "外孙女");
            //
            relationMale.put("表弟1", "父", "舅舅");
            relationMale.put("表弟1", "母", "舅妈");
            relationMale.put("表弟1", "兄", "表弟1,表哥1");
            relationMale.put("表弟1", "弟", "表弟1");
            relationMale.put("表弟1", "姐", "表妹1,表妹1");
            relationMale.put("表弟1", "妹", "表妹1");
            //
            relationMale.put("表哥1", "父", "舅舅");
            relationMale.put("表哥1", "母", "舅妈");
            relationMale.put("表哥1", "兄", "表哥1");
            relationMale.put("表哥1", "弟", "表弟1,表哥1");
            relationMale.put("表哥1", "姐", "表姐1");
            relationMale.put("表哥1", "妹", "表妹1,表姐1");

            //
            relationMale.put("表妹1", "父", "舅舅");
            relationMale.put("表妹1", "母", "舅妈");
            relationMale.put("表妹1", "兄", "表弟1,表哥1");
            relationMale.put("表妹1", "弟", "表弟1");
            relationMale.put("表妹1", "姐", "表姐1,表妹1");
            relationMale.put("表妹1", "妹", "表妹1");
            //
            relationMale.put("表姐1", "父", "舅舅");
            relationMale.put("表姐1", "母", "舅妈");
            relationMale.put("表姐1", "兄", "表哥1");
            relationMale.put("表姐1", "弟", "表弟1,表哥1");
            relationMale.put("表姐1", "姐", "表姐1");
            relationMale.put("表姐1", "妹", "表妹1,表姐1");
            //
            relationMale.put("表弟2", "父", "姨丈");
            relationMale.put("表弟2", "母", "姨妈");
            relationMale.put("表弟2", "兄", "表弟2,表哥2");
            relationMale.put("表弟2", "弟", "表弟2");
            relationMale.put("表弟2", "姐", "表妹2,表妹2");
            relationMale.put("表弟2", "妹", "表妹2");
            //
            relationMale.put("表哥2", "父", "姨丈");
            relationMale.put("表哥2", "母", "姨妈");
            relationMale.put("表哥2", "兄", "表哥2");
            relationMale.put("表哥2", "弟", "表弟2,表哥2");
            relationMale.put("表哥2", "姐", "表姐2");
            relationMale.put("表哥2", "妹", "表妹2,表姐2");

            //
            relationMale.put("表妹2", "父", "姨丈");
            relationMale.put("表妹2", "母", "姨妈");
            relationMale.put("表妹2", "兄", "表弟2,表哥2");
            relationMale.put("表妹2", "弟", "表弟2");
            relationMale.put("表妹2", "姐", "表姐2,表妹2");
            relationMale.put("表妹2", "妹", "表妹2");
            //
            relationMale.put("表姐2", "父", "姨丈");
            relationMale.put("表姐2", "母", "姨妈");
            relationMale.put("表姐2", "兄", "表哥2");
            relationMale.put("表姐2", "弟", "表弟2,表哥2");
            relationMale.put("表姐2", "姐", "表姐2");
            relationMale.put("表姐2", "妹", "表妹2,表姐2");
            //
            relationMale.put("表弟3", "父", "姑丈");
            relationMale.put("表弟3", "母", "姑妈");
            relationMale.put("表弟3", "兄", "表弟3,表哥3");
            relationMale.put("表弟3", "弟", "表弟3");
            relationMale.put("表弟3", "姐", "表妹3,表妹3");
            relationMale.put("表弟3", "妹", "表妹3");
            //
            relationMale.put("表哥3", "父", "姑丈");
            relationMale.put("表哥3", "母", "姑妈");
            relationMale.put("表哥3", "兄", "表哥3");
            relationMale.put("表哥3", "弟", "表弟3,表哥3");
            relationMale.put("表哥3", "姐", "表姐3");
            relationMale.put("表哥3", "妹", "表妹3,表姐3");

            //
            relationMale.put("表妹3", "父", "姑丈");
            relationMale.put("表妹3", "母", "姑妈");
            relationMale.put("表妹3", "兄", "表弟3,表哥3");
            relationMale.put("表妹3", "弟", "表弟3");
            relationMale.put("表妹3", "姐", "表姐3,表妹3");
            relationMale.put("表妹3", "妹", "表妹3");
            //
            relationMale.put("表姐3", "父", "姑丈");
            relationMale.put("表姐3", "母", "姑妈");
            relationMale.put("表姐3", "兄", "表哥3");
            relationMale.put("表姐3", "弟", "表弟3,表哥3");
            relationMale.put("表姐3", "姐", "表姐3");
            relationMale.put("表姐3", "妹", "表妹3,表姐3");
            relationMale.put("堂哥1", "父", "叔父");
            relationMale.put("堂哥1", "母", "婶婶");
            relationMale.put("堂哥1", "兄", "堂哥1");
            relationMale.put("堂哥1", "弟", "堂哥1,堂弟1");
            relationMale.put("堂哥1", "姐", "堂姐1");
            relationMale.put("堂哥1", "妹", "堂妹1,堂姐1");
            //
            relationMale.put("堂弟1", "父", "叔父");
            relationMale.put("堂弟1", "母", "婶婶");
            relationMale.put("堂弟1", "兄", "堂弟1,堂哥1");
            relationMale.put("堂弟1", "弟", "堂弟1");
            relationMale.put("堂弟1", "姐", "堂姐1,堂妹1");
            relationMale.put("堂弟1", "妹", "堂妹1");
            //
            relationMale.put("堂哥2", "父", "伯父");
            relationMale.put("堂哥2", "母", "伯母");
            relationMale.put("堂哥2", "兄", "堂哥2");
            relationMale.put("堂哥2", "弟", "堂哥2,堂弟2");
            relationMale.put("堂哥2", "姐", "堂姐2");
            relationMale.put("堂哥2", "妹", "堂妹2,堂姐2");
            //
            relationMale.put("堂弟2", "父", "伯父");
            relationMale.put("堂弟2", "母", "伯母");
            relationMale.put("堂弟2", "兄", "堂弟2,堂哥2");
            relationMale.put("堂弟2", "弟", "堂弟2");
            relationMale.put("堂弟2", "姐", "堂姐2,堂妹2");
            relationMale.put("堂弟2", "妹", "堂妹2");
            //
            relationMale.put("堂姐1", "父", "叔父");
            relationMale.put("堂姐1", "母", "婶婶");
            relationMale.put("堂姐1", "兄", "堂哥1");
            relationMale.put("堂姐1", "弟", "堂哥1,堂弟1");
            relationMale.put("堂姐1", "姐", "堂姐1");
            relationMale.put("堂姐1", "妹", "堂妹1,堂姐1");
            //
            relationMale.put("堂妹1", "父", "叔父");
            relationMale.put("堂妹1", "母", "婶婶");
            relationMale.put("堂妹1", "兄", "堂弟1,堂哥1");
            relationMale.put("堂妹1", "弟", "堂弟1");
            relationMale.put("堂妹1", "姐", "堂姐1,堂妹1");
            relationMale.put("堂妹1", "妹", "堂妹1");
            //
            relationMale.put("堂姐2", "父", "伯父");
            relationMale.put("堂姐2", "母", "伯母");
            relationMale.put("堂姐2", "兄", "堂哥2");
            relationMale.put("堂姐2", "弟", "堂哥2,堂弟2");
            relationMale.put("堂姐2", "姐", "堂姐2");
            relationMale.put("堂姐2", "妹", "堂妹2,堂姐2");
            //
            relationMale.put("堂妹2", "父", "伯父");
            relationMale.put("堂妹2", "母", "伯母");
            relationMale.put("堂妹2", "兄", "堂弟2,堂哥2");
            relationMale.put("堂妹2", "弟", "堂弟2");
            relationMale.put("堂妹2", "姐", "堂姐2,堂妹2");
            relationMale.put("堂妹2", "妹", "堂妹2");

            relationFemale.put("自己", "父", "爸爸");
            relationFemale.put("自己", "母", "妈妈");
            relationFemale.put("自己", "兄", "哥哥");
            relationFemale.put("自己", "弟", "弟弟");
            relationFemale.put("自己", "姐", "姐姐");
            relationFemale.put("自己", "妹", "妹妹");
            relationFemale.put("自己", "儿", "儿子");
            relationFemale.put("自己", "女", "女儿");
            relationFemale.put("自己", "夫", "丈夫");
            //
            relationFemale.put("爸爸", "父", "祖父");
            relationFemale.put("爸爸", "母", "祖母");
            relationFemale.put("爸爸", "兄", "伯父");
            relationFemale.put("爸爸", "弟", "叔父");
            relationFemale.put("爸爸", "姐", "姑妈");
            relationFemale.put("爸爸", "妹", "姑妈");
            relationFemale.put("爸爸", "儿", "哥哥,弟弟"); //
            relationFemale.put("爸爸", "女", "自己,姐姐,妹妹");
            relationFemale.put("爸爸", "妻", "妈妈");
            //
            relationFemale.put("妈妈", "父", "外祖父");
            relationFemale.put("妈妈", "母", "外祖母");
            relationFemale.put("妈妈", "兄", "舅舅");
            relationFemale.put("妈妈", "弟", "舅舅");
            relationFemale.put("妈妈", "姐", "姨妈");
            relationFemale.put("妈妈", "妹", "姨妈");
            relationFemale.put("妈妈", "儿", "哥哥,弟弟");
            relationFemale.put("妈妈", "女", "自己,姐姐,妹妹");
            relationFemale.put("妈妈", "夫", "爸爸");
            //
            relationFemale.put("哥哥", "父", "爸爸");
            relationFemale.put("哥哥", "母", "妈妈");
            relationFemale.put("哥哥", "兄", "哥哥");
            relationFemale.put("哥哥", "弟", "哥哥,弟弟");
            relationFemale.put("哥哥", "姐", "姐姐");
            relationFemale.put("哥哥", "妹", "自己,姐姐,妹妹");
            relationFemale.put("哥哥", "儿", "侄子2");
            relationFemale.put("哥哥", "女", "侄女2");
            relationFemale.put("哥哥", "妻", "嫂嫂");
            //
            relationFemale.put("弟弟", "父", "爸爸");
            relationFemale.put("弟弟", "母", "妈妈");
            relationFemale.put("弟弟", "兄", "哥哥,弟弟");
            relationFemale.put("弟弟", "弟", "弟弟");
            relationFemale.put("弟弟", "姐", "自己,姐姐,妹妹");
            relationFemale.put("弟弟", "妹", "妹妹");
            relationFemale.put("弟弟", "儿", "侄子1");
            relationFemale.put("弟弟", "女", "侄女1");
            relationFemale.put("弟弟", "妻", "弟媳");
            //
            relationFemale.put("姐姐", "父", "爸爸");
            relationFemale.put("姐姐", "母", "妈妈");
            relationFemale.put("姐姐", "兄", "哥哥");
            relationFemale.put("姐姐", "弟", "哥哥,弟弟");
            relationFemale.put("姐姐", "姐", "姐姐");
            relationFemale.put("姐姐", "妹", "自己,姐姐,妹妹");
            relationFemale.put("姐姐", "儿", "外甥2");
            relationFemale.put("姐姐", "女", "外甥女2");
            relationFemale.put("姐姐", "夫", "姐夫");
            //
            relationFemale.put("妹妹", "父", "爸爸");
            relationFemale.put("妹妹", "母", "妈妈");
            relationFemale.put("妹妹", "兄", "哥哥,弟弟");
            relationFemale.put("妹妹", "弟", "弟弟");
            relationFemale.put("妹妹", "姐", "自己,姐姐,妹妹");
            relationFemale.put("妹妹", "妹", "妹妹");
            relationFemale.put("妹妹", "儿", "外甥1");
            relationFemale.put("妹妹", "女", "外甥女1");
            relationFemale.put("妹妹", "夫", "妹婿");
            //
            relationFemale.put("女儿", "父", "丈夫");
            relationFemale.put("女儿", "母", "自己");
            relationFemale.put("女儿", "兄", "儿子");
            relationFemale.put("女儿", "弟", "儿子");
            relationFemale.put("女儿", "姐", "女儿");
            relationFemale.put("女儿", "妹", "女儿");
            relationFemale.put("女儿", "儿", "外孙");
            relationFemale.put("女儿", "女", "外孙女");
            relationFemale.put("女儿", "夫", "女婿");
            //
            relationFemale.put("儿子", "父", "丈夫");
            relationFemale.put("儿子", "母", "自己");
            relationFemale.put("儿子", "兄", "儿子");
            relationFemale.put("儿子", "弟", "儿子");
            relationFemale.put("儿子", "姐", "女儿");
            relationFemale.put("儿子", "妹", "女儿");
            relationFemale.put("儿子", "儿", "孙子");
            relationFemale.put("儿子", "女", "孙女");
            relationFemale.put("儿子", "妻", "儿媳");
            //
            relationFemale.put("丈夫", "父", "爸爸");
            relationFemale.put("丈夫", "母", "妈妈");
            relationFemale.put("丈夫", "兄", "哥哥");
            relationFemale.put("丈夫", "弟", "弟弟");
            relationFemale.put("丈夫", "姐", "姐姐");
            relationFemale.put("丈夫", "妹", "妹妹");
            relationFemale.put("丈夫", "儿", "儿子");
            relationFemale.put("丈夫", "女", "女儿");
            relationFemale.put("丈夫", "妻", "自己");
            //
            relationFemale.put("伯父", "父", "祖父");
            relationFemale.put("伯父", "母", "祖母");
            relationFemale.put("伯父", "兄", "伯父");
            relationFemale.put("伯父", "弟", "叔父,伯父,爸爸");//?
            relationFemale.put("伯父", "姐", "姑妈");
            relationFemale.put("伯父", "妹", "姑妈");
            relationFemale.put("伯父", "儿", "堂弟2,堂哥2");
            relationFemale.put("伯父", "女", "堂妹2,堂姐2");
            relationFemale.put("伯父", "妻", "伯母");
            //
            relationFemale.put("叔父", "父", "祖父");
            relationFemale.put("叔父", "母", "祖母");
            relationFemale.put("叔父", "兄", "伯父,叔父,爸爸");//?
            relationFemale.put("叔父", "弟", "叔父");
            relationFemale.put("叔父", "姐", "姑妈");
            relationFemale.put("叔父", "妹", "姑妈");
            relationFemale.put("叔父", "儿", "堂弟1,堂哥1");
            relationFemale.put("叔父", "女", "堂妹1,堂姐1");
            relationFemale.put("叔父", "妻", "婶婶");
            //
            relationFemale.put("姑妈", "父", "祖父");
            relationFemale.put("姑妈", "母", "祖母");
            relationFemale.put("姑妈", "兄", "伯父,叔父,爸爸");//?
            relationFemale.put("姑妈", "弟", "叔父,伯父,爸爸");//?
            relationFemale.put("姑妈", "姐", "姑母");
            relationFemale.put("姑妈", "妹", "姑母");
            relationFemale.put("姑妈", "儿", "表哥3,表弟3");
            relationFemale.put("姑妈", "女", "表姐3,表妹3");
            relationFemale.put("姑妈", "夫", "姑丈");
            //
            relationFemale.put("姑丈", "儿", "表哥3,表弟3");
            relationFemale.put("姑丈", "女", "表姐3,表妹3");
            relationFemale.put("姑丈", "妻", "姑妈");
            //
            relationFemale.put("伯母", "儿", "堂弟2,堂哥2");
            relationFemale.put("伯母", "女", "堂妹2,堂姐2");
            relationFemale.put("伯母", "夫", "伯父");
            //
            relationFemale.put("婶婶", "儿", "堂弟1,堂哥1");
            relationFemale.put("婶婶", "女", "堂妹1,堂姐1");
            relationFemale.put("婶婶", "夫", "叔父");
            //
            relationFemale.put("祖父", "儿", "伯父,叔父,爸爸");//?
            relationFemale.put("祖父", "女", "姑妈");
            relationFemale.put("祖父", "妻", "祖母");
            //
            relationFemale.put("祖母", "儿", "伯父,叔父,爸爸");//?
            relationFemale.put("祖母", "女", "姑妈");
            relationFemale.put("祖母", "夫", "祖父");
            //
            relationFemale.put("舅舅", "父", "外祖父");
            relationFemale.put("舅舅", "母", "外祖母");
            relationFemale.put("舅舅", "兄", "舅舅");
            relationFemale.put("舅舅", "弟", "舅舅");
            relationFemale.put("舅舅", "姐", "姨妈");
            relationFemale.put("舅舅", "妹", "姨妈");
            relationFemale.put("舅舅", "儿", "表哥1,表弟1");
            relationFemale.put("舅舅", "女", "表姐1,表妹1");
            relationFemale.put("舅舅", "妻", "舅妈");
            //
            relationFemale.put("舅妈", "儿", "表哥1,表弟1");
            relationFemale.put("舅妈", "女", "表姐1,表妹1");
            relationFemale.put("舅妈", "夫", "舅舅");
            //
            relationFemale.put("姨妈", "父", "外祖父");
            relationFemale.put("姨妈", "母", "外祖母");
            relationFemale.put("姨妈", "兄", "舅舅");
            relationFemale.put("姨妈", "弟", "舅舅");
            relationFemale.put("姨妈", "姐", "姨妈,妈妈");
            relationFemale.put("姨妈", "妹", "姨妈,妈妈");
            relationFemale.put("姨妈", "儿", "表兄弟2");
            relationFemale.put("姨妈", "女", "表姐妹2");
            relationFemale.put("姨妈", "夫", "姨丈");
            //
            relationFemale.put("姨丈", "儿", "表哥2,表弟2");
            relationFemale.put("姨丈", "女", "表姐2,表妹2");
            relationFemale.put("姨丈", "妻", "姨妈");
            //
            relationFemale.put("外祖父", "儿", "舅舅");
            relationFemale.put("外祖父", "女", "姨妈,妈妈");
            relationFemale.put("外祖父", "妻", "外祖母");
            //
            relationFemale.put("外祖母", "儿", "舅舅");
            relationFemale.put("外祖母", "女", "姨妈,妈妈");
            relationFemale.put("外祖母", "夫", "外祖父");
            //
            relationFemale.put("女婿", "儿", "外孙");
            relationFemale.put("女婿", "女", "外孙女");
            relationFemale.put("女婿", "妻", "女儿");
            //
            relationFemale.put("妹婿", "儿", "外甥1");
            relationFemale.put("妹婿", "女", "外甥女1");
            relationFemale.put("妹婿", "妻", "妹妹");
            //
            relationFemale.put("儿媳", "儿", "孙子");
            relationFemale.put("儿媳", "女", "孙女");
            relationFemale.put("儿媳", "夫", "儿子");
            //
            relationFemale.put("弟媳", "儿", "侄子1");
            relationFemale.put("弟媳", "女", "侄女1");
            relationFemale.put("弟媳", "夫", "弟弟");
            //
            relationFemale.put("嫂嫂", "儿", "侄子2");
            relationFemale.put("嫂嫂", "女", "侄女2");
            relationFemale.put("嫂嫂", "夫", "哥哥");
            //
            relationFemale.put("姐夫", "儿", "外甥2");
            relationFemale.put("姐夫", "女", "外甥女2");
            relationFemale.put("姐夫", "妻", "姐姐");
            //
            relationFemale.put("侄子1", "父", "弟弟");
            relationFemale.put("侄子1", "母", "弟媳");
            relationFemale.put("侄子1", "兄", "侄子1");
            relationFemale.put("侄子1", "弟", "侄子1");
            relationFemale.put("侄子1", "姐", "侄女1");
            relationFemale.put("侄子1", "妹", "侄女1");
            //
            relationFemale.put("侄子2", "父", "哥哥");
            relationFemale.put("侄子2", "母", "嫂嫂");
            relationFemale.put("侄子2", "兄", "侄子2");
            relationFemale.put("侄子2", "弟", "侄子2");
            relationFemale.put("侄子2", "姐", "侄女2");
            relationFemale.put("侄子2", "妹", "侄女2");
            //
            relationFemale.put("侄女1", "父", "弟弟");
            relationFemale.put("侄女1", "母", "弟媳");
            relationFemale.put("侄女1", "兄", "侄子1");
            relationFemale.put("侄女1", "弟", "侄子1");
            relationFemale.put("侄女1", "姐", "侄女1");
            relationFemale.put("侄女1", "妹", "侄女1");
            //
            relationFemale.put("侄女2", "父", "哥哥");
            relationFemale.put("侄女2", "母", "嫂嫂");
            relationFemale.put("侄女2", "兄", "侄子2");
            relationFemale.put("侄女2", "弟", "侄子2");
            relationFemale.put("侄女2", "姐", "侄女2");
            relationFemale.put("侄女2", "妹", "侄女2");
            //
            relationFemale.put("外甥女1", "父", "妹婿");
            relationFemale.put("外甥女1", "母", "妹妹");
            relationFemale.put("外甥女1", "兄", "外甥1");
            relationFemale.put("外甥女1", "弟", "外甥1");
            relationFemale.put("外甥女1", "姐", "外甥女1");
            relationFemale.put("外甥女1", "妹", "外甥女1");
            //
            relationFemale.put("外甥1", "父", "妹婿");
            relationFemale.put("外甥1", "母", "妹妹");
            relationFemale.put("外甥1", "兄", "外甥1");
            relationFemale.put("外甥1", "弟", "外甥1");
            relationFemale.put("外甥1", "姐", "外甥女1");
            relationFemale.put("外甥1", "妹", "外甥女1");
            //
            relationFemale.put("外甥女2", "父", "姐夫");
            relationFemale.put("外甥女2", "母", "姐姐");
            relationFemale.put("外甥女2", "兄", "外甥2");
            relationFemale.put("外甥女2", "弟", "外甥2");
            relationFemale.put("外甥女2", "姐", "外甥女2");
            relationFemale.put("外甥女2", "妹", "外甥女2");
            //
            relationFemale.put("外甥2", "父", "姐夫");
            relationFemale.put("外甥2", "母", "姐姐");
            relationFemale.put("外甥2", "兄", "外甥2");
            relationFemale.put("外甥2", "弟", "外甥2");
            relationFemale.put("外甥2", "姐", "外甥女2");
            relationFemale.put("外甥2", "妹", "外甥女2");

            //
            relationFemale.put("孙子", "父", "儿子");
            relationFemale.put("孙子", "母", "儿媳");
            relationFemale.put("孙子", "兄", "孙子");
            relationFemale.put("孙子", "弟", "孙子");
            relationFemale.put("孙子", "姐", "孙女");
            relationFemale.put("孙子", "妹", "孙女");
            //
            relationFemale.put("孙女", "父", "儿子");
            relationFemale.put("孙女", "母", "儿媳");
            relationFemale.put("孙女", "兄", "孙子");
            relationFemale.put("孙女", "弟", "孙子");
            relationFemale.put("孙女", "姐", "孙女");
            relationFemale.put("孙女", "妹", "孙女");
            //
            relationFemale.put("外孙", "父", "女婿");
            relationFemale.put("外孙", "母", "女儿");
            relationFemale.put("外孙", "兄", "外孙");
            relationFemale.put("外孙", "弟", "外孙");
            relationFemale.put("外孙", "姐", "外孙女");
            relationFemale.put("外孙", "妹", "外孙女");
            //
            relationFemale.put("外孙女", "父", "女婿");
            relationFemale.put("外孙女", "母", "女儿");
            relationFemale.put("外孙女", "兄", "外孙");
            relationFemale.put("外孙女", "弟", "外孙");
            relationFemale.put("外孙女", "姐", "外孙女");
            relationFemale.put("外孙女", "妹", "外孙女");
            //
            relationFemale.put("表弟1", "父", "舅舅");
            relationFemale.put("表弟1", "母", "舅妈");
            relationFemale.put("表弟1", "兄", "表弟1,表哥1");
            relationFemale.put("表弟1", "弟", "表弟1");
            relationFemale.put("表弟1", "姐", "表妹1,表妹1");
            relationFemale.put("表弟1", "妹", "表妹1");
            //
            relationFemale.put("表哥1", "父", "舅舅");
            relationFemale.put("表哥1", "母", "舅妈");
            relationFemale.put("表哥1", "兄", "表哥1");
            relationFemale.put("表哥1", "弟", "表弟1,表哥1");
            relationFemale.put("表哥1", "姐", "表姐1");
            relationFemale.put("表哥1", "妹", "表妹1,表姐1");

            //
            relationFemale.put("表妹1", "父", "舅舅");
            relationFemale.put("表妹1", "母", "舅妈");
            relationFemale.put("表妹1", "兄", "表弟1,表哥1");
            relationFemale.put("表妹1", "弟", "表弟1");
            relationFemale.put("表妹1", "姐", "表姐1,表妹1");
            relationFemale.put("表妹1", "妹", "表妹1");
            //
            relationFemale.put("表姐1", "父", "舅舅");
            relationFemale.put("表姐1", "母", "舅妈");
            relationFemale.put("表姐1", "兄", "表哥1");
            relationFemale.put("表姐1", "弟", "表弟1,表哥1");
            relationFemale.put("表姐1", "姐", "表姐1");
            relationFemale.put("表姐1", "妹", "表妹1,表姐1");
            //
            relationFemale.put("表弟2", "父", "姨丈");
            relationFemale.put("表弟2", "母", "姨妈");
            relationFemale.put("表弟2", "兄", "表弟2,表哥2");
            relationFemale.put("表弟2", "弟", "表弟2");
            relationFemale.put("表弟2", "姐", "表妹2,表妹2");
            relationFemale.put("表弟2", "妹", "表妹2");
            //
            relationFemale.put("表哥2", "父", "姨丈");
            relationFemale.put("表哥2", "母", "姨妈");
            relationFemale.put("表哥2", "兄", "表哥2");
            relationFemale.put("表哥2", "弟", "表弟2,表哥2");
            relationFemale.put("表哥2", "姐", "表姐2");
            relationFemale.put("表哥2", "妹", "表妹2,表姐2");

            //
            relationFemale.put("表妹2", "父", "姨丈");
            relationFemale.put("表妹2", "母", "姨妈");
            relationFemale.put("表妹2", "兄", "表弟2,表哥2");
            relationFemale.put("表妹2", "弟", "表弟2");
            relationFemale.put("表妹2", "姐", "表姐2,表妹2");
            relationFemale.put("表妹2", "妹", "表妹2");
            //
            relationFemale.put("表姐2", "父", "姨丈");
            relationFemale.put("表姐2", "母", "姨妈");
            relationFemale.put("表姐2", "兄", "表哥2");
            relationFemale.put("表姐2", "弟", "表弟2,表哥2");
            relationFemale.put("表姐2", "姐", "表姐2");
            relationFemale.put("表姐2", "妹", "表妹2,表姐2");
            //
            relationFemale.put("表弟3", "父", "姑丈");
            relationFemale.put("表弟3", "母", "姑妈");
            relationFemale.put("表弟3", "兄", "表弟3,表哥3");
            relationFemale.put("表弟3", "弟", "表弟3");
            relationFemale.put("表弟3", "姐", "表妹3,表妹3");
            relationFemale.put("表弟3", "妹", "表妹3");
            //
            relationFemale.put("表哥3", "父", "姑丈");
            relationFemale.put("表哥3", "母", "姑妈");
            relationFemale.put("表哥3", "兄", "表哥3");
            relationFemale.put("表哥3", "弟", "表弟3,表哥3");
            relationFemale.put("表哥3", "姐", "表姐3");
            relationFemale.put("表哥3", "妹", "表妹3,表姐3");

            //
            relationFemale.put("表妹3", "父", "姑丈");
            relationFemale.put("表妹3", "母", "姑妈");
            relationFemale.put("表妹3", "兄", "表弟3,表哥3");
            relationFemale.put("表妹3", "弟", "表弟3");
            relationFemale.put("表妹3", "姐", "表姐3,表妹3");
            relationFemale.put("表妹3", "妹", "表妹3");
            //
            relationFemale.put("表姐3", "父", "姑丈");
            relationFemale.put("表姐3", "母", "姑妈");
            relationFemale.put("表姐3", "兄", "表哥3");
            relationFemale.put("表姐3", "弟", "表弟3,表哥3");
            relationFemale.put("表姐3", "姐", "表姐3");
            relationFemale.put("表姐3", "妹", "表妹3,表姐3");
            //
            relationFemale.put("堂哥1", "父", "叔父");
            relationFemale.put("堂哥1", "母", "婶婶");
            relationFemale.put("堂哥1", "兄", "堂哥1");
            relationFemale.put("堂哥1", "弟", "堂哥1,堂弟1");
            relationFemale.put("堂哥1", "姐", "堂姐1");
            relationFemale.put("堂哥1", "妹", "堂妹1,堂姐1");
            //
            relationFemale.put("堂弟1", "父", "叔父");
            relationFemale.put("堂弟1", "母", "婶婶");
            relationFemale.put("堂弟1", "兄", "堂弟1,堂哥1");
            relationFemale.put("堂弟1", "弟", "堂弟1");
            relationFemale.put("堂弟1", "姐", "堂姐1,堂妹1");
            relationFemale.put("堂弟1", "妹", "堂妹1");
            //
            relationFemale.put("堂哥2", "父", "伯父");
            relationFemale.put("堂哥2", "母", "伯母");
            relationFemale.put("堂哥2", "兄", "堂哥2");
            relationFemale.put("堂哥2", "弟", "堂哥2,堂弟2");
            relationFemale.put("堂哥2", "姐", "堂姐2");
            relationFemale.put("堂哥2", "妹", "堂妹2,堂姐2");
            //
            relationFemale.put("堂弟2", "父", "伯父");
            relationFemale.put("堂弟2", "母", "伯母");
            relationFemale.put("堂弟2", "兄", "堂弟2,堂哥2");
            relationFemale.put("堂弟2", "弟", "堂弟2");
            relationFemale.put("堂弟2", "姐", "堂姐2,堂妹2");
            relationFemale.put("堂弟2", "妹", "堂妹2");
            //
            relationFemale.put("堂姐1", "父", "叔父");
            relationFemale.put("堂姐1", "母", "婶婶");
            relationFemale.put("堂姐1", "兄", "堂哥1");
            relationFemale.put("堂姐1", "弟", "堂哥1,堂弟1");
            relationFemale.put("堂姐1", "姐", "堂姐1");
            relationFemale.put("堂姐1", "妹", "堂妹1,堂姐1");
            //
            relationFemale.put("堂妹1", "父", "叔父");
            relationFemale.put("堂妹1", "母", "婶婶");
            relationFemale.put("堂妹1", "兄", "堂弟1,堂哥1");
            relationFemale.put("堂妹1", "弟", "堂弟1");
            relationFemale.put("堂妹1", "姐", "堂姐1,堂妹1");
            relationFemale.put("堂妹1", "妹", "堂妹1");
            //
            relationFemale.put("堂姐2", "父", "伯父");
            relationFemale.put("堂姐2", "母", "伯母");
            relationFemale.put("堂姐2", "兄", "堂哥2");
            relationFemale.put("堂姐2", "弟", "堂哥2,堂弟2");
            relationFemale.put("堂姐2", "姐", "堂姐2");
            relationFemale.put("堂姐2", "妹", "堂妹2,堂姐2");
            //
            relationFemale.put("堂妹2", "父", "伯父");
            relationFemale.put("堂妹2", "母", "伯母");
            relationFemale.put("堂妹2", "兄", "堂弟2,堂哥2");
            relationFemale.put("堂妹2", "弟", "堂弟2");
            relationFemale.put("堂妹2", "姐", "堂姐2,堂妹2");
            relationFemale.put("堂妹2", "妹", "堂妹2");

        }
    public static String find(String expr, boolean isMale){
        String[] e = expr.split("的");
        List<String> el = new ArrayList();
        for(int i = 1; i < e.length; ++i){
            el.add(e[i]);
        }
        e = new String[el.size()];
        int j = 0;
        for(String i : el){
            e[j++] = i;
        }
        if(isMale){
            return Utils.join(",",find(new String[]{"自己"}, e, 0, relationMale));
        } else {
            return Utils.join(",",find(new String[]{"自己"}, e, 0, relationFemale));
        }

    }
    private static String[] find(String[] cs, String[] n, int i, Dict2D<String, String, String> relation){
        List<String> cs2 = new ArrayList<>();
        //final List<String> cs3 = new ArrayList<>();
        if(i == n.length){
            return cs;
        }
        for(String c : cs) {
            if (relation.get(c, n[i]) == null) {
                return new String[]{"超出范围"};
                /*System.out.println("超出范围");*/
            }
            String[] s = relation.get(c, n[i]).split(",");
            System.out.println(s);
            for (String j : s) {
                //cs3.add(findtitle(j));

                cs2.add(j);
                //System.out.println(cs2);
            }

        }


        return find((String[]) cs2.toArray(new String[cs2.size()]), n, ++i, relation);
    }

    public static String findpath(String title)
    {
        String path = "";
        switch (title)
        {
            case "爷爷":
            case "祖父":path = "爸爸的爸爸";break;
            case "奶奶":
            case "祖母":path = "爸爸的妈妈";break;
            case "姑妈":path = "爸爸的姐妹";break;
            case "姑丈":path = "爸爸的姐妹的丈夫";break;
            case "伯父":path = "爸爸的哥哥";break;
            case "伯母":path = "爸爸的哥哥的妻子";break;
            case "叔父":path = "爸爸的弟弟";break;
            case "婶婶":path = "爸爸的弟弟的妻子";break;
            case "表哥":path = "爸爸的姐妹的儿子,妈妈的姐妹的儿子,妈妈的兄弟的儿子";break;
            case "表弟":path = "爸爸的姐妹的儿子,妈妈的姐妹的儿子,妈妈的兄弟的儿子";break;
            case "表姐":path = "爸爸的姐妹的女儿,妈妈的姐妹的女儿,妈妈的兄弟的女儿";break;
            case "表妹":path = "爸爸的姐妹的女儿,妈妈的姐妹的女儿,妈妈的兄弟的女儿";break;
            case "哥哥":path = "哥哥";break;
            case "弟弟":path = "弟弟";break;
            case "嫂嫂":path = "哥哥的妻子";break;
            case "弟媳":path = "弟弟的儿子";break;
            case "姐姐":path = "姐姐";break;
            case "妹妹":path = "妹妹";break;
            case "姐夫":path = "姐姐的丈夫";break;
            case "妹婿":path = "妹妹的丈夫";break;
            case "侄子":path = "哥哥的儿子,弟弟的儿子";break;
            case "侄女":path = "哥哥的女儿,弟弟的女儿";break;
            case "外甥":path = "姐姐的儿子,妹妹的儿子";break;
            case "外甥女":path = "姐姐的女儿,妹妹的女儿";break;
            case "儿子":path = "儿子";break;
            case "女儿":path = "女儿";break;
            case "儿媳":path = "儿子的妻子";break;
            case "女婿":path = "女儿的丈夫";break;
            case "孙子":path = "儿子的儿子";break;
            case "孙女":path = "儿子的女儿";break;
            case "外孙":path = "女儿的儿子";break;
            case "外孙女":path = "女儿的女儿";break;
            case"舅舅":path="妈妈的兄弟";break;
            case"舅妈":path="妈妈的兄弟的妻子";break;
            case"姨妈":path="妈妈的姐妹";break;
            case"姨丈":path="妈妈的姐妹的丈夫";break;
            case"外祖父":path="妈妈的爸爸";break;
            case"外祖母":path="妈妈的妈妈";break;
            case"堂哥":path="爸爸的兄弟的儿子";break;
            case"堂弟":path="爸爸的兄弟的儿子";break;
            case"堂姐":path="爸爸的兄弟的女儿";break;
            case"堂妹":path="爸爸的兄弟的女儿";break;
            case"妻子":path="妻子";break;
            case"丈夫":path="丈夫";break;


        }
        return path;
    }
    public static String findtitle(String title)
    {
        String path = "";
        switch (title)
        {
            case "祖父":if (sex())path = "孙子";else path="孙女";break;
            case "祖母":if (sex())path = "孙子";else path="孙女";break;
            case "姑妈":if (sex())path = "侄子";else path="侄女";break;
            case "姑丈":if (sex())path = "侄子";else path="侄女";break;
            case "伯父":if (sex())path = "侄子";else path="侄女";break;
            case "伯母":if (sex())path = "侄子";else path="侄女";break;
            case "叔父":if (sex())path = "侄子";else path="侄女";break;
            case "婶婶":if (sex())path = "侄子";else path="侄女";break;
            case "表哥1":if (sex())path = "表弟";else path="表妹";break;
            case "表弟1":if (sex())path = "表哥";else path="表姐";break;
            case "表姐1":if (sex())path = "表弟";else path="表妹";break;
            case "表妹1":if (sex())path = "表哥";else path="表姐";break;
            case "表哥2":if (sex())path = "表弟";else path="表妹";break;
            case "表弟2":if (sex())path = "表哥";else path="表姐";break;
            case "表姐2":if (sex())path = "表弟";else path="表妹";break;
            case "表妹2":if (sex())path = "表哥";else path="表姐";break;
            case "表哥3":if (sex())path = "表弟";else path="表妹";break;
            case "表弟3":if (sex())path = "表哥";else path="表姐";break;
            case "表姐3":if (sex())path = "表弟";else path="表妹";break;
            case "表妹3":if (sex())path = "表哥";else path="表姐";break;
            case "哥哥":if (sex())path = "弟弟";else path="妹妹";break;
            case "弟弟":if (sex())path = "哥哥";else path="姐姐";break;
            case "嫂嫂":if (sex())path = "弟弟";else path="妹妹";break;
            case "弟媳":if (sex())path = "哥哥";else path="姐姐";break;
            case "姐姐":if (sex())path = "弟弟";else path="妹妹";break;
            case "妹妹":if (sex())path = "哥哥";else path="姐姐";break;
            case "姐夫":if (sex())path = "弟弟";else path="妹妹";break;
            case "妹婿":if (sex())path = "哥哥";else path="姐姐";break;
            case "侄子1":if (sex())path = "叔父";else path="婶婶";break;
            case "侄子2":if (sex())path = "伯父";else path="伯母";break;
            case "侄女1":if (sex())path = "叔父";else path="婶婶";break;
            case "侄女2":if (sex())path = "伯父";else path="伯母";break;
            case "外甥1":if (sex())path = "舅舅";else path="姨妈";break;
            case "外甥女1":if (sex())path = "舅舅";else path="姨妈";break;
            case "外甥2":if (sex())path = "舅舅";else path="姨妈";break;
            case "外甥女2":if (sex())path = "舅舅";else path="姨妈";break;
            case "儿子":if (sex())path = "爸爸";else path="妈妈";break;
            case "女儿":if (sex())path = "爸爸";else path="妈妈";break;
            case "儿媳":if (sex())path = "爸爸";else path="妈妈";break;
            case "女婿":if (sex())path = "爸爸";else path="妈妈";break;
            case "孙子":if (sex())path = "祖父";else path="祖母";break;
            case "孙女":if (sex())path = "祖父";else path="祖母";break;
            case "外孙":if (sex())path = "外祖父";else path="外祖母";break;
            case "外孙女":if (sex())path = "外祖父";else path="外祖母";break;
            case"舅舅":if (sex())path = "外甥";else path="外甥女";break;
            case"舅妈":if (sex())path = "外甥";else path="外甥女";break;
            case"姨妈":if (sex())path = "外甥";else path="外甥女";break;
            case"姨丈":if (sex())path = "外甥";else path="外甥女";break;
            case"外祖父":if (sex())path = "外孙";else path="外孙女";break;
            case"外祖母":if (sex())path = "外孙";else path="外孙女";break;
            case"堂哥1":if (sex())path = "堂弟";else path="堂妹";break;
            case"堂弟1":if (sex())path = "堂哥";else path="堂姐";break;
            case"堂姐1":if (sex())path = "堂弟";else path="堂妹";break;
            case"堂妹1":if (sex())path = "堂哥";else path="堂姐";break;
            case"堂哥2":if (sex())path = "堂弟";else path="堂妹";break;
            case"堂弟2":if (sex())path = "堂哥";else path="堂姐";break;
            case"堂姐2":if (sex())path = "堂弟";else path="堂妹";break;
            case"堂妹2":if (sex())path = "堂哥";else path="堂姐";break;



        }
        return path;
    }

    public static String findRelation(String title)
    {
        String path = "";
        switch (title)
        {
            case "爷爷":
            case "祖父":path = "爸爸的爸爸";break;
            case "奶奶":
            case "祖母":path = "爸爸的妈妈";break;
            case "爸爸":
            case "父亲":path = "爸爸";break;
            case "妈妈":
            case "母亲":path = "妈妈";break;
            case "姑妈":path = "爸爸的姐妹";break;
            case "姑丈":path = "爸爸的姐妹的丈夫";break;
            case "伯父":path = "爸爸的哥哥";break;
            case "伯母":path = "爸爸的哥哥的妻子";break;
            case "叔父":path = "爸爸的弟弟";break;
            case "婶婶":path = "爸爸的弟弟的妻子";break;
            case "表哥":path = "爸爸的姐妹的儿子,妈妈的姐妹的儿子,妈妈的兄弟的儿子";break;
            case "表弟":path = "爸爸的姐妹的儿子,妈妈的姐妹的儿子,妈妈的兄弟的儿子";break;
            case "表姐":path = "爸爸的姐妹的女儿,妈妈的姐妹的女儿,妈妈的兄弟的女儿";break;
            case "表妹":path = "爸爸的姐妹的女儿,妈妈的姐妹的女儿,妈妈的兄弟的女儿";break;
            case "哥哥":path = "哥哥";break;
            case "弟弟":path = "弟弟";break;
            case "嫂嫂":path = "哥哥的妻子";break;
            case "弟媳":path = "弟弟的儿子";break;
            case "姐姐":path = "姐姐";break;
            case "妹妹":path = "妹妹";break;
            case "姐夫":path = "姐姐的丈夫";break;
            case "妹婿":path = "妹妹的丈夫";break;
            case "侄子":path = "哥哥的儿子,弟弟的儿子";break;
            case "侄女":path = "哥哥的女儿,弟弟的女儿";break;
            case "外甥":path = "姐姐的儿子,妹妹的儿子";break;
            case "外甥女":path = "姐姐的女儿,妹妹的女儿";break;
            case "儿子":path = "儿子";break;
            case "女儿":path = "女儿";break;
            case "儿媳":path = "儿子的妻子";break;
            case "女婿":path = "女儿的丈夫";break;
            case "孙子":path = "儿子的儿子";break;
            case "孙女":path = "儿子的女儿";break;
            case "外孙":path = "女儿的儿子";break;
            case "外孙女":path = "女儿的女儿";break;
            case"舅舅":path="妈妈的兄弟";break;
            case"舅妈":path="妈妈的兄弟的妻子";break;
            case"姨妈":path="妈妈的姐妹";break;
            case"姨丈":path="妈妈的姐妹的丈夫";break;
            case"外祖父":path="妈妈的爸爸";break;
            case"外祖母":path="妈妈的妈妈";break;
            case"堂哥":path="爸爸的兄弟的儿子";break;
            case"堂弟":path="爸爸的兄弟的儿子";break;
            case"堂姐":path="爸爸的兄弟的女儿";break;
            case"堂妹":path="爸爸的兄弟的女儿";break;
            case"妻子":path="妻子";break;
            case"丈夫":path="丈夫";break;


        }
        if(path.equals(""))
        {
            return"超出查找范围";
        }
        else
        {
            return path;
        }

    }

    private static String findCallToMe2(String title, boolean isMale)
    {
        String path = "";
        switch (title)
        {
            case "祖父":if (isMale)path = "孙子";else path="孙女";break;
            case "祖母":if (isMale)path = "孙子";else path="孙女";break;
            case "姑妈":if (isMale)path = "侄子";else path="侄女";break;
            case "姑丈":if (isMale)path = "侄子";else path="侄女";break;
            case "伯父":if (isMale)path = "侄子";else path="侄女";break;
            case "伯母":if (isMale)path = "侄子";else path="侄女";break;
            case "叔父":if (isMale)path = "侄子";else path="侄女";break;
            case "婶婶":if (isMale)path = "侄子";else path="侄女";break;
            case "表哥1":if (isMale)path = "表弟";else path="表妹";break;
            case "表弟1":if (isMale)path = "表哥";else path="表姐";break;
            case "表姐1":if (isMale)path = "表弟";else path="表妹";break;
            case "表妹1":if (isMale)path = "表哥";else path="表姐";break;
            case "表哥2":if (isMale)path = "表弟";else path="表妹";break;
            case "表弟2":if (isMale)path = "表哥";else path="表姐";break;
            case "表姐2":if (isMale)path = "表弟";else path="表妹";break;
            case "表妹2":if (isMale)path = "表哥";else path="表姐";break;
            case "表哥3":if (isMale)path = "表弟";else path="表妹";break;
            case "表弟3":if (isMale)path = "表哥";else path="表姐";break;
            case "表姐3":if (isMale)path = "表弟";else path="表妹";break;
            case "表妹3":if (isMale)path = "表哥";else path="表姐";break;
            case "哥哥":if (isMale)path = "弟弟";else path="妹妹";break;
            case "弟弟":if (isMale)path = "哥哥";else path="姐姐";break;
            case "嫂嫂":if (isMale)path = "弟弟";else path="妹妹";break;
            case "弟媳":if (isMale)path = "哥哥";else path="姐姐";break;
            case "姐姐":if (isMale)path = "弟弟";else path="妹妹";break;
            case "妹妹":if (isMale)path = "哥哥";else path="姐姐";break;
            case "姐夫":if (isMale)path = "弟弟";else path="妹妹";break;
            case "妹婿":if (isMale)path = "哥哥";else path="姐姐";break;
            case "侄子1":if (isMale)path = "叔父";else path="婶婶";break;
            case "侄子2":if (isMale)path = "伯父";else path="伯母";break;
            case "侄女1":if (isMale)path = "叔父";else path="婶婶";break;
            case "侄女2":if (isMale)path = "伯父";else path="伯母";break;
            case "外甥1":if (isMale)path = "舅舅";else path="姨妈";break;
            case "外甥女1":if (isMale)path = "舅舅";else path="姨妈";break;
            case "外甥2":if (isMale)path = "舅舅";else path="姨妈";break;
            case "外甥女2":if (isMale)path = "舅舅";else path="姨妈";break;
            case "儿子":if (isMale)path = "爸爸";else path="妈妈";break;
            case "女儿":if (isMale)path = "爸爸";else path="妈妈";break;
            case "儿媳":if (isMale)path = "爸爸";else path="妈妈";break;
            case "女婿":if (isMale)path = "爸爸";else path="妈妈";break;
            case "孙子":if (isMale)path = "祖父";else path="祖母";break;
            case "孙女":if (isMale)path = "祖父";else path="祖母";break;
            case "外孙":if (isMale)path = "外祖父";else path="外祖母";break;
            case "外孙女":if (isMale)path = "外祖父";else path="外祖母";break;
            case"舅舅":if (isMale)path = "外甥";else path="外甥女";break;
            case"舅妈":if (isMale)path = "外甥";else path="外甥女";break;
            case"姨妈":if (isMale)path = "外甥";else path="外甥女";break;
            case"姨丈":if (isMale)path = "外甥";else path="外甥女";break;
            case"外祖父":if (isMale)path = "外孙";else path="外孙女";break;
            case"外祖母":if (isMale)path = "外孙";else path="外孙女";break;
            case"堂哥1":if (isMale)path = "堂弟";else path="堂妹";break;
            case"堂弟1":if (isMale)path = "堂哥";else path="堂姐";break;
            case"堂姐1":if (isMale)path = "堂弟";else path="堂妹";break;
            case"堂妹1":if (isMale)path = "堂哥";else path="堂姐";break;
            case"堂哥2":if (isMale)path = "堂弟";else path="堂妹";break;
            case"堂弟2":if (isMale)path = "堂哥";else path="堂姐";break;
            case"堂姐2":if (isMale)path = "堂弟";else path="堂妹";break;
            case"堂妹2":if (isMale)path = "堂哥";else path="堂姐";break;
        }
        return path;
    }

    public  static String findCallFromMe(String expr,boolean ismale)
    {
        String result = RelationTree.find(expr, ismale);
        String[] re = result.split(",");

        List<String> lis1 = new ArrayList();
        for(int i = 0; i < re.length; ++i)
        {
            // lis2.add(Relation.findCallToMe2(re[i], sex));
            int l =re[i].length();
            if(re[i].substring(l-1,l).equals("1")|re[i].substring(l-1,l).equals("2")|re[i].substring(l-1,l).equals("3"))
            {
                re[i] = re[i].substring(0,l-1);
            }
            lis1.add(re[i]);

        }
        HashSet h1 = new HashSet(lis1);
        lis1.clear();
        lis1.addAll(h1);

        /*HashSet h2 = new HashSet(lis2);
        lis2.clear();
        lis2.addAll(h2);*/
        /*String st1 = "";
        for(String j:lis1)
        {
            st1 = j + ",";
        }*/
        if(result!=null)
        {
            return Utils.join(",",lis1);
        }
        else
            return "超出范围";

    }

    public static String findCallToMe(String expr,boolean ismale){
        String result = RelationTree.find(expr, ismale); // ?
        String[] re = result.split(",");

        List<String> lis1 = new ArrayList();
        for(int i = 0; i < re.length; ++i)
        {
            lis1.add(RelationTree.findCallToMe2(re[i], ismale));
        }

        HashSet h2 = new HashSet(lis1);
        lis1.clear();
        lis1.addAll(h2);

        /*String st = "";
        for(String i:lis1)
        {
            st = i+","+st;
        }*/
        String str =  Utils.join(",",lis1).replace(",,",",");
        return str;
    }

}
