package com.log.logrecord.file;

import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * 将图片转换为base64
 */
public class ImageToBase64 {
    public static void main(String[] args) throws IOException {
        String imgPath = "D:\\IdeaWorkSpace\\logrecord\\img.png";
        System.out.println(covertImgToBase64(imgPath));
    }

    public static String covertImgToBase64(String imgPath) throws IOException {
        File imageFile = new File(imgPath);
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imageFile);
            data=new byte[inputStream.available()];
            inputStream.read(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        System.out.println("字节长度:"+data.length);
        return encoder.encode(data);
    }
}
