package com.architect.peng.commonioapp;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @author shangpeng
 * @version V1.0
 * @Date 2020/8/21 16:30
 */
public class Play {
    public static void main(String[] args) throws Exception {
        InputStream in = new URL("http://www.baidu.com").openStream();
        try {
            final List<String> strings = IOUtils.readLines(in);
//            System.out.println(strings);
        } finally {
            IOUtils.closeQuietly(in);
        }

        //文件
        final List<String> strings = FileUtils.readLines(new File("D://play/1.txt"));

        Validate.notEmpty(strings);

        strings.stream()
                .forEach(System.out::println);
    }
}
