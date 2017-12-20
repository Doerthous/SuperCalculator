package com.sc.utity;

import com.sc.utity.YahooExchangeRateAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Doerthous on 2017/6/26.
 */

public class ThirdPartAPI {
    // 汇率
    public static Double getRate(String src, String obj){
        return YahooExchangeRateAPI.getRate(src, obj);
    }
    public static HashMap<String, Double> getRates(String currency) {
        return YahooExchangeRateAPI.getRates(currency);
    }
    public static String[] getCurrencyName() {
        return YahooExchangeRateAPI.getCurrencyName();
    }

}
