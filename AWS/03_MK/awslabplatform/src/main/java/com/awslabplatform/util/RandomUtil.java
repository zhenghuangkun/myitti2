package com.awslabplatform.util;

import java.util.Date;
/**
 * Created by weix on 2017/10/16.
 */
import java.text.SimpleDateFormat;
import java.util.Random;

public class RandomUtil {

    /**
     * 生成随机文件名：当前年月日时分秒+五位随机数
     *
     * @return
     */
    public static String getRandomFileName() {

        SimpleDateFormat simpleDateFormat;

        simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        Date date = new Date();

        String str = simpleDateFormat.format(date);

        Random random = new Random();

        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数

        return rannum + str;// 当前时间
    }

    public static void main(String[] args) {

        String fileName = RandomUtil.getRandomFileName();

        System.out.println(fileName);//8835920140307
    }
}