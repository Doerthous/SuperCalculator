package com.sc.calculate.tax;

import com.sc.utity.Utils;

/**
 * Created by Doerthous on 2017/6/29.
 */

public class Tax {
    public static void main(String[] args) {
        System.out.println(bonous("6000", "20000"));
        System.out.println(slavary("6000"));
    }
    public static String bonous(String slavary, String bonnus){
        if (Utils.isNumber(slavary)&& Utils.isNumber(bonnus)){
            Double s = Double.valueOf(slavary);
            Double b = Double.valueOf(bonnus);
            Double taxPerMonth = 0.0;
            Double rates[] = {0.03,0.1,0.2,0.25,0.3,0.35,0.45};
            Double aparts[] = {0.0,105.0,555.0,1005.0,2755.0,5505.0,13505.0};
            Double layer[] = {1500.0,4500.0,9000.0,35000.0,55000.0,80000.0};
            if(s < 3500){
                taxPerMonth = (b - (3500-s))/12;
            } else {
                taxPerMonth = b/12;
            }
            int i = 0;
            while(taxPerMonth > layer[i]){
                ++i;
            }
            return String.valueOf(12*taxPerMonth*rates[i] - aparts[i]);
        }else {
            return "请输入金额";
        }

    }
    public static String slavary(String slavary){
        if (!Utils.isNumber(slavary)){
            return "请输入金额";
        }else{
            Double s = Double.valueOf(slavary);
            Double taxPerMonth = 0.0;
            Double rates[] = {0.03,0.1,0.2,0.25,0.3,0.35,0.45};
            Double aparts[] = {0.0,105.0,555.0,1005.0,2755.0,5505.0,13505.0};
            Double layer[] = {1500.0,4500.0,9000.0,35000.0,55000.0,80000.0};
            Double layer2[] = {1455.0,4155.0,7755.0,27255.0,41255.0,57505.0};
            taxPerMonth = s-3500;
            if (taxPerMonth > 0){
                if (taxPerMonth > 80000.0){
                    return String.valueOf(taxPerMonth*rates[6] - aparts[6]);
                }else{
                    int i = 0;
                    while(taxPerMonth > layer[i]){
                        ++i;
                    }
                    return String.valueOf(taxPerMonth*rates[i] - aparts[i]);
                }
            }else{
                return "无需纳税";
            }
        }
    }
}

