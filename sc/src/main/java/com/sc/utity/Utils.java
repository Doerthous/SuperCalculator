package com.sc.utity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Doerthous on 2017/6/28.
 */

public class Utils {
    public static void main(String[] args) {
        ;
        System.out.printf(String.valueOf(isHexNumber("asfsdf")));
        System.out.printf(String.valueOf(isHexNumber("1328aabcdef1231320456798")));
    }
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+$");
        return pattern.matcher(str).matches();
    }

    public static boolean isNumber(String str) {
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }

    public static boolean isHexNumber(String str){
        String reg = "^[0-9a-fA-Z]+$";
        return str.matches(reg);
    }

    public static String last(String str) {
        return String.valueOf(str.charAt(str.length() - 1));
    }

    public static String first(String str) {
        return String.valueOf(str.charAt(0));
    }

    public static String toASCII(long value) {
        int length = 8;
        StringBuilder builder = new StringBuilder(length);
        for (int i = length - 1; i >= 0; i--) {
            builder.append((char) ((value >> (8 * i)) & 0xFF));
        }
        return builder.toString();
    }

    public static String[] concat(String[] a, String[] b) {
        String[] c = new String[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public static String addSeparator(String str, int step, boolean fromBegin, String separator) {
        if (!fromBegin) {
            str = reverse(str);
        }
        String t = "" + str.charAt(0);
        for (int i = 1; i < str.length(); ++i) {
            if (i % step == 0) {
                t += separator;
            }
            t += str.charAt(i);
        }
        if (!fromBegin) {
            t = reverse(t);
        }
        return t;
    }

    public static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static String join(String separator, String[] array) {
        String t = "";
        for (int i = 0; i < array.length; ++i) {
            t += array[i];
            if (i != array.length - 1) {
                t += separator;
            }
        }
        return t;
    }

    public static String join(String separator, List<String> list) {
        String[] arr = list.toArray(new String[]{});

        /*String t = "";
        for (int i = 0;i < list.size() - 1;++i){
            t +=list.get(i);
            if(i != list.size() -1){
                t +=separator;
            }
        }*/
        return join(separator, arr);
    }

    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if(mNetworkInfo == null){
                return false;
            }
            if (mNetworkInfo.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;//没有网
    }


    public static  String numFormat(Double dbl){
        NumberFormat nFormat=NumberFormat.getNumberInstance();
        nFormat.setMaximumFractionDigits(8);
        return nFormat.format(dbl);
    }

    public static String listToString(List<String> list){
        String str = "";
        for(String s : list){
            str += s;
        }
        return str;
    }
}