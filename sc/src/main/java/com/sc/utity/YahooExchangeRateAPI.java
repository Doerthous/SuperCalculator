package com.sc.utity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import static com.alibaba.fastjson.JSON.parseObject;

/**
 * Created by Doerthous on 2017/6/27.
 */

public class YahooExchangeRateAPI {
    private static String[] currencyName = {
            "ARS", "AUD", "AZN", "BGN",
            "BHD", "BND", "BRL", "CAD",
            "CHF", "CLP", "CNY", "CZK",
            "DKK", "EGP", "EUR", "FJD",
            "GBP", "HKD", "HUF", "IDR",
            "ILS", "INR", "JPY", "KRW",
            "KWD", "LKR", "MAD", "MGA",
            "MXN", "MYR", "NOK", "NZD",
            "OMR", "PEN", "PGK", "PHP",
            "PKR", "PLN", "RUB", "SAR",
            "SBD", "SCR", "SEK", "SGD",
            "THB", "TOP", "TRY", "TWD",
            "TZS", "USD", "VEF", "VND",
            "VUV", "WST", "XOF", "ZAR",
    } ;
    private static HashMap<String, Double> rates;

    //
    public static Double getRate(String src, String obj) {
        return getRateByAbbr(getAbbr(src), getAbbr(obj));
    }
    public static Double getRateByAbbr(String src, String obj){
        init();
        return rates.get(obj) / rates.get(src);
    }
    public static HashMap<String, Double> getRates(String src){
        return getRatesByAbbr(getAbbr(src));
    }
    public static HashMap<String, Double> getRatesByAbbr(String src){
        init();
        HashMap<String, Double> r = new HashMap<>();
        Double s = rates.get(src);
        for(String key : rates.keySet()){
            r.put(getCurrencyName(key), rates.get(key) / s);
        }
        return r;
    }
    public static String[] getCurrencyName(){
        String[] r = new String[currencyName.length];
        for(int i = 0; i < currencyName.length; ++i){
            r[i] = getCurrencyName(currencyName[i]);
        }
        return r;
    }
    public static String getCurrencyName(String abbr){
        return Abbr2Chinese.toChinese(abbr)+"("+abbr+")";
    }
    //
    private static void init(){
        if(rates == null){
            rates = buildMap(loadJSON(buildURL(currencyName)));
        }
    }
    private static String buildURL(String[] currencyName){
        String url;
        String pair = "";
        for(int i = 0; i < currencyName.length; ++i){
            pair+= "%22CNY"+currencyName[i]+"%22%2C";
        }
        pair = pair.substring(0,pair.length()-3);
        url = "https://query.yahooapis.com/v1/public/yql?q="+
                "select%20*%20from%20yahoo.finance.xchange%20" +
                "where%20pair%20in%20("+pair+")" +
                "&format=json&diagnostics=true" +
                "&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
        return url;
    }
    private static String loadJSON (String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return json.toString();
    }
    private static HashMap<String, Double> buildMap(String json) {
        HashMap<String, Double> rates = new HashMap<>();
        JSONObject j = parseObject(json);
        JSONArray jsonRate = j.getJSONObject("query").getJSONObject("results").getJSONArray("rate");
        for(int i = 0; i < jsonRate.size(); ++i) {
            j = ((JSONObject)jsonRate.get(i));
            String name = j.getString("Name").substring(4,7);
            Double rate = j.getDouble("Rate");
            rates.put(name, rate);
        }
        return rates;
    }
    //
    private static String getAbbr(String fullName){
        return  fullName.substring(fullName.length()-4,fullName.length()-1);
    }

    public static void main(String[] args) {
        /*System.out.println(getRate("CNY","USD"));
        HashMap<String, Double> r = getRates("USD");
        String[] n = getCurrencyName();
        for(String key : r.keySet()){
            System.out.println(key+", "+r.get(key));
        }
        for(String k : n){
            System.out.println(k);
        }*/
        //System.out.println(getAbbr("SDFSD(SDF)"));
    }
}

