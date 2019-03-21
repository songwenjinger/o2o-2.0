package com.imooc.o2o.util;

import java.text.SimpleDateFormat;
import java.util.Random;

public class PathUtil {
    private static String seperator = System.getProperty("file.separator");
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat(
            "yyyyMMddHHmmss"); // 时间格式化的格式
    private static final Random r = new Random();

    /**
     * 根据不同的操作系统选择不同的目录
     * @return
     */
    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "D:/projectdev/image/";
        } else {
            basePath = "/home/root/image";
        }
        basePath = basePath.replace("/", seperator);
        return basePath;
    }

    /**
     *
     * @param shopId
     * @return
     */
    public static String getShopImagePath(long shopId) {
       String imagePath="upload/item/shop"+shopId+"/";
       return imagePath.replace("/",seperator);
    }

}