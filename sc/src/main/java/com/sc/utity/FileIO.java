package com.sc.utity;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Created by Doerthous on 2017/7/4.
 */

public class FileIO {
    /*public static String getExternalStorageDirectory(){
        return Environment.getExternalStorageDirectory().getPath();
    }*/
    public static String[] getDir(String path){
        ArrayList<String> dirs = new ArrayList<>();
        File file = new File(path);
        File[] files = file.listFiles();
        if(files != null) {
            try {
                //获取path下的所有文件夹
                for (int k = 0; k < files.length; k++)
                    if (files[k].isDirectory())
                        dirs.add(files[k].getName());
            } catch (Exception e) {
                //file.pa
            }
        }
        return dirs.toArray(new String[]{});
    }
    public static boolean write(String content, String path){
        File file;
        try {
            file = new File(path);
            if(!file.exists())
                file.createNewFile();
        } catch(Exception e) {
            return false;
        }
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(content.getBytes());
            raf.close();
        } catch(Exception e){
            return false;
        }
        return true;
    }
}
