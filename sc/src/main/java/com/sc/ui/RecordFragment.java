package com.sc.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sc.R;
import com.sc.common.record.Record;
import com.sc.utity.UIUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecordFragment extends FragmentBase{
    ImageButton imgBtnStartOrPause;
    ImageButton imgBtnStop;
    Record record;
    //
    private int STOP = 0;
    private int START = 1;
    private int PAUSE = 2;
    private int state = STOP;
    private String currModuleName = "";
    private String defaultPath = "/storage/emulated/0/";

    public RecordFragment() {
        // Required empty public constructor
        fragmentId = R.layout.fragment_record;
    }

    @Override
    public void init() {
        // initButton();
        imgBtnStartOrPause = view.findViewById(R.id.rcdBtnStart);
        imgBtnStartOrPause.setOnClickListener(this);
        imgBtnStop = view.findViewById(R.id.rcdBtnStop);
        imgBtnStop.setOnClickListener(this);
        //
        File p = getActivity().getExternalFilesDir("");
        defaultPath = p.getAbsolutePath()+"/";
        record = Record.getInstance();
    }

    public void start(){
        UIUtils.setImageButtonDrawable(imgBtnStartOrPause, R.drawable.btn_pause);
        state = START;
        Toast.makeText(getContext(), "开始记录", Toast.LENGTH_SHORT).show();
        record.setWritable(true);
    }
    public void pause(){
        UIUtils.setImageButtonDrawable(imgBtnStartOrPause, R.drawable.btn_start);
        state = PAUSE;
        Toast.makeText(getContext(), "暂停记录", Toast.LENGTH_SHORT).show();
        record.setWritable(false);
    }
    public void stop(){
        if(!isStop()) {
            UIUtils.setImageButtonDrawable(imgBtnStartOrPause, R.drawable.btn_start);
            state = STOP;
            // 要保存
            String path = getPath();
            record.save(path);
            Toast.makeText(getContext(), "计算过程保存在" + path, Toast.LENGTH_SHORT).show();
            record.setWritable(false);
        }
    }
    public boolean isStop(){
        return state == STOP;
    }
    public boolean isPause(){
        return state == PAUSE;
    }
    public boolean isStart(){
        return state == START;
    }
    public void setCurrentModule(String name){
        currModuleName = name;
    }
    private String getPath(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        return defaultPath+currModuleName+format.format(curDate)+".txt";
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rcdBtnStart:
                if(isPause() || isStop()) {
                    start();
                } else if(isStart()){
                    pause();
                }
                break;
            case R.id.rcdBtnStop:
                if(!isStop()) {
                    stop();
                }
                break;
        }
    }
}
