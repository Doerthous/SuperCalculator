package com.sc.utity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Doerthous on 2017/7/3.
 */


public class Dict2D<K1, K2, V>{
    HashMap<K1, List<HashMap<K2, V>>> dict;
    public Dict2D(){
        dict = new HashMap<>();
    }
    public void put(K1 k1, K2 k2, V v){
        List l = (List) getOrDefault(dict, k1, new ArrayList<HashMap<K2, V>>());
        HashMap h = new HashMap();
        h.put(k2, v);
        l.add(h);
        dict.put(k1, l);
    }
    public V get(K1 k1, K2 k2){
        List<HashMap<K2, V>> l = dict.get(k1);
        for(HashMap h : l){
            V i = (V) getOrDefault(h, k2, null);
            if(i == null){
                continue;
            }
            return i;
        }
        return null;
    }

    private static Object getOrDefault(HashMap map, Object key,  Object df){
        for(Object k : map.keySet()){
            if(k.equals(key)){
                return map.get(key);
            }
        }
        return df;
    }
}