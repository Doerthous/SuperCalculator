package com.sc.common.record;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sc.R;
import com.sc.calculate.loan.LoanActivity;
import com.sc.ui.MainActivity;
import com.sc.utity.DialogUtil;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;


public class RecordActivity extends Activity {
    //显示当前文件夹路径的文本框
    TextView textView1;
    //ScrollView里的布局
    LinearLayout linearLayout;
    //文件夹列表和用于显示文件夹列表的文本框列表
    ArrayList<File> folderList;
    ArrayList<TextView> textViewList;
    //当前文件夹路径
    String currFolderPath;
    //
    public static String recordPath = "";
    public static String newFileName = "";
    //0：未开始；1：暂停中；2：录制中
    public static int recordState = 0;

    //对话框
    LinearLayout loginDialog;
    static public File recordFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        //绑定控件
        textView1 = (TextView) findViewById(R.id.textView1Record);
        linearLayout = (LinearLayout) findViewById(R.id.LinearLayoutRecord);
        //初始化变量
        folderList = new ArrayList<File>();
        textViewList = new ArrayList<TextView>();

        //检测扩展存储区域是否可用
        if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            recordPath = Environment.getExternalStorageDirectory().getPath();
            //获得根目录
            currFolderPath = Environment.getExternalStorageDirectory().getPath();
            //获取当前文件夹下的文件列表
            getFiles(currFolderPath);
            //更新TextView
            textView1.setText(currFolderPath);
        } else
            textView1.setText("未检测到扩展存储区！");
    }

    //保存Button
    public void Btn1WasClicked(View view){
        recordPath=textView1.getText().toString();
        //切换到主界面
        Intent intent = new Intent();
        //(当前Activity，目标Activity)
        intent.setClass(RecordActivity.this, MainActivity.class);
        //销毁当前活动
        this.finish();
        startActivity(intent);
    }

    //取消Button
    public void Btn2WasClicked(View view){
        //DialogUtil.ShowMsg("取消操作！",RecordActivity.this);
        //切换到主界面
        Intent intent = new Intent();
        //(当前Activity，目标Activity)
        intent.setClass(RecordActivity.this, MainActivity.class);
        //销毁当前活动
        this.finish();
        startActivity(intent);
    }

    //上一层button
    public void Btn3WasClicked(View view){
        //如果当前目录不是根目录
        if (!currFolderPath.equals(Environment.getExternalStorageDirectory().getPath())) {
            int mark = 0;
            for (int i = 0; i < currFolderPath.length(); i++) {
                if (currFolderPath.charAt(i) == '/') {
                    mark = i;
                }
            }
            currFolderPath = currFolderPath.substring(0, mark);
            textView1.setText(currFolderPath);
            getFiles(currFolderPath);
        }
    }

    //获取当前path下的文件列表并输出到ScrollView
    public void getFiles(String path) {
        //文件夹列表初始化
        folderList.clear();
        textViewList.clear();
        linearLayout.removeAllViews();
        //获取path下的所有文件（包括文件和文件夹）列表
        File file = new File(path);
        File[] allFileList = file.listFiles();
        try {
            //获取path下的所有文件夹
            for (int k = 0; k < allFileList.length; k++)
                if (allFileList[k].isDirectory())
                    folderList.add(allFileList[k]);
        }
        catch (Exception e){
            DialogUtil.ShowMsg("手机存储区异常，记录功能将不可用！",this);
        }
        //处理获取到的文件夹列表
        for (int i = 0; i < folderList.size(); i++) {
            //动态创建TextView并添加到滚动板
            textViewList.add(new TextView(this));
            textViewList.get(i).setText(folderList.get(i).getName());
            textViewList.get(i).setClickable(true);
            textViewList.get(i).setId(DialogUtil.generateViewId());
            linearLayout.addView(textViewList.get(i));
            //给TextView添加点击事件
            textViewList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView textViewTemp = (TextView) findViewById(view.getId());
                    currFolderPath = currFolderPath + "/" + textViewTemp.getText().toString();
                    textView1.setText(currFolderPath);
                    //递归调用getFiles()
                    getFiles(currFolderPath);
                }
            });
        }
    }

    //记录
    public static boolean record(String recordStr){
        //文件路径
        String filePath= recordPath;
        //设置默认文件名格式为：20170703_18:56:00_Record.txt
/*        SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd_HH:mm:ss");
        Date curDate=new Date(System.currentTimeMillis());
        String str=format.format(curDate);*/
        String fileName="defaultRecord.txt";
        //创建文件
        recordFile=null;
        try {
            recordFile = new File(filePath + "/" + fileName);
            if(!recordFile.exists())
                recordFile.createNewFile();
        }
        catch(Exception e){
            return false;
        }
        //写入记录
        try {
            RandomAccessFile raf = new RandomAccessFile(recordFile, "rwd");
            raf.seek(recordFile.length());
            //写入一条记录后强制换行
            recordStr+="\r\n";
            raf.write(recordStr.getBytes());
            raf.close();
        }
        catch(Exception e){
            return false;
        }
        return true;
    }

}
