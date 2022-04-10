package com.log.logrecord.file2ByteArr;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CovertFile2ByteArr {
    public static void main(String[] args) {
        FileInputStream fileInputStream = null;
        String filePath = "d:\fileDir";
        byte[] byteArr = null;
        try {
            File file = new File(filePath);
            fileInputStream = new FileInputStream(file);
            // 将文件输入流转为Byte数组
            fileInputStream.read(byteArr);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
