package com.imooc.o2o.util;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        String aa="F://abc.jpg";
        File a=new File(aa);
        QiNiuYunFileUtils.fileUpload(a);
        System.out.println(QiNiuYunFileUtils.getFileDownloadUrl(a.getName()));

    }
}