class Abbr2Chinese {
    public static String toAbbr(String chinese){
        for(int i = 0; i < Abbr2Chinese.chinese.length; ++i){
            if(chinese.equals(Abbr2Chinese.chinese[i])){
                return Abbr2Chinese.abbr[i];
            }
        }
        return "";
    };
    public static String toChinese(String abbr){
        for(int i = 0; i < Abbr2Chinese.abbr.length; ++i){
            if(abbr.equals(Abbr2Chinese.abbr[i])){
                return Abbr2Chinese.chinese[i];
            }
        }
        return "";
    };
    public static final String[] abbr = {
            "HKD","MOP","TWD","EUR",
            "USD","GBP","AUD","KRW",
            "JPY","CNH","CAD","RUB",
            "THB","PHP","ALL","ARS",
            "AWG","AED","ANG","AZN",
            "AOA","BSD","BHD","BBD",
            "BYR","BZD","BMD","BTN",
            "BOB","BWP","BRL","BGN",
            "BIF","BDT","BND","CVE",
            "COP","CRC","CUP","CZK",
            "CHF","CYP","CLP","DZD",
            "DKK","DOP","DJF","EGP",
            "ETB","ECS","ERN","FKP",
            "FJD","GMD","GYD","GHS",
            "GNF","GTQ","GIP","HTG",
            "HNL","HRK","HUF","ISK",
            "INR","IDR","IRR","IQD",
            "ILS","JMD","JOD","KPW",
            "KZT","KHR","KYD","KMF",
            "KES","KWD","LAK","LVL",
            "LBP","LSL","LRD","LYD",
            "LTL","LKR","MKD","MWK",
            "MYR","MVR","MRO","MUR",
            "MXN","MDL","MNT","MAD",
            "MMK","MGA","NAD","NPR",
            "NIO","NGN","NOK","NZD",
            "OMR","PKR","PAB","PGK",
            "PYG","PLN","PEN","QAR",
            "RON","RWF","SEK","SVC",
            "STD","SAR","SCR","SLL",
            "SIT","SBD","SOS","SHP",
            "SDG","SZL","SGD","SYP",
            "TRY","TZS","TOP","TTD",
            "TND","TJS","UAH","UYU",
            "UGX","VUV","VEF","VND",
            "WST","XOF","XAF","XCD",
            "XPF","YER","ZWD","ZAR",
            "ZMW","CNY"};
    public static final String[] chinese = {
            "港元","澳门元","台币","欧元",
            "美元","英镑","澳元","韩元",
            "日元","离岸人民币","加拿大元","俄罗斯卢布",
            "泰国铢","菲律宾比索","阿尔巴尼亚列克","阿根廷比索",
            "阿鲁巴岛弗罗林","阿联酋迪拉姆","列斯荷兰盾","阿塞拜疆新马纳特",
            "安哥拉宽扎","巴哈马元","巴林第纳尔","巴巴多斯元",
            "白俄罗斯卢","伯利兹元","百慕大元","不丹卢比",
            "玻利维亚诺","博茨瓦纳普拉","巴西里亚伊","保加利亚列瓦",
            "布隆迪法郎","孟加拉塔卡","文莱元","佛得角埃斯库多",
            "哥伦比亚比索","哥斯达黎加科朗","古巴比索","捷克克朗",
            "瑞士法郎","塞浦路斯镑","智利比索","阿尔及利亚第纳尔",
            "丹麦克朗","多米尼加比索","吉布提法郎","埃及镑",
            "埃塞俄比亚比尔","厄瓜多尔苏克雷","厄立特里亚","福克兰群岛镑",
            "斐济元","冈比亚达拉西","圭亚那元","加纳塞地",
            "几内亚法郎","危地马拉格查尔","直布罗陀镑","海地古德",
            "洪都拉斯伦皮拉","克罗地亚库纳","匈牙利福林","冰岛克朗",
            "印度卢比","印度尼西亚卢比盾","伊朗里亚尔","伊拉克第纳尔",
            "以色列镑","牙买加元","约旦第纳尔","朝鲜圆",
            "哈萨克斯坦腾格","柬埔寨利尔斯","开曼岛元","科摩罗法郎",
            "肯尼亚先令","科威特第纳尔","老挝基普","拉脱维亚拉图",
            "黎巴嫩镑","莱索托洛提","利比里亚元","利比亚第纳尔",
            "立陶宛里塔斯","斯里兰卡卢比","马其顿第纳尔","马拉维克瓦查",
            "马来西亚林吉特","马尔代夫卢非亚","毛里塔尼亚乌吉亚","毛里求斯卢比",
            "墨西哥比索","摩尔多瓦列伊","蒙古图格里克","摩洛哥道拉姆",
            "缅甸元","马达加斯加阿里亚里","纳米比亚元","尼泊尔卢比",
            "尼加拉瓜科多巴","尼日利亚奈拉","挪威克朗","新西兰元",
            "阿曼里亚尔","巴基斯坦卢比","巴拿马巴尔博亚","巴布亚新几内亚基那",
            "巴拉圭瓜拉尼","波兰兹罗提","秘鲁索尔","卡塔尔利尔",
            "罗马尼亚新列伊","卢旺达法郎","瑞典克朗","萨尔瓦多科朗",
            "圣多美多布拉","沙特阿拉伯里亚尔","塞舌尔法郎","塞拉利昂利昂",
            "斯洛文尼亚托拉捷夫","所罗门群岛元","索马里先令","圣赫勒拿群岛磅",
            "苏丹第纳尔","斯威士兰里兰吉尼","新加坡元","叙利亚镑",
            "土耳其新里拉","坦桑尼亚先令","汤加潘加","特立尼达和多巴哥元",
            "突尼斯第纳尔","塔吉克斯坦索莫尼","乌克兰赫夫米","乌拉圭新比索",
            "乌干达先令","瓦努阿图瓦图","委内瑞拉博利瓦","越南盾",
            "萨摩亚塔拉","多哥非洲共同体法郎","刚果中非共同体法郎","格林纳达东加勒比元",
            "太平洋法郎","也门里亚尔","津巴布韦元","南非兰特",
            "赞比亚克瓦查","人民币"
    };
}