package com.sc.utity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sc.R;
import com.sc.calculate.loan.LoanActivity;
import com.sc.common.record.Record;
import com.sc.common.record.RecordActivity;
import com.sc.ui.MainActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by asus on 2017/7/4.
 */

public class DialogUtil extends AppCompatActivity {

    static LinearLayout saveRecordDialog;  //保存提示框
    static EditText dialogFileNameEditText;   //对话框的文件名编辑框

    //AtomicInteger与Integer类相似，但比Integer类更强大
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
    //自动生成ID，生成的结果是数字：1,2,3...
    public static int generateViewId() {
        for (;;) {
            final int result = sNextGeneratedId.get();
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }


    //提示信息对话框
    static public void ShowMsg(String msg, Context context) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(context);
        dlg.setTitle("提示");
        dlg.setMessage(msg);
        dlg.setPositiveButton("确定",null);
        dlg.show();
    }


    //停止按钮
    static public  void StopBtnWasClicked(final Activity activity, ImageButton pauseImageButton){
        //未开始-->停止
        if(RecordActivity.recordState==0){
            ShowMsg("未检测到录制过程",activity);
        }
        //录制中/暂停中-->停止
        else{
            //弹出对话框
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setTitle("保存");
            //设置内容为自定义View
            saveRecordDialog = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.layout, null);
            builder.setView(saveRecordDialog);
            //显示选择的文件路径
            TextView folderTextView=(TextView) saveRecordDialog.findViewById(R.id.DialogFolderTextView);
            folderTextView.setText(RecordActivity.recordPath);
            //设置默认文件名，格式为“20170704_11:15:00_record.txt”
            SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd_HH:mm:ss");
            Date curDate=new Date(System.currentTimeMillis());
            RecordActivity.newFileName=format.format(curDate)+"_record.txt";
            //显示默认文件名
            dialogFileNameEditText=(EditText)saveRecordDialog.findViewById(R.id.DialogFileNameEditText);
            dialogFileNameEditText.setText(RecordActivity.newFileName);
            //显示对话框
            builder.setCancelable(true);
            final AlertDialog dialog = builder.create();
            dialog.show();
            //对话框确定按钮
            Button button = (Button) saveRecordDialog.findViewById(R.id.DialogOKBtn);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RecordActivity.newFileName=dialogFileNameEditText.getText().toString();
                    //修改文件名
                    try{
                        File fileTo=new File(RecordActivity.recordPath,RecordActivity.newFileName);
                        RecordActivity.recordFile.renameTo(fileTo);
                    }
                    catch (Exception e){
                       ShowMsg("文件名格式错误，请重新输入",activity);
                    }
                    dialog.dismiss();
                }
            });
            //对话框取消按钮
            button = (Button) saveRecordDialog.findViewById(R.id.DialogCancelBtn);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        }
        //修改暂停按钮状态
        pauseImageButton=(ImageButton)activity.findViewById(R.id.imageButton1Loan);
        pauseImageButton.setBackgroundResource(android.R.drawable.ic_media_play);
        RecordActivity.recordState=0;
    }


    //暂停按钮
    static public void PauseBtnWasClicked(final Activity activity, ImageButton pauseImageButton){
        //暂停中-->录制中
        if(RecordActivity.recordState==1){
            RecordActivity.recordState=2;
            pauseImageButton.setBackgroundResource(android.R.drawable.ic_media_pause); //注意看图标，这两个图标的命名好像是反的
        }
        //录制中-->暂停中
        else if(RecordActivity.recordState==2){
            RecordActivity.recordState=1;
            pauseImageButton.setBackgroundResource(android.R.drawable.ic_media_play);
        }
        //未开始-->录制中
        else if(RecordActivity.recordState==0){
            RecordActivity.recordState=2;
            pauseImageButton.setBackgroundResource(android.R.drawable.ic_media_pause);
        }
    }

}
