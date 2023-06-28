package com.awslabplatform_admin.util;
/**
 * 复杂密码生成工具（包含a-z、A-Z、0-9和 特殊符号）
 * @author lijf
 * @date 2018年7月8日 上午10:07:29
 */
public class PasswordUtil {
	/**
	 * 
	 * @param passwdLength 密码长度
	 * @return
	 */
	public static String NewPasswd(int passwdLength){
		String[] pswdStr = { "qwertyuiopasdfghjklzxcvbnm",
				"QWERTYUIOPASDFGHJKLZXCVBNM", "0123456789",
				"~!@#$%^&*()_+{}|?:{}" };
		
		int pswdLen = passwdLength;
		String pswd = " ";
		int baseLength=passwdLength>pswdStr.length?pswdStr.length:passwdLength;
		char[] chs = new char[pswdLen];
		for (int i = 0; i < baseLength; i++) {// 各种类型先必取一个

			int idx = (int) (Math.random() * pswdStr[i].length());
			chs[i] = pswdStr[i].charAt(idx);

		}
		for (int i = pswdStr.length; i < pswdLen; i++) {// 取随机值补全剩余空位

			int arrIdx = (int) (Math.random() * pswdStr.length);
			int strIdx = (int) (Math.random() * pswdStr[arrIdx].length());

			chs[i] = pswdStr[arrIdx].charAt(strIdx);
		}
		for (int i = 0; i < 100; i++) {// 调乱字符顺序
			int idx1 = (int) (Math.random() * chs.length);
			int idx2 = (int) (Math.random() * chs.length);

			if (idx1 == idx2) {
				continue;
			}

			char tempChar = chs[idx1];
			chs[idx1] = chs[idx2];
			chs[idx2] = tempChar;
		}

		pswd = new String(chs);
		return pswd;
	}
}
