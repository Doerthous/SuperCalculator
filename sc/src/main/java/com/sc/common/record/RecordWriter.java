package com.sc.common.record;

/**
 * Created by Doerthous on 2017/7/4.
 */

public class RecordWriter {
    //
    private static RecordWriter instance;
    public static RecordWriter getInstance(){
        if(instance == null){
            instance = new RecordWriter();
        }
        return instance;
    }

    private Record record;
    private RecordWriter(){
        record = Record.getInstance();
    }
    public void write(String data){
        write(data, "\r\n");
    }
    public void write(String data, String end){
        record.write(data+end);
    }
}
