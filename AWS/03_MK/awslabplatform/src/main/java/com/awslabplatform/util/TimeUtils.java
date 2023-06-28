package com.awslabplatform.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 *
 * @version 2017-9-28
 * @author   weix
 */
public class TimeUtils {

	//获取系统当前时间
	public static String currentTime() {
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(dt);
	}

	//获取系统当前日期
	public static String currentDate() {
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(dt);
	}

	/**
	 * 取两位小数
	 * @param value
	 * @return
	 */
	public static double convert(double value) {
		long l1 = Math.round(value * 100);
		double ret = l1 / 100.0;
		return ret;
	}
}
