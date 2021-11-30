package com.log.logrecord.xmlparse;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

public class Test
{
    public static void main(String[] args) throws IOException, DocumentException {
        String filepath = "D:\\IdeaWorkSpace\\logrecord\\pom.xml";
        File file = new File(filepath);
        String data = FileUtils.readFileToString(file, "UTF-8");
        InputStream inputStrem = new ByteArrayInputStream(data.getBytes());
        UnicodeReader ur = new UnicodeReader(inputStrem, "UTF-8");
        BufferedReader br = new BufferedReader(ur);
        String str = null;
        while((str=br.readLine())!=null){

        }



    }
}
