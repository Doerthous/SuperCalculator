package com.sc.calculate;

import com.sc.common.record.RecordWriter;
import com.sc.datastructrue.records.CalResult;

/**
 * Created by Doerthous on 2017/7/5.
 */

public class ModuleBase {
    private RecordWriter recordWriter;
    protected ModuleBase(){
        recordWriter = RecordWriter.getInstance();
    }
    protected void writeRecord(CalResult result){
        recordWriter.write(result.toString());
    }
    public CalResult output(){ return null;}
}
