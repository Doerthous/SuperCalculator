package com.sc.common.record;

import com.sc.utity.FileIO;

/**
 * Created by Doerthous on 2017/7/4.
 */

public class Record {
    //
    private static Record instance;
    public static Record getInstance(){
        if(instance == null){
            instance = new Record();
        }
        return instance;
    }

    //
    private Record(){

    }
    private boolean isWritable = false;
    public String buffer = "";

    public void write(String record){
        if(isWritable) {
            buffer += record;
        }
    }
    public void save(String path){
        FileIO.write(buffer, path);
        buffer = "";
    }
    public void setWritable(boolean isWritable){
        this.isWritable = isWritable;
    }

}
